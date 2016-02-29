package fr.upem.ediall02.game.model;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JOptionPane;

import fr.upem.ediall02.game.Options;

public class Game2048Model {
    
    /* Fields */
    
    /**
     * The board
     */
    private Board board;
    
    /**
     * The game file
     */
    private GameFile gameFile;
    
    /**
     * Default game file path
     */
    private static final String defaultGamePath = "data/game.sav";
    
    /**
     * ScoreSaver (stores the best score)
     */
    private ScoreSaver scores;
    
    /**
     * High score
     */
    private int highScore;
    
    /**
     * High tile
     */
    private int highTile;
    
    /**
     * Option manager
     */
    private Options opts;
    
    /**
     * Boolean which enables the user to move tiles or not
     */
    private boolean moves;
    private boolean hasPlayedAlready;
    
    /**
     * Constructor
     * @param opt
     */
    public Game2048Model(Options opt) {
	int size = opt.getGridSize();
	String gameFilePath = opt.getFile(Options.FILE_GAME);
	    
	if (size < 0 || size > 9) {
	    throw new IllegalArgumentException("Invalid size");
	}
	
	scores = new ScoreSaver(this);
	scores.loadScores();
	
	if (opt.isEnabled(Options.OPT_BINARY)){
	    if (opt.getFile(Options.FILE_BINARY) == null) {
		throw new IllegalArgumentException("Invalid file");
	    }
	    gameFile = new GameFile(opt.getFile(Options.FILE_BINARY));
	    board = new Board(size, getBinaryFile());
	}
	
	else {
	    board = new Board(size);
	}
	if (opt.isEnabled(Options.OPT_SAVE)|| opt.isEnabled(Options.OPT_REPLAY) || opt.isEnabled(Options.OPT_RANDOM)) {
        	if (gameFilePath == null) {
        	    gameFilePath = defaultGamePath;
        	}
        	gameFile = new GameFile(gameFilePath);
	}
	opts = opt;
	moves = false;
	hasPlayedAlready = false;
    }
    
    /** 
     * Checks whether moves are enabled or not.
     * @return
     */
    public boolean isMoveEnabled() {
	return moves == true;
    }
    
    /**
     * Checks whether the file loaded was played or not (replay)
     * @return
     */
    public boolean hasPlayed() {
	return hasPlayedAlready;
    }
    
    /**
     * Enables the user to move the tiles
     */
    public void enableMoves() {
	moves = true;
    }
    
    /**
     * Disable moves
     */
    public void disableMoves() {
	moves = false;
    }
    
    /**
     * Get options
     * @return
     */
    public Options getOpt() {
	return opts;
    }
    
    /**
     * Get the board
     * @return
     */
    public Board getBoard() {
	return board;
    }
    
    /**
     * Checks if the save option is enabled
     * @return
     */
    public boolean isSaveOption() {
	return opts.isEnabled(Options.OPT_SAVE);
    }
    
    /**
     * Checks if the replay option is enabled
     * @return
     */
    public boolean isReplay() {
	return opts.isEnabled(Options.OPT_REPLAY);
    }
    
    /**
     * Checks if the random option is enabled.
     * @return
     */
    public boolean isRandom() {
	return opts.isEnabled(Options.OPT_RANDOM);
    }
    
    /**
     * Checks if the game is over or not
     * @return
     */
    public boolean isGameOver() {
	return (board.isFull() && !board.isMovePossible());
    }
    
    /**
     * Get the high tile
     * @return
     */
    public int getHighTile() {
	return highTile;
    }
    
    /**
     * Set the high tile (for controller)
     * @param highTile
     */
    public void setHighTile(int highTile) {
	this.highTile = highTile;
    }
    
    /**
     * Get the high score
     * @return
     */
    public int getHighScore() {
	return highScore;
    }
    
    /**
     * Set the high score (for controller)
     * @param highScore
     */
    public void setHighScore(int highScore) {
	this.highScore = highScore;
    }
    
    /**
     * Update the scores
     */
    public void updateHighScore() {
	if (board.getScore() > highScore) {
	    highScore = board.getScore();
	}
	if (board.getBestTile() > highTile) {
	    highTile = board.getBestTile();
	}
	scores.saveScores();
    }
    
    /**
     * Write the board in a file at save format.
     * @throws IOException
     */
    public void writeBoardInFile() throws IOException {
	if (isSaveOption()) {
	    gameFile.addBoardState(board.getCurrentBoardString());
	}
    }
    
    /**
     * Get the file's type
     * @return
     */
    public int getGameFileType() {
	return gameFile.getType();
    }
    
    /**
     * Get the file into a String[]
     * @return
     */
    private String[] getGameFile() {
	try {
	    return gameFile.getLines();
	} 
	catch (IllegalStateException | IOException e) {
		JOptionPane.showMessageDialog(null, "Fichier introuvable ou format de fichier incorrect!", "Error", JOptionPane.ERROR_MESSAGE);
		e.printStackTrace();
	}
	finally {
	    try {
		gameFile.closeRessources();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
	JOptionPane.showMessageDialog(null, "Echec de la récupération de la sauvegarde", "Error", JOptionPane.ERROR_MESSAGE);
	throw new IllegalStateException();
    }
    
    /**
     * Plays a file
     */
    public void playGameFile() {
	if (isReplay() && !hasPlayedAlready) {
	    String[] states = getGameFile();
	    Arrays.asList(states).stream().forEach(s -> board.applyGameString(s));
	    if (isGameOver()) {
		JOptionPane.showMessageDialog(null, "Erreur: le jeu est terminé !", "Game Over", JOptionPane.ERROR_MESSAGE);
		gameOver();
	    }
	    hasPlayedAlready = true;
	    enableMoves();
	}
    }
    
    /**
     * Gets a binary file
     * @return
     */
    byte[] getBinaryFile() {
	try {
	    return gameFile.readBinarySequence();
	}
	catch(IOException e) {
	    JOptionPane.showMessageDialog(null, "Fichier introuvable ou format de fichier incorrect!", "Error", JOptionPane.ERROR_MESSAGE);
	    e.printStackTrace();
	}
	finally {
	    try {
		gameFile.closeRessources();
	    }
	    catch (IOException e) {
		e.printStackTrace();
	    }
	}
	JOptionPane.showMessageDialog(null, "Echec de la récupération du fichier binaire.", "Error", JOptionPane.ERROR_MESSAGE);
	throw new IllegalStateException();
    }
    
    /**
     * Create a random game save.
     */
    public void createRandomFile() {
	if (isRandom()) {
	    Random rd = new Random();
	    int nbMoves = rd.nextInt(8192);
	    for (int i = 0; i < nbMoves ; i++) {
		System.out.println(getBoard().randomGameString());
		gameFile.addBoardState(getBoard().randomGameString());
		if (i > 2) {
		    int d = rd.nextInt(4);
		    Direction dir;
		    do {
			dir = Direction.values()[d];
		    }
		    while(dir == Direction.D_NONE);
		    board.setDirectionMove(dir);
		    board.moveAndMergeTiles();
		}
	    }
	}
    }
    
    public void gameOver() {
	System.exit(0);
    }

    public void clear() {
	board.reset();
	if (gameFile != null) {
	    gameFile.clear();
	}
    }
}
