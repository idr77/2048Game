package fr.upem.ediall02.game.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class ScoreSaver {
    
    /**
     * File where the best score is stored
     */
    private static final String fileName = "data/conf.prop";
    
    private static final String highTile = "highTile";
    private static final String highScore = "highScore";
    private Game2048Model model;
    
    /**
     * Constructor
     * @param model
     */
    public ScoreSaver(Game2048Model model) {
	this.model = model;
    }
    
    /**
     * Load scores from file
     */
    public void loadScores() {
	Properties prop = new Properties();
	InputStream inStream;
	File f = new File(fileName);
	
	try {
	    inStream = new FileInputStream(f);
	    prop.load(inStream);
	    model.setHighScore(Integer.parseInt(prop.getProperty(highScore)));
	    model.setHighTile(Integer.parseInt(prop.getProperty(highTile)));   
	}
	catch (FileNotFoundException e) {
	    e.printStackTrace();
	}
	catch (IOException e) {
	    e.printStackTrace();
	}	
    }
    
    /**
     * Save best score into the file
     */
    public void saveScores() {
	Properties prop = new Properties();
	prop.setProperty(highScore,""+model.getHighScore());
	prop.setProperty(highTile, ""+model.getHighTile());
	
	OutputStream outStream = null;
	File f = new File(fileName);
	
	try {
	    outStream = new FileOutputStream(f);
	    prop.store(outStream, "2048 High Score");
	    outStream.flush();
	}
	catch (FileNotFoundException e) {
	    e.printStackTrace();
	}
	catch (IOException e) {
	    e.printStackTrace();
	}
	finally {
	    try {
		outStream.close();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
	
    }
}
