package org.jpacman.undo;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import org.jpacman.framework.factory.FactoryException;
import org.jpacman.framework.ui.ButtonPanel;
import org.jpacman.framework.ui.MainUI;
import org.jpacman.framework.ui.PacmanInteraction;

public class UndoablePacman extends MainUI{
 
	private static UndoButton buttonPanel = new UndoButton();

	
	private static final long serialVersionUID = -59470379321937183L;

//	static void undo(){
//	}
//    
//	public UndoablePacman(){
//		
//	}
//	
//	@Override
//	protected ButtonPanel createButtonPanel(PacmanInteraction pi) {
//    	assert pi != null;
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

	public void undo() {
		getGame().undo();

	}

	@Override
	public UndoableGame getGame() {
		return (UndoableGame) super.getGame();
	}

	public MainUI UndoableFactorySetup() {

		return withFactory(undoGameFactory);
	}

	public UndoablePacman() {
		super();
		withFactory(undoGameFactory);
		withButtonPanel(new UndoButton());
		withModelInteractor(new UndoPacmanInteraction());

	}

	public static void main(String[] args) throws FactoryException {
		new UndoablePacman().main();
	}

}
