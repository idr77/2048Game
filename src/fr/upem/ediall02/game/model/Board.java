package fr.upem.ediall02.game.model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

/**
 * @class Board
 *
 */
public class Board {
    
    /**
     * Array of Tiles
     */
    private Tile[][] grid;
    
    /**
     * Array of Tiles which represents the ex-grid
     */
    private Tile[][] exGrid;
    
    /**
     * Size of the board
     */
    private final int size;
    
    /**
     * Current direction
     */
    private Direction dir = Direction.D_NONE;
    
    /**
     * Random generator
     */
    private final Random random;
    
    /**
     * Seed of random generator
     */
    private final long seed = 14071789;
    
    /**
     * Binary sequence 
     */
    private boolean hasBin;
    private byte[] binarySeq;
    private int indBin = 0;
    
    /**
     * Score on the board
     */
    private int score;
    
    /**
     * Points won/earned with the last action
     */
    private int pointsAction;
    
    /**
     * Best tile 
     */
    private int bestTile;
    
    /**
     * Random fields' coord selected
     */
    private int rX, rY;
    
    
    /**
     * Constructor
     */
    public Board() {
	this(4);
    }
    
    /**
     * Constructor with size
     * @param size
     */
    public Board(int size) {
	this.size = size;
	this.grid = new Tile[size][size];
	this.random = new Random(seed);
	this.score = 0;
	this.pointsAction = 0;
	this.bestTile = 2;
	this.hasBin = false;
	
	for (int y = 0; y < size ; y++) {
	    for(int x = 0; x < size ; x++) {
		grid[y][x] = new Tile(0);
	    }
	}
	
	exGrid = new Board(this).grid;
    }
    
    public Board(int size, byte[] binarySeq) {
	this(size);
	this.hasBin = true;
	this.binarySeq = binarySeq;
    }
    
    
    /**
     * Copy constructor
     * @param board
     */
    public Board(Board b) {
	size = b.size;
	grid = new Tile[b.size][b.size];
	random = b.random;
	score = b.score;
	pointsAction = b.pointsAction;
	bestTile = 2;
	binarySeq = b.binarySeq;
	hasBin = b.hasBin;
	indBin = b.indBin;
	
	for (int y = 0; y < size ; y++) {
	    for(int x = 0; x < size ; x++) {
		grid[y][x] = new Tile(b.grid[y][x]);
	    }
	}
	exGrid = b.exGrid;
    }
    
    /**
     * Get the direction
     * @return Direction
     */
    public Direction getDir() {
	return dir;
    }
    
    /**
     * Set the direction
     */
    public void setDirectionMove(Direction d) {
	dir = d;
    }
    
    /**
     * Get the score on this grid
     * @return score
     */
    public int getScore() {
	return score;
    }
    
    /**
     * Get the best tile on this grid
     */
    public int getBestTile() {
	return bestTile;
    }
    
    /**
     * Get the ID of the tile
     * @return the ID (y*size)+x
     */
    public int getIDTile(int x, int y) {
	return (y*size)+x;  
    }
    
    
    /**
     * Checks whether the board has a binary sequence for the cells
     */
    public boolean hasBinarySeq() {
	return hasBin;
    }
    /**
     * Get Empty Cells with ID
     * @return An ArrayList with empty cells ID
     */
    LinkedList<Integer> getEmptyTiles() {
	LinkedList<Integer> emptyTiles = new LinkedList<>();
	for (int y = 0; y < size ; y++) {
	    for (int x = 0; x < size ; x++) {
		if (grid[y][x].isEmpty()) {
		    emptyTiles.add(getIDTile(x,y));
		}
	    }
	}
	return emptyTiles;
    }
    
    /**
     * Size of the grid
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * Adds a random cell on the grid
     */
    public boolean addRandomTile() {
	if (isFull()) {
	    return false;
	}
	
	int value;
	if (hasBinarySeq() && indBin < binarySeq.length) {
	    value = (binarySeq[indBin] == 0)?2 : 4;
	}
	else {
	    value = (random.nextInt(100) > 10)? 2 : 4;
	}
	
	LinkedList<Integer> empty = getEmptyTiles();
	int randomCell = empty.get(random.nextInt(empty.size()));
	rX = randomCell % size;
	rY = randomCell / size;
	grid[rY][rX] = new Tile(value);
	return true;
    }
    
    /**
     * Adds a specified cell on the grid
     */
    public void addSpecifiedTile(int x, int y, int value) {
	    grid[y][x] = new Tile(value);
    }
    
    /**
     * Checks whether a move is possible on the board
     * @return true if a move is possible, false otherwise
     */
    public boolean isMovePossible() {
	LinkedList<Integer> emptyTiles = getEmptyTiles();
	if (emptyTiles.size() != 0) {
	    return true;
	}
	for (int y = 0 ; y < size ; y++) {
	    for (int x = 0 ; x < size ; x++) {
		if ( x+1 < size && grid[y][x].getValue() == grid[y][x+1].getValue()) {
		    return true;
		}
		if ( y+1 < size && grid[y][x].getValue() == grid[y+1][x].getValue()) {
		    return true;
		}
	    }
	}
	return false;
    }
    
    /**
     * Checks whether a move is possible on the current direction
     * @return true if a move is possible, false otherwise
     */
    public boolean isMovePossibleDir() {
	if (dir == Direction.D_NONE) {
	    return false;
	}
	rotateGrid();
	for (int y = 0; y < size ; y++) {
	    for (int x = size-1; x >= 0 ; x--) {
		if (x-1 > 0 && (grid[y][x].getValue() == grid[y][x-1].getValue() || grid[y][x-1].isEmpty())) {
		    reRotateGrid();
		    return true;
		}
	    }
	}
	reRotateGrid();
	return false;
    }
    
    /**
     * Checks whether the board is full
     * @return true if it's full, false otherwise
     */
    public boolean isFull() {
	for (int y = 0; y < size ; y++) {
	    for (int x = 0; x < size ; x++) {
		if (grid[y][x].isEmpty()) {
		    return false;
		}
	    }
	}
	return true;
    }
    
    /*********************************************************************************
     * 	==============		ALGORITHM FOR MOVING TILES		==============
     *********************************************************************************/
    
    
    /**
     * Rotate the grid
     * (to simplify the moving and merging algorithm)
     */
    private void rotateGrid() {
	if (dir == Direction.D_LEFT || dir == Direction.D_NONE) {
	    return;
	}
	Tile[][] rotatedGrid = new Tile[size][size];
	for (int y = 0; y < size ; y++) {
	    for (int x = 0; x < size ; x++) {
		if (dir == Direction.D_RIGHT) {
		    // rotate 180°
		    rotatedGrid[y][x] = grid[size-1-y][size-1-x];
		}
		
		else if (dir == Direction.D_UP) {
		    // rotate 270°
		    rotatedGrid[y][x] = grid[x][size-1-y];
		}
		
		else if (dir == Direction.D_DOWN) {
		    // rotate 90°
		    rotatedGrid[y][x] = grid[size-1-x][y];
		}
	    }
	}
	grid = rotatedGrid;
    }
    
    /**
     * Re-rotate the grid on the right position
     */
    private void reRotateGrid() {
	if (dir == Direction.D_LEFT) {
	    return;
	}
	Tile[][] newGrid = new Tile[size][size];
	for (int y = 0; y < size ; y++) {
	    for (int x = 0; x < size ; x++) {
		if (dir == Direction.D_RIGHT) {
		   // rotate 180°
		   newGrid[y][x] = grid[size-1-y][size-1-x];
		}
		
		else if (dir == Direction.D_UP) {
		    // rotate 270°
		    newGrid[y][x] = grid[size-1-x][y];
		}
		
		else if (dir == Direction.D_DOWN) {
		    // rotate 90°
		    newGrid[y][x] = grid[x][size-1-y];
		}
	    }
	}
	grid = newGrid;	
    }
    
    /**
     * Move, and merge the tiles on the grid
     * @return Points won
     */
    public int moveAndMergeTiles() {
	exGrid = new Board(this).grid;
	pointsAction = 0;
	
	// Rotate the grid for a treatment with left direction
	rotateGrid();
	
	// This algorithm is treated for left direction
	for (int y = 0; y < size ; y++) {
	    int lastX = 0;
	    for (int x = 1; x < size ; x++) {
		
		// If the square is empty, go to the next case
		if (grid[y][x].isEmpty()) {
		    continue;
		}
		
		int prevX;
		for (prevX = x - 1; prevX > lastX && grid[y][prevX].isEmpty() ; prevX--)
		    ;
		
		if (grid[y][prevX].isEmpty() ) {
		    grid[y][prevX] = new Tile(grid[y][x]);
		    grid[y][x].setEmpty();
		}
		
		else if (prevX+1 != x && grid[y][x].getValue() != grid[y][prevX].getValue()) {
		    grid[y][prevX + 1] = new Tile(grid[y][x]);
		    grid[y][x].setEmpty();
		}
		
		else if (grid[y][x].getValue() == grid[y][prevX].getValue()) {
		    int newValue = grid[y][prevX].incrementValue();
		    if (newValue > bestTile) {
			bestTile = newValue;
		    }
		    grid[y][x].setEmpty();
		    pointsAction += grid[y][prevX].getValue();
		    lastX = prevX + 1;
		}
	    }
	}
	
	// Re-rotate the grid on the correct direction
	reRotateGrid();
	
	score += pointsAction;
	return pointsAction;
    }
    
    /**
     * Undo the last move
     */
    public void undoLastMove() {
	score -= pointsAction;
	grid = exGrid;
	if (indBin > 0) {
	    indBin --;
	}
    }
    
    /**
     * Reset the board
     */
    public void reset() {
	for (int i = 0; i < size*size ; i++) {
	    grid[i/size][i%size] = new Tile(0);
	}
	score = 0;
	pointsAction = 0;
	bestTile = 2;
	indBin = 0;
    }
    
    
    
    /***********************************************************************************
     *	 	===================	   FILE		=======================	       *
     ***********************************************************************************/
    
    
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + bestTile;
	result = prime * result + Arrays.hashCode(binarySeq);
	result = prime * result + ((dir == null) ? 0 : dir.hashCode());
	result = prime * result + Arrays.hashCode(exGrid);
	result = prime * result + Arrays.hashCode(grid);
	result = prime * result + (hasBin ? 1231 : 1237);
	result = prime * result + indBin;
	result = prime * result + pointsAction;
	result = prime * result + rX;
	result = prime * result + rY;
	result = prime * result + score;
	result = prime * result + (int) (seed ^ (seed >>> 32));
	result = prime * result + size;
	return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (!(obj instanceof Board)) {
	    return false;
	}
	Board other = (Board) obj;
	if (bestTile != other.bestTile) {
	    return false;
	}
	if (!Arrays.equals(binarySeq, other.binarySeq)) {
	    return false;
	}
	if (dir != other.dir) {
	    return false;
	}
	if (!Arrays.deepEquals(exGrid, other.exGrid)) {
	    return false;
	}
	if (!Arrays.deepEquals(grid, other.grid)) {
	    return false;
	}
	if (hasBin != other.hasBin) {
	    return false;
	}
	if (indBin != other.indBin) {
	    return false;
	}
	if (pointsAction != other.pointsAction) {
	    return false;
	}
	if (rX != other.rX) {
	    return false;
	}
	if (rY != other.rY) {
	    return false;
	}
	if (score != other.score) {
	    return false;
	}
	if (seed != other.seed) {
	    return false;
	}
	if (size != other.size) {
	    return false;
	}
	return true;
    }

    /**
     * Get the Letter of the current Direction
     */
    private String getLetterDirection() {
	switch(dir){
	case D_UP:
	    return "N";
	case D_LEFT:
	    return "W";
	case D_RIGHT:
	    return "E";
	case D_DOWN:
	    return "S";  
	default:
	    return "";
	}
     }
    
    /**
     * Get the Direction following the letter
     */
    private Direction getDirection(String letter) {
	if (letter.equals("N")) {
	    return Direction.D_UP;
	}
	if (letter.equals("S")) {
	    return Direction.D_DOWN;
	}
	if (letter.equals("W")) {
	    return Direction.D_LEFT;
	}
	if (letter.equals("E")) {
	    return Direction.D_RIGHT;
	}
	if (letter.equals("")) {
	    return Direction.D_NONE;
	}
	throw new IllegalStateException();
    }
    
    
    
    /***
     * Write the state of Board at GameFile format
     */
    public String getCurrentBoardString() {
	return "" + grid[rY][rX].getValue() + rY + rX + getLetterDirection();
    }
    
    /**
     * Apply GameFile's String to the Board
     */
    public void applyGameString(String gameString) {
	int value = gameString.charAt(0) - '0';
	int y = gameString.charAt(1) - '0';
	int x = gameString.charAt(2) - '0';
	if (gameString.length() > 3) {
	    String dr = new String(""+gameString.charAt(3)); 
	    setDirectionMove(getDirection(dr));
	    moveAndMergeTiles();
	}
	else {
	    setDirectionMove(Direction.D_NONE);
	}
	addSpecifiedTile(x, y, value);
    }
    
    /**
     * Create a random GameFile's string
     */
    public String randomGameString() {
	addRandomTile();
	return getCurrentBoardString();
	
    }

    /***********************************************************************************
     *	 	===================	DISPLAY		=======================	       *
     ***********************************************************************************/
    
    /**
     * Display board on shell
     */
    @Override
    public String toString() {
	StringBuilder str = new StringBuilder();
	Arrays.asList(grid).stream().forEach(e -> {
	    Arrays.asList(e).stream().forEach(value -> str.append(value.getValue()).append(" "));
	    str.append("\n");
	});
	return str.toString();
    }
    
    /**
     * Get the size for displaying the board
     */
    public Dimension getDimension() {
	int sizeDim = Tile.getSize() * size;
	return new Dimension(sizeDim , sizeDim);
    }
     
    /**
     * Draw the Board on Window
     */
    public void draw(Graphics g) {
	g.setColor(Color.BLACK);
	Dimension dim = getDimension();
	g.fillRect(0, 0, dim.width , dim.height);
	for (int y = 0; y < size ; y++) {
	    for (int x = 0; x < size ; x++) {
		grid[y][x].draw(g, x , y);
	    }
	}
    }
    
}
