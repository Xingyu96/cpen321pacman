package org.jpacman.undo;
import org.jpacman.framework.model.Direction;
import org.jpacman.framework.ui.PacmanInteraction;

public class UndoPacmanInteraction extends PacmanInteraction{
    
//    public void undoUp(){
//        movePlayer(Direction.DOWN);
//    }
//    
//    public void undoDown(){
//        movePlayer(Direction.UP);
//    }
//    
//    public void undoRight(){
//        movePlayer(Direction.LEFT);
//    }
//    
//    public void undoLeft(){
//        movePlayer(Direction.RIGHT);
//    }
	/**
	 * updates the state and calls undo from UndoableGame
	 */
	public void undo() {
		updateState();
		((UndoableGame) this.getGame()).undo();

	}
}
