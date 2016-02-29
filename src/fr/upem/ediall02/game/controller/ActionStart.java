package fr.upem.ediall02.game.controller;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import fr.upem.ediall02.game.model.Game2048Model;
import fr.upem.ediall02.game.model.GameFile;
import fr.upem.ediall02.game.view.Window2048;

/**
 * @class ActionStart
 * @extends {@link AbstractAction}
 * @author eric
 *
 */
public class ActionStart extends AbstractAction{

    private Game2048Model model;
    private Window2048 window;
    private static final long serialVersionUID = 4019841623545494495L;
    
    /**
     * Constructor
     * @param model
     * @param window
     */
    public ActionStart(Game2048Model model, Window2048 window) {
	this.model = model;
	this.window = window;
    }
    
    /**
     * Add a tile on the board and update the window.
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
     * Start the game following its options
     */
    @Override
    public void actionPerformed(ActionEvent event) {
	if (model.isRandom()) {
	    model.createRandomFile();
	    JOptionPane.showMessageDialog(null, "Jeu aléatoire créé", "Information", JOptionPane.INFORMATION_MESSAGE);
	}
	if (model.isReplay() || model.isRandom()) {
	    if (model.getGameFileType() == GameFile.GAME_TYPE) {
		model.playGameFile();
	    }
	    model.updateHighScore();
	}
	else {
	    model.clear();
	    addTile();
	    addTile();
	}
	window.updateBoardContainer();
	window.updateScoreContainer();
	model.enableMoves();
    }
}
