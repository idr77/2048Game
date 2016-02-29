package fr.upem.ediall02.game.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import fr.upem.ediall02.game.Options;
import fr.upem.ediall02.game.model.Direction;
import fr.upem.ediall02.game.model.Game2048Model;
import fr.upem.ediall02.game.model.IASolver;
import fr.upem.ediall02.game.view.Window2048;

/**
 * @class ActionIA
 * @extends AbstractAction
 * @author eric
 *
 */
public class ActionIA extends AbstractAction {
    private static final long serialVersionUID = 1L;
    private IASolver IA;
    private Game2048Model model;
    private Window2048 window;
    
    /**
     * Constructor
     * @param model
     * @param window
     */
    public ActionIA(Game2048Model model, Window2048 window) {
	this.model = model;
	this.window = window;
	this.IA = new IASolver(model);
    }
    
    /**
     * Performs a move from the IA.
     */
    @Override
    public void actionPerformed(ActionEvent arg0) {
	Direction d;
	if (model.getOpt().isEnabled(Options.OPT_IASOLVER)) {
	    d = IA.getHint();
	}
	else {
	    d = IA.getHintAlphaBeta();
	}
	new ActionMove(model, window, d).actionPerformed(arg0);
    }


}
