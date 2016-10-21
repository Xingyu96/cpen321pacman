package org.jpacman.undo;

import org.jpacman.framework.factory.DefaultGameFactory;

public class UndoGameFactory extends DefaultGameFactory {

	private transient UndoableGame game;

	
	/**
	 * Make Undoable game instead of regular game
	 * @return new UndoableGame
	 */
	@Override
	public UndoableGame makeGame() {
		game = new UndoableGame();
		return game;
	}

	/**
	 * get the newly created UndoableGame
	 * @return undoableGame
	 */
	@Override
	protected UndoableGame getGame() {
		assert game != null;
		return game;
	}

}