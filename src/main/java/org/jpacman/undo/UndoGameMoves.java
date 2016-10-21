package org.jpacman.undo;

import java.util.Stack;

import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.Food;
import org.jpacman.framework.model.Ghost;
import org.jpacman.framework.model.Player;
import org.jpacman.framework.model.Tile;
import org.jpacman.framework.controller.AbstractGhostMover;
import org.jpacman.framework.controller.RandomGhostMover;
import org.jpacman.framework.factory.IGameFactory;
import org.jpacman.framework.factory.DefaultGameFactory;
import org.jpacman.undo.UndoableGame;
import org.jpacman.undo.UndoButton;


/**
 * Object that stores data for every move
 * Data includes = ghost posision, food position, and the direction
 *
 */
public class UndoGameMoves {
    Direction direction;
	Tile playerPosition;
	int position;
	Stack<Tile> ghostPositions = new Stack<Tile>();

	/**
	 * Adding ghost positions to the stack and getting the current position of pacman
	 * @param game UndoableGame - current game that is being played
	 */
	public UndoGameMoves(UndoableGame game) {
	    playerPosition = game.getPlayer().getTile();
		direction = game.getPlayer().getDirection();
		position = game.getPointManager().getFoodEaten();

		// Saving ghost positions
		for (int i = 0; i < game.getGhosts().size(); i++) {
		    ghostPositions.push(game.getGhosts().get(i).getTile());
		}
	}

	/**
	 * Moves everything back to the previous position - ghosts, pacman, and food. Brings player back to life if game is over
	 * @param undoableGame UndoableGame - current game that is being played
	 */
	public void lastMove(UndoableGame undoableGame) {
	    
		Player player = undoableGame.getPlayer();
		if (!player.isAlive()) {
			player.resurrect();      // print player back if undo is pressed and game is over
		}

		int change = position - undoableGame.getPointManager().getFoodEaten();
		undoableGame.getPointManager().consumePointsOnBoard(player, change);
		if (change != 0) {        //bring back food that was already eaten
            Food food = new Food();
            food.occupy(player.getTile());
        }
		
		// move pacman to previous tile
		player.deoccupy();
        player.setDirection(direction);
		player.occupy(this.playerPosition);

		// move ghosts to previous tiles
		for (int j = 0; j < undoableGame.getGhosts().size(); j++) {
			undoableGame.getGhosts().get(j).deoccupy();
			undoableGame.getGhosts().get(j).occupy(ghostPositions.pop());

		}
		
	}

}
