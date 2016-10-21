package org.jpacman.undo;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import org.jpacman.framework.factory.FactoryException;
import org.jpacman.framework.ui.ButtonPanel;
import org.jpacman.framework.ui.MainUI;
import org.jpacman.framework.ui.PacmanInteraction;

public class UndoablePacman extends MainUI{
 
	//private static UndoButton buttonPanel = new UndoButton();

	
	private static final long serialVersionUID = -59470379321937183L;

//	static void undo(){
//	}
//    
//	public UndoablePacman(){
//		
//	}
//	
//	@Override
//	protected ButtonPanel createButtonPanel(PacmanInteraction PI) {
//    	assert PI != null;
//    	if (buttonPanel == null) {
//    		buttonPanel = new UndoButton();
//    	}
//    	return buttonPanel
//     		.withParent(this)
//    		.withInteractor(pi);
//    }
//
//
//    public static void main(String[] args) throws FactoryException {
//        UndoablePacman u = new UndoablePacman();
//        u.withButtonPanel(buttonPanel);
//        u.initialize();
//        u.start();    
//        
//        System.out.println(u.isValid());
//    }
	
	public UndoGameFactory undoGameFactory = new UndoGameFactory();

	/**
	 * cast getGame to UndoableGame
	 * @return UndoableGame object
	 */
	@Override
	public UndoableGame getGame() {
		return (UndoableGame) super.getGame();
	}
	
	
	/**
	 * call .undo() for the Undo game
	 */
	public void undo() {
		getGame().undo();

	}

	
	/**
	 * 
	 * @return undoGameFactory (itself) for fluency.
	 */
	public MainUI UndoableFactorySetup() {

		return withFactory(undoGameFactory);
	}

	/**
	 * Creates button panel with undo button and checks undoGameFactory and undo interaction for fluency
	 */
	public UndoablePacman() {
		super();
		withFactory(undoGameFactory);
		withButtonPanel(new UndoButton());
		withModelInteractor(new UndoPacmanInteraction());

	}

	
	/**
	 * Calls itself to run the game #recursion4lyfe
	 * @param args
	 * @throws FactoryException
	 */
	public static void main(String[] args) throws FactoryException {
		new UndoablePacman().main();
	}

}
