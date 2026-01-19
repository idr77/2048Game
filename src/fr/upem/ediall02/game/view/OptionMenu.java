package fr.upem.ediall02.game.view;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import fr.upem.ediall02.game.controller.ActionIA;
import fr.upem.ediall02.game.controller.ActionUndo;
import fr.upem.ediall02.game.model.Game2048Model;

/**
 * 
 * @class OptionMenu
 * @extends JMenuBar
 * @author eric
 * 
 * The menu bar of the game
 *
 */
public class OptionMenu extends JMenuBar {
    private Game2048Model model;
    private Window2048 window;
    
    private static final long serialVersionUID = 401984159545494495L;
    
    private JMenu file = new JMenu("File");
    private JMenuItem getHint = new JMenuItem("Get Hint !");
    private JMenuItem exit = new JMenuItem("Exit");
    
    private JMenu edit = new JMenu("Edit");
    private JMenuItem undo = new JMenuItem("Undo last move");
    
    
    /**
     * Cnstructor
     * @param model
     * @param window
     */
    public OptionMenu(Game2048Model model, Window2048 window) {
	this.model = model;
	this.window = window;
	setListeners();
	this.add(file);
	this.add(edit);
    }
    
    /**
     * Set listeners to the menu item
     */
    private void setListeners() {
	// File Menu
	file.setMnemonic(KeyEvent.VK_F);
	file.getAccessibleContext().setAccessibleDescription("File menu");

	getHint.addActionListener(new ActionIA(model, window));
	getHint.setMnemonic(KeyEvent.VK_H);
	getHint.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_DOWN_MASK));
	getHint.getAccessibleContext().setAccessibleDescription("Get a hint for the next move");
	file.add(getHint);

	exit.addActionListener(l -> {
	    model.gameOver();
	});
	exit.setMnemonic(KeyEvent.VK_X);
	exit.getAccessibleContext().setAccessibleDescription("Exit the game");
	file.add(exit);
	
	// Edit Menu
	edit.setMnemonic(KeyEvent.VK_E);
	edit.getAccessibleContext().setAccessibleDescription("Edit menu");

	undo.addActionListener(new ActionUndo(model, window));
	undo.setMnemonic(KeyEvent.VK_U);
	undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK));
	undo.getAccessibleContext().setAccessibleDescription("Undo the last move");
	edit.add(undo);
    }

}
