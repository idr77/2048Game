package fr.upem.ediall02.game.controller;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import fr.upem.ediall02.game.model.Direction;
import fr.upem.ediall02.game.model.Game2048Model;
import fr.upem.ediall02.game.view.Window2048;

/**
 * @class ActionMove
 * @extends {@link AbstractAction}
 * @author eric
 *
 */
public class ActionMove extends AbstractAction {

    private Game2048Model model;
    private Window2048 window;
    private Direction dir;
    private static final long serialVersionUID = 4019841623545494495L;
    
    /**
     * Constructor
     * @param model
     * @param window
     * @param dir
     */
    public ActionMove(Game2048Model model, Window2048 window, Direction dir) {
	this.model = model;
	this.window = window;
	this.dir = dir;
    }
    
    /**
     * Add a tile and update the window
     */
    private void addTile() {
	model.getBoard().addRandomTile();
	if (model.isSaveOption()) {
	    try {
		model.writeBoardInFile();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
	window.updateBoardContainer();
	window.updateScoreContainer();
    }
    
    /**
     * Performs a move
     */
    @Override
    public void actionPerformed(ActionEvent event) {
	if (model.isMoveEnabled()) {
        	model.getBoard().setDirectionMove(dir);
        	model.getBoard().moveAndMergeTiles();
        	model.updateHighScore();
        	if (model.isGameOver()) {
        	    model.disableMoves();
        	    if(model.getBoard().getBestTile() >= 2048) {
        		JOptionPane.showMessageDialog(null, "Vous avez réussi", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        	    }
        	    else {
        		JOptionPane.showMessageDialog(null, "Vous avez échoué", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        	    }
        	    model.gameOver();
        	}
        	else {
        	    addTile();
        	}
	}
    }
}
