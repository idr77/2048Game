package fr.upem.ediall02.game.model;

import java.util.LinkedList;

public class IASolver {
    /**
     * The model
     */
    private Game2048Model model;
    /**
     * A copy of the board (used to find the best direction)
     */
    private Board testBoard;
    
    /**
     * Constants which represent the player
     */
    private static final int PLAYER_USER = 0;
    private static final int PLAYER_COMPUTER = 1;
    
    /**
     * Constructor
     * @param model
     */
    public IASolver(Game2048Model model) {
	this.model = model;
	this.testBoard = new Board(model.getBoard());
    }
    
  
    /*
     * (Level 3)
     */
    
    /**
     * Get the best direction for the board
     * @return the best direction
     */
    public Direction getHint() {
	Direction bestDirection = Direction.D_LEFT;
	int bestScore = 0;
	for (Direction d : Direction.values()) {
	    if (d == Direction.D_NONE) {
		continue;
	    }
	    testBoard = new Board(model.getBoard());
	    testBoard.setDirectionMove(d);
	    testBoard.moveAndMergeTiles();

	    int currentScore = testBoard.getScore();
	    
	    if (currentScore > bestScore) {
		bestScore = currentScore;
		bestDirection = d;
	    }
	}
	if (testBoard.getEmptyTiles().stream().count() < ((testBoard.getSize() - 1))+1) {
	    if (bestDirection == Direction.D_LEFT) {
		bestDirection = Direction.D_RIGHT;
	    }
	    else if (bestDirection == Direction.D_DOWN) {
		bestDirection = Direction.D_UP;
	    }
	    else if (bestDirection == Direction.D_RIGHT) {
		bestDirection = Direction.D_LEFT;
	    }
	    else if (bestDirection == Direction.D_RIGHT) {
		bestDirection = Direction.D_LEFT;
	    }
	}
	return bestDirection;
    }
    
    /* Level 4 */
    
    /**
     * Class used to store data for the MiniMax algorithm
     * @author eric
     *
     */
    private class AlphaBetaData {
	private Direction direction;
	private int score;
	
	AlphaBetaData() {
	    direction = Direction.D_DOWN;
	    score = 0;
	}
	
	Direction getDirection() {
	    return direction;
	}
	
	void setDirection(Direction d) {
	    direction = d;
	}
	
	int getScore() {
	    return score;
	}
	
	void setScore(int s) {
	    score = s;
	}
	
	void print() {
	    System.out.println("Hint: " + direction + " Score : " + score );
	}
    }
    
    
    /**
     * Get the best direction using the Minimax algortihm with alpha-beta pruning.
     * @return The best direction
     */
    public Direction getHintAlphaBeta() {
	return minMaxAlphaBeta(7, Integer.MIN_VALUE, Integer.MAX_VALUE, PLAYER_USER).getDirection();
    }
    
    /**
     * Minimax algorithm with alpha-beta pruning.
     * @param depth
     * @param alpha
     * @param beta
     * @param player
     * @return
     */
    private AlphaBetaData minMaxAlphaBeta(int depth, int alpha, int beta, int player) {
	Direction bestDir = Direction.D_NONE;
	int bestScore;
	AlphaBetaData rslt = new AlphaBetaData();
	if (model.isGameOver()) {
	    if (model.getHighTile() >= 2048) {
		bestScore = Integer.MAX_VALUE;
	    }
	    else {
		bestScore = Math.min(model.getBoard().getScore(), 1);
	    }
	}
	
	else if(depth == 0) {
	    bestScore = heuristicScore((int) model.getBoard().getEmptyTiles().stream().count());
	}
	
	else {
	    if (player == PLAYER_USER) {
		for (Direction dir : Direction.values()) {
		    if (dir == Direction.D_NONE) {
			continue;
		    }
		    
		    testBoard = new Board(model.getBoard());
		    testBoard.setDirectionMove(dir);
		    int pts = testBoard.moveAndMergeTiles();
		    if (pts == 0 && testBoard.equals(model.getBoard())){
			continue;
		    }
		    rslt = minMaxAlphaBeta(depth-1, alpha, beta, PLAYER_COMPUTER);
		    
		    int currentScore = rslt.getScore();
		    if (currentScore > alpha) {
			alpha = currentScore;
			bestDir = dir;
		    }
		    if (beta <= alpha) {
			break;
		    } 
		}
		bestScore = alpha;
	    }
	    else {
		LinkedList<Integer> emptyTiles = testBoard.getEmptyTiles();
		int[] valuesAtStart = {2, 4};
		int y, x;
		int size = model.getBoard().getSize();
		
		for(int tile: emptyTiles) {
		    x = tile % size;
		    y = tile / size;
		    boolean alphaCutOff = false;
		    
		    for (int v : valuesAtStart) {
			testBoard = new Board(model.getBoard());
			testBoard.addSpecifiedTile(x, y, v);
			rslt = minMaxAlphaBeta(depth-1, alpha, beta, PLAYER_USER);
			int currentScore = rslt.getScore();
			if (currentScore < beta) {
				beta = currentScore;
			}
			alphaCutOff = beta <= alpha;
			if (alphaCutOff) {
			    break;  
			} 
		    }
		    if (alphaCutOff) {
			break;
		    }
		}
		bestScore = beta;
		
		if (emptyTiles.isEmpty()) {
		    bestScore = 0;
		}
	    }
	}
	
	rslt.setScore(bestScore);
	rslt.setDirection(bestDir);
	rslt.print();
	return rslt;
    }
    
    /**
     * Estimates a heuristic score.
     * @param nbEmptyCells
     * @return
     */
    private int heuristicScore(int nbEmptyCells) {
	return model.getBoard().getScore() * nbEmptyCells;
    }
}
