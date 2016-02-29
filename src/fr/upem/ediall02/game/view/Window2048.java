package fr.upem.ediall02.game.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import fr.upem.ediall02.game.controller.ActionIA;
import fr.upem.ediall02.game.controller.ActionMove;
import fr.upem.ediall02.game.controller.ActionStart;
import fr.upem.ediall02.game.controller.ActionUndo;
import fr.upem.ediall02.game.model.Direction;
import fr.upem.ediall02.game.model.Game2048Model;

/**
 * @class Window2048
 * @extends JFrame
 * @author eric
 * 
 * The main window
 *
 */
public class Window2048 extends JFrame {
    
    private Game2048Model model;
    
    /**
     * Default window size
     */
    private final static int wWindow = 800;
    private final static int hWindow = 800;
    
    private static final long serialVersionUID = 4019841629547464495L;
    
    /**
     * Panels of the window
     */
    private BoardPanel boardPanel;
    private ScorePanel scorePanel;
    private OptionMenu optionMenu;
    
    
    /**
     * Constructor
     * @param model
     */
    public Window2048(Game2048Model model) {
	this.model = model;
	this.setTitle("2048 Game");
	this.setSize(wWindow, hWindow);
	this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	this.addWindowListener(new WindowAdapter() {
	    @Override
	    public void windowClosing(WindowEvent e) {
		model.gameOver();
	    }
	});
	this.setLocationRelativeTo(null);
	this.setResizable(true);
	createContainers();
    }
    
    /**
     * Create window's containers
     */
    private void createContainers() {
	boardPanel = new BoardPanel(model);
	optionMenu = new OptionMenu(model, this);
	scorePanel = new ScorePanel(model);
	setKeys();
	
	JPanel mainContainer = new JPanel();
	JPanel settingsContainer = createSettingsPanel();
	mainContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
	mainContainer.setBackground(new Color(238,233,233));
	mainContainer.add(boardPanel);
	mainContainer.add(settingsContainer);
	mainContainer.add(createTextPanel());
	
	this.setJMenuBar(optionMenu);
	this.add(mainContainer);
	this.setLocationByPlatform(true);
	this.setVisible(true);
    }
    
    /**
     * Create settings panel
     * @return
     */
    private JPanel createSettingsPanel() {
	JPanel settings = new JPanel();
	settings.add(scorePanel);
	return settings;
    }
    
    /**
     * Draw infos for the game 
     * @return
     */
    private JPanel createTextPanel() {
	JPanel textPanel = new JPanel();
	textPanel.setLayout(new GridBagLayout());
	JLabel start = new JLabel();
	start.setText("Press [Enter] to Start or Restart the game");
	JLabel undo = new JLabel();
	undo.setText("Press [Z] to undo the last move");
	JLabel IA = new JLabel();
	IA.setText("Press [Space] to get hint from the AI solver");
	textPanel.add(start, ScorePanel.setPosition(0, 0, ScorePanel.regularInsets));
	textPanel.add(undo, ScorePanel.setPosition(0, 1, ScorePanel.regularInsets) );
	textPanel.add(IA, ScorePanel.setPosition(0, 2, ScorePanel.regularInsets));
	return textPanel;
    }
    
    /**
     * Set keys
     */
    private void setKeys() {
	InputMap input = boardPanel.getInputMap();
	
	if (!model.isSaveOption()) {
	    input.put(KeyStroke.getKeyStroke("Z"), "Undo");
	}
	input.put(KeyStroke.getKeyStroke("ENTER"), "Start");
	input.put(KeyStroke.getKeyStroke("SPACE"), "Hint");
	input.put(KeyStroke.getKeyStroke("UP"), "Up");
	input.put(KeyStroke.getKeyStroke("DOWN"), "Down");
	input.put(KeyStroke.getKeyStroke("LEFT"), "Left");
	input.put(KeyStroke.getKeyStroke("RIGHT"), "Right");
	
	if (!model.isSaveOption()) {
	    boardPanel.getActionMap().put("Undo", new ActionUndo(model, this));
	}
	boardPanel.getActionMap().put("Start", new ActionStart(model, this));
	boardPanel.getActionMap().put("Hint", new ActionIA(model, this));
	boardPanel.getActionMap().put("Up", new ActionMove(model, this, Direction.D_UP));
	boardPanel.getActionMap().put("Down", new ActionMove(model, this, Direction.D_DOWN));
	boardPanel.getActionMap().put("Left", new ActionMove(model, this, Direction.D_LEFT));
	boardPanel.getActionMap().put("Right", new ActionMove(model, this, Direction.D_RIGHT));
	
    }
    
    public void updateBoardContainer() {
	boardPanel.repaint();
    }
    
    public void updateScoreContainer() {
	scorePanel.update();
    }

}
