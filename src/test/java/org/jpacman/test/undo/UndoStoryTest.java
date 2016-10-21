package org.jpacman.test.undo;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayDeque;
import java.util.Deque;

import org.jpacman.framework.factory.FactoryException;
import org.jpacman.framework.model.Ghost;
import org.jpacman.framework.model.Tile;
import org.jpacman.framework.ui.MainUI;
import org.jpacman.framework.ui.PacmanInteraction.MatchState;
import org.jpacman.test.framework.accept.*;
import org.jpacman.undo.UndoPacmanInteraction;
import org.jpacman.undo.UndoablePacman;

public class UndoStoryTest extends MovePlayerStoryTest{
	private UndoPacmanInteraction engine;
	
	private UndoablePacman theUndo;
	private Tile foodTile, ghostTile;

	@Override
	public MainUI makeUI() {
		theUndo = new UndoablePacman();
		theUndo.UndoableFactorySetup();// set up undoFactory
		return theUndo;
	}

	@Override
	protected MainUI getUI() {
		return theUndo;
	}
	
    @Test
    public void test_S7_1_GhostIsClose() {
        
        
     
    }
    
    @Test
    public void test_S7_2_MissedACoin(){
    	// given
		getEngine().start();		
		Tile tile = getPlayer().getTile();
		
		// and
		getEngine().left();
		int score = getPlayer().getPoints();
		Tile food = getPlayer().getTile();

		// when
		theUndo.undo();
		
		// then
		assertEquals(getPlayer().getTile(), tile);
		assertEquals(getPlayer().getPoints(), score - 10);
    }
    
    @Test
    public void test_S7_3_UserJustDied() {
        //given
    	getEngine().start();
    	getEngine().up();
    	getPlayer().die();
    	assertFalse(getPlayer().isAlive());
    	
    	//when
    	theUndo.undo();
    	
    	//then
    	assertTrue(getPlayer().isAlive());
    }
    
    @Test
    public void test_S7_4_TookTheWrongPath(){
    	// given
		getEngine().start();
		// and
		getEngine().left();
		getEngine().left();

		Tile oldPlayerPos = getPlayer().getTile();
    	Deque<Tile> oldGhostPos = new ArrayDeque<Tile>();
    	for(Ghost g : getUI().getGame().getGhosts()){
    		oldGhostPos.push(g.getTile());
    	}

		getEngine().right();
		// when
		theUndo.undo();
		
		Tile newPlayerPos = getPlayer().getTile();
    	Deque<Tile> newGhostPos = new ArrayDeque<Tile>();
    	for(Ghost g : getUI().getGame().getGhosts()){
    		newGhostPos.push(g.getTile());
    	}
    	
		// then
		assertEquals(newPlayerPos, oldPlayerPos);
    	assertEquals(oldGhostPos.size(), newGhostPos.size());
    	for(int i = 0; i < oldGhostPos.size(); i++){
    		assertEquals(oldGhostPos.pop(), newGhostPos.pop());
    	}
    }
    
    @Test
    public void test_S7_5_PressedStart(){
    	//given
    	getEngine().start();
    	getEngine().right();
    	
    	Tile playerTile = getPlayer().getTile();
    	//when
    	getEngine().start();
    	
    	//then
    	assertEquals(playerTile, getPlayer().getTile());
    	
    }
    
    @Test
    public void test_S7_6_Start(){
    	Tile playerTile = getPlayer().getTile();
    	//given
    	getEngine().start();
        //when
        theUndo.undo();
        //then
	    assertEquals(playerTile, getPlayer().getTile());
    }

}
