package org.jpacman.undo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.jpacman.framework.ui.ButtonPanel;

public class UndoButton extends ButtonPanel{
	
	private static final long serialVersionUID = 1L;
	private JButton undoButton;
	
//	@Override
//	 public void initialize() {  
//  	  	startButton = new JButton("Start");
//       	stopButton = new JButton("Stop");
//    	    undoButton = new JButton("Undo");
////    	initializeStartButton();
////    	initializeStopButton();
////    	initializeUndoButton();
//    	
////    	JButton exitButton = createExitButton();
////    	    	
////        setName("jpacman.buttonPanel");
////        addButton(startButton);
////        addButton(stopButton);
////        addButton(exitButton);  
//        addButton(undoButton);
//     }
//	
//	public void initializeUndoButton(){
//		undoButton.addActionListener(new ActionListener() {
//    		@Override
//			public void actionPerformed(ActionEvent e) {
//    			UndoablePacman.undo();
//    		}
//    	});
//    	undoButton.setName("jpacman.undo");
//    	undoButton.requestFocusInWindow();
//	}
	
	/**
	 * Casts getPacmanInteractor() to UndoPacmanInteraction so Undo button appears
	 * @return pacmanInteraction of the Game with undo
	 */
	@Override
	public UndoPacmanInteraction getPacmanInteractor() {
		return (UndoPacmanInteraction) super.getPacmanInteractor();
	}
	
	@Override
	/**
	 * Adding in the undo button.
	 */
	public void initialize() {
		super.initialize();

		undoButton = new JButton("Undo");
		initializeUndoButton();

		addButton(undoButton);
	}

	/**
	 * Create the undo button.
	 */
	protected void initializeUndoButton() {

		undoButton.setEnabled(true);

		undoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getPacmanInteractor().undo();
				pause();

			}
		});
		undoButton.setName("jpacman.undo");
	}

	
	
	
	
	
}
