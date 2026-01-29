package fr.upem.ediall02.game.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.upem.ediall02.game.model.Game2048Model;

/**
 * @class ScorePanel
 * @extends JPanel
 * @author eric
 * 
 * Draw the score of the board
 *
 */
public class ScorePanel extends JPanel {
    private Game2048Model model;
    private static final long serialVersionUID = 4019841629545894495L;
    public static final Insets regularInsets = new Insets(10, 10, 0, 10);
    private static final Insets spaceInsets = new Insets(10, 10, 10, 10);
    private static final int sizeTextField = 10;
    
    private JTextField highScoreField;
    private JTextField scoreField;
    
    private JTextField highTileField;
    private JTextField tileField;
    
    /**
     * Constructor
     * @param model
     */
    public ScorePanel(Game2048Model model) {
	this.model = model;
	this.setLayout(new GridBagLayout());
	this.setBackground(new Color(238,233,233));
	setPanel();
	update();
    }
    
    /**
     * Set panel
     */
    private void setPanel() {
	int gridX = 0, gridY = 0;
	
	JLabel highScoreLabel = new JLabel("High score ");
	highScoreField = new JTextField(sizeTextField);
	highScoreField.setEditable(false);
	highScoreField.setBackground(new Color(248,233,223));
	highScoreField.setHorizontalAlignment(JTextField.CENTER);
	highScoreField.setBorder(null);
	highScoreLabel.setLabelFor(highScoreField);
	this.add(highScoreLabel, setPosition(gridX, gridY, regularInsets));
	this.add(highScoreField, setPosition(gridX + 1, gridY, regularInsets));
	
	JLabel scoreLabel = new JLabel("Current score ");
	scoreField = new JTextField(sizeTextField);
	scoreField.setEditable(false);
	scoreField.setBackground(new Color(248,233,223));
	scoreField.setHorizontalAlignment(JTextField.CENTER);
	scoreField.setBorder(null);
	scoreLabel.setLabelFor(scoreField);
	this.add(scoreLabel, setPosition(gridX, ++gridY, spaceInsets));
	this.add(scoreField, setPosition(gridX + 1, gridY, spaceInsets));
	
	JLabel highTileLabel = new JLabel("Best tile ");
	highTileField = new JTextField(sizeTextField);
	highTileField.setEditable(false);
	highTileField.setBackground(new Color(248,233,223));
	highTileField.setHorizontalAlignment(JTextField.CENTER);
	highTileField.setBorder(null);
	highTileLabel.setLabelFor(highTileField);
	this.add(highTileLabel, setPosition(gridX, ++gridY, regularInsets));
	this.add(highTileField, setPosition(gridX + 1, gridY, regularInsets));
	
	JLabel tileLabel = new JLabel("Current Best Tile ");
	tileField = new JTextField(sizeTextField);
	tileField.setEditable(false);
	tileField.setBackground(new Color(248,233,223));
	tileField.setHorizontalAlignment(JTextField.CENTER);
	tileField.setBorder(null);
	tileLabel.setLabelFor(tileField);
	this.add(tileLabel, setPosition(gridX, ++gridY, regularInsets));
	this.add(tileField, setPosition(gridX + 1, gridY, regularInsets));

    }
    
    /**
     * Set the position of the JLabel in the panel
     * @param gridX
     * @param gridY
     * @param insets
     * @return
     */
    static GridBagConstraints setPosition(int gridX, int gridY, Insets insets) {
	return new GridBagConstraints(gridX, gridY, 1, 1, 1.0, 1.0, 
			GridBagConstraints.LINE_START, 
			GridBagConstraints.HORIZONTAL, 
			insets, 0, 0);
    }
    
    /**
     * Update fields
     *      */
    public void update() {
	highScoreField.setText(Integer.toString(model.getHighScore()));
	scoreField.setText(Integer.toString(model.getBoard().getScore()));
	highTileField.setText(Integer.toString(model.getHighTile()));
	tileField.setText(Integer.toString(model.getBoard().getBestTile()));
    }
    
 

}
