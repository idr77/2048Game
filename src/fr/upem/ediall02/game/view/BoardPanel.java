package fr.upem.ediall02.game.view;

import java.awt.Graphics;

import javax.swing.JPanel;

import fr.upem.ediall02.game.model.Game2048Model;

/**
 * @class BoardPanel
 * @extends JPanel
 * @author eric
 * 
 * Draw the board.
 *
 */
public class BoardPanel extends JPanel{

    private Game2048Model model;
    private static final long serialVersionUID = 4019841629545494495L;

    public BoardPanel(Game2048Model model) {
	this.model = model;
	this.setPreferredSize(model.getBoard().getDimension());
    }

    @Override
    protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	model.getBoard().draw(g);
    }

}
