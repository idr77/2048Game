package fr.upem.ediall02.game.model;

import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;

public class Tile {    
    
    private static final int W_TILE = 100;
    private static final int H_TILE = W_TILE;
    
    /**
     * Value of the case
     */
    private int value;
    
    
    /**
     * Constructor
     * @param value
     */
    public Tile(int value) {
	this.value = value;
    }
    
    public Tile(Tile tile) {
	this(tile.value);
    }
    
    /**
     * Get the value of the tile
     * @return The value of the tile
     */
    public int getValue() {
        return this.value;
    }
   
    
    @Override
    public String toString() {
	return "" + value;
    }
    
    /**
     * Get the size value for displaying 
     */
    public static int getSize() {
	return W_TILE;
    }
    
    
    /**
     * Increment a value when 2 tiles merged
     * @return 2*value
     */
    public int incrementValue() {
	return (value *= 2);
    }
    
    /**
     * Check if tile is empty
     */
    public boolean isEmpty() {
	return (value == 0 );
    }
    
    /**
     * Set a tile to an empty tile
     */
    public Tile setEmpty() {
	value = 0;
	return this;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + value;
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
	if (!(obj instanceof Tile)) {
	    return false;
	}
	Tile other = (Tile) obj;
	if (value != other.value) {
	    return false;
	}
	return true;
    }
    
    public Color getColor() {
	switch(value) {
	case 0:
	    return new Color(238,233,233);
	case 2:
	    return Color.WHITE;
	case 4:
	    return new Color(244,164,96);
	case 8:
	    return Color.ORANGE;
	case 16:
	    return new Color(0xFF7F24);
	case 32:
	    return Color.PINK;
	case 64:
	    return Color.RED;
	case 128:
	    return Color.YELLOW;
	case 256:
	    return new Color(255,236,139);
	case 512:
	    return new Color(255,246,143);
	case 1024:
	    return new Color(202,255,112);
	case 2048:
	    return new Color(255,185,15);
	case 4096:
	    return new Color(0xFFD700);
	case 8192:
	    return new Color(0xDDA0DD);
	case 16384:
	    return Color.GREEN;
	case 32768:
	    return Color.CYAN;
	case 65536:
	    return Color.BLUE;
	case 131072:
	    return Color.BLUE.darker();
	default:
	    throw new IllegalStateException("Invalid color");
	}
    }
    
    private void drawCenteredValue(Graphics g, int x, int y) {
	FontMetrics fm = g.getFontMetrics();
	int pX = (x*W_TILE) + (W_TILE - fm.stringWidth(Integer.toString(value))) / 2;
	int pY = (y*H_TILE) + (fm.getAscent() + (H_TILE - (fm.getAscent() + fm.getDescent())) / 2);
	g.drawString(Integer.toString(value), pX, pY);
    }
    
    private Font setFontValue() {
	Font font;
	try {
	    font = Font.createFont(Font.TRUETYPE_FONT, new File("font/ClearSans-Regular.ttf"));
	}
	catch(IOException | FontFormatException e) {
	   System.err.println("Error while loading font");
	   e.printStackTrace();
	   font = new Font("Monospaced", Font.PLAIN, 12);
	}
	
	return font.deriveFont(18f);
	
    }
    
    private Color getTextColor(Color background) {
	double luminance = 0.299 * background.getRed() + 0.587 * background.getGreen() + 0.114 * background.getBlue();
	return luminance < 128 ? Color.WHITE : Color.BLACK;
    }

    public void draw(Graphics g, int x, int y) {
	Graphics2D g2D = (Graphics2D) g;
	g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	
	g.setFont(setFontValue());
	Color backgroundColor = getColor();
	g.setColor(backgroundColor);
	g.fillRect(x*W_TILE, y*H_TILE, W_TILE, H_TILE);
	g.setColor(getTextColor(backgroundColor));
	if (this.value != 0) {
	    drawCenteredValue(g, x, y);
	}
    }
}
