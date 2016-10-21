package org.jpacman.undo;
    
    import java.util.ArrayDeque;
    import java.util.Deque;
    import java.util.List;

import org.jpacman.framework.model.Direction;
import org.jpacman.framework.model.Food;
    import org.jpacman.framework.model.Game;
    import org.jpacman.framework.model.Ghost;
    import org.jpacman.framework.model.Player;
    import org.jpacman.framework.model.Tile;

//    public class UndoableGame extends Game {
//
//        private Deque<GameSnapshot> gameHistory = new ArrayDeque<GameSnapshot>();
//        private GameSnapshot undoGameSnapshot;
//        private boolean first_undo = false; // tells if player has done another undo before this one
//
//        public void undo() {
//            Player player = super.getPlayer();
//
//            assert gameHistory != null;
//
//            getPreviousSnapshot();
//
//            placingFoodBack(player);
//            placingPacmanBack(player);
//            placingGhostBack();
//            notifyViewers();
//
//        }
//
//        public void getPreviousSnapshot() {
//
//            if (first_undo) {
//                popGameSnapshot();// the first pop contains the same position as the player pressed undo
//                popGameSnapshot();// this stores previous Pacman location
//                first_undo = false;
//            } else {
//                popGameSnapshot();
//            }
//
//        }
//
//        // taking the first Snapshot
//        public void firstSnapshot() {
//            gameHistory.push(new GameSnapshot(this));
//        }
//
//        // Taking Snapshot everytime player has pressed the key
//        // only record the playerPosition when playerPosition has actually changed
//        public void keyPressed() {
//            Player player = super.getPlayer();
//            if (gameHistory.size() >= 1) {
//                if (gameHistory.peekFirst().getPlayerPosition() != player.getTile()) {
//                    gameHistory.push(new GameSnapshot(this));
//                    first_undo = true;
//                }
//            } else {
//                gameHistory.push(new GameSnapshot(this));
//            }
//        }
//
//        // if gameHistory has more than the one Snapshot, pop the last Snapshot. If not, just use
//        // the first Snapshot
//        private void popGameSnapshot() {
//            if (gameHistory.size() > 1) {
//                undoGameSnapshot = gameHistory.pop();
//            } else {
//                undoGameSnapshot = gameHistory.peekFirst();
//            }
//        }
//
//        // to ensure food is reproduced iff player has eaten food
//        // placing the food back to the position where Player has been
//        // Precondition: Point has changed
//        private void placingFoodBack(Player p) {
//            Tile foodTilePosition = p.getTile();
//
//            if (undoGameSnapshot.getPlayerPoint() != p.getPoints()) {
//                Food food = new Food();
//                food.occupy(foodTilePosition);
//            }
//
//        }
//
//        // this function will placing Pacman back to previous location and always resurrect the Pacman
//        // upon undo function is called, reseting Pacman's mouth direction
//        private void placingPacmanBack(Player player) {
//            player.deoccupy();
//            player.occupy(undoGameSnapshot.getPlayerPosition());
//            player.resurrect();
//
//            // Initializations for setting the player's points.
//            resetPlayerPoints(player);
//
//            // set the Pacman's mouth direction
//            player.setDirection(undoGameSnapshot.getPlayerDirection());
//        }
//
//        private void resetPlayerPoints(Player player) {
//            int newPoint = player.getPoints();
//            int originalPoint = undoGameSnapshot.getPlayerPoint();
//            super.getPointManager().consumePointsOnBoard(player, originalPoint - newPoint);
//        }
//
//        // Initialization for setting ghosts' positions.
//        private void placingGhostBack() {
//            List<Ghost> gameGhost = super.getGhosts();
//            for (int i = 0; i < gameGhost.size(); i++) {
//                gameGhost.get(i).deoccupy();
//                gameGhost.get(i).occupy(undoGameSnapshot.getGhostPosition().get(i));
//            }
//        }
//
//    }

    public class UndoableGame extends Game {

    	public Deque<UndoGameStack> gameDeque = new ArrayDeque<UndoGameStack>();
    	UndoButton undo;

    	public void undo() {

    		// check that the deque is not empty and that player has not won
    		if (gameDeque.size() >= 1 && !this.won()) {
    			UndoGameStack undogame = gameDeque.pop();
    			undogame.LastStack(this);
    			notifyViewers();

    		}

    	}

    	@Override
    	public void movePlayer(Direction dir) {
    		// create current game's data ( pacman position, ghosts, points ...etc)
    		UndoGameStack game = new UndoGameStack(this);
    		gameDeque.push(game);

    		super.movePlayer(dir);
    	}

    	@Override
    	public void moveGhost(Ghost theGhost, Direction dir) {
    		super.moveGhost(theGhost, dir);
    	}

    }


