package fr.upem.ediall02.game.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import fr.upem.ediall02.game.model.Game2048Model;
import fr.upem.ediall02.game.view.Window2048;

/**
 * @class ActionUndo
 * @extends {@link AbstractAction}
 * @author eric
 *
 */
public class ActionUndo extends AbstractAction {
    private Game2048Model model;
    private Window2048 window;
    
    private static final long serialVersionUID = 4019841623585494495L;

   /**
    * Constructor
    * @param model
    * @param window
    */
    public ActionUndo(Game2048Model model, Window2048 window) {
	this.model = model;
	this.window = window;
    }
    
    /**
     * Undo the last move
     */
    @Override
    public void actionPerformed(ActionEvent event) {
	if (model.isMoveEnabled()) {
        	if (!model.isSaveOption() && !model.isReplay()) {
                	model.getBoard().undoLastMove();
                	window.updateBoardContainer();
                	window.updateScoreContainer();
        	}
	}
    }

}
