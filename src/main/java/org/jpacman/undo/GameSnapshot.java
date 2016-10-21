package org.jpacman.undo;

import java.util.ArrayList;
import java.util.List;

import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.Ghost;
import org.jpacman.framework.model.Player;
import org.jpacman.framework.model.Tile;

public class GameSnapshot {
	private int point = 0;
	private Direction playerDir;
	private Tile playerPosition;
	private List<Tile> ghostsPosition = new ArrayList<Tile>();
	private boolean playerAlive;
	
	public GameSnapshot(UndoableGame game){
		setPlayerStats(game);
		setGhostStats(game);
	}
	
	public void setPlayerStats(UndoableGame game) {
		Player player = game.getPlayer();				
		this.point = player.getPoints();				
		this.playerDir = player.getDirection();			
		this.playerPosition = player.getTile();			
		this.playerAlive = player.isAlive();			
	}
	
	public void setGhostStats(UndoableGame game) {
		
		for (int i = 0; i < game.getGhosts().size(); i++) {
			Ghost ghost = game.getGhosts().get(i);
			this.ghostsPosition.add(i, ghost.getTile());
		}

	}
	
	public Tile getPlayerPosition() {
		return playerPosition;
	}

	public Direction getPlayerDirection() {
		return playerDir;
	}

	public List<Tile> getGhostPosition() {
		return ghostsPosition;
	}

	public int getPlayerPoint() {
		return point;
	}

	public boolean playerAlive() {
		return playerAlive;
	}

	
	
}
