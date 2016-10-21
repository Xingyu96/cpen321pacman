package org.jpacman.undo;
    

import java.util.List;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Queue;
import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.Food;
import org.jpacman.framework.model.Game;
import org.jpacman.framework.model.Ghost;
import org.jpacman.framework.model.Player;
import org.jpacman.framework.model.Tile;

//public class UndoableGame extends Game {
//
//  private Stack<UndoGameMoves> gameHistory = new Stack<UndoGameMoves>();
//  private UndoGameMoves undoGameMoves;
//
//  public void undo() {
//      Player player = super.getPlayer();
//      assert gameHistory != null;
//      getPreviousSnapshot();
//      placingFoodBack(player);
//      placingPacmanBack(player);
//      placingGhostBack();
//      notifyViewers();
//  }
//
//  public void getPreviousSnapshot() {
//      if (first_undo) {
//          popGameSnapshot();// the first pop contains the same position as the player pressed undo
//          popGameSnapshot();// this stores previous Pacman location
//          first_undo = false;
//      } else {
//          popGameSnapshot();
//      }
//  }
//
//
//  // Saving data everytime key is pressed
//  public void keyPressed() {
//      Player player = super.getPlayer();
//      if (gameHistory.size() >= 1) {
//          if (gameHistory.peekFirst().getPlayerPosition() != player.getTile()) {
//              gameHistory.push(new GameSnapshot(this));
//              first_undo = true;
//          }
//      } else {
//          gameHistory.push(new GameSnapshot(this));
//      }
//  }
//
//  private void popGameSnapshot() {
//      if (gameHistory.size() > 1) {
//          undoGameSnapshot = gameHistory.pop();
//      } else {
//          undoGameSnapshot = gameHistory.peekFirst();
//      }
//  }
//
//  private void placingFoodBack(Player p) {
//      Tile foodTilePosition = p.getTile();
//
//      if (undoGameSnapshot.getPlayerPoint() != p.getPoints()) {
//          Food food = new Food();
//          food.occupy(foodTilePosition);
//      }
//
//  }
//
//  
//  private void placingPacmanBack(Player player) {
//      player.deoccupy();
//      player.occupy(undoGameSnapshot.getPlayerPosition());
//      player.resurrect();
//
//      // Initializations for setting the player's points.
//      resetPlayerPoints(player);
//
//      // set the Pacman's mouth direction
//      player.setDirection(undoGameSnapshot.getPlayerDirection());
//  }
//
//  private void resetPlayerPoints(Player player) {
//      int newPoint = player.getPoints();
//      int originalPoint = undoGameSnapshot.getPlayerPoint();
//      super.getPointManager().consumePointsOnBoard(player, originalPoint - newPoint);
//  }
//
//  // Initialization for setting ghosts' positions.
//  private void placingGhostBack() {
//      List<Ghost> gameGhost = super.getGhosts();
//      for (int i = 0; i < gameGhost.size(); i++) {
//          gameGhost.get(i).deoccupy();
//          gameGhost.get(i).occupy(undoGameSnapshot.getGhostPosition().get(i));
//      }
//  }
//
//}


    public class UndoableGame extends Game {

    	public Stack<UndoGameMoves> playerMoves = new Stack<UndoGameMoves>();
    	UndoButton undo;
    	
    	/**
    	 * Moves player in appropriate direction
    	 * @param direction Direction
    	 */
    	@Override
    	public void movePlayer(Direction direction) {
    	    
    		// Add starting data to the stack because of new game
    		UndoGameMoves undoableMoves = new UndoGameMoves(this);
    		playerMoves.push(undoableMoves);
    		super.movePlayer(direction);
    	}

    	/**
    	 * moves ghost in appropriate direction
    	 * @param Ghost ghost
    	 * @param direction Direction
    	 */
    	@Override
    	public void moveGhost(Ghost Ghost, Direction direction) {
    		super.moveGhost(Ghost, direction);
    	}
    	
    	/**
    	 * Gets the last move that just occurred so game can be brought back to that state
    	 */
    	public void undo() {
            // Checks to see that the stack isn't empty and that there are still moves left.
            if (playerMoves.size() >= 1 && !this.won()) {
                UndoGameMoves undogame = playerMoves.pop();
                undogame.lastMove(this);
                notifyViewers();
            }
        }
    }


