package fr.upem.ediall02.game.model;

/**
 * @enum Direction
 * @author eric
 * Enumeration which represents the different directions on the board
 *
 */
public enum Direction {
	D_UP(0, "N", "Up"),
	D_DOWN(1, "S", "Down"),
	D_LEFT(2, "W", "Left"),
	D_RIGHT(3, "E", "Right"),
	D_NONE(-1, "O", "None");
	
	private int code;
	private String letter;
	private String desc;
	
	private Direction(int code, String letter, String desc) {
	    this.code  = code;
	    this.letter = letter;
	    this.desc = desc; 
	}
	
	@Override
	public String toString() {
	    return desc + " ("  + letter + ": " +  code + ')';
	}
}
