package fr.upem.ediall02.game.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

/**
 * @class {@link GameFile}
 * @extends {@link File}
 * @author eric
 *
 */
public class GameFile extends File{

    private static final long serialVersionUID = 1L;
    private static final String format = "[2|4][0-9][0-9][N|W|E|S]?";
    private BufferedWriter writer;
    private BufferedReader reader;
    private int type;
    public static final int GAME_TYPE = 1;
    public static final int BINARY_TYPE = 2;

    /**
     * Constructor
     * @param pathname
     */
    public GameFile(String pathname) {
	super(pathname);
	openRessources();
    }
    
    /**
     * Get the File's type
     * @return file's type
     */
    public int getType() {
	try {
	    reader = new BufferedReader(new FileReader(this));
	    switch (reader.read())  {
	    case '0':
	    case '1':
		type = BINARY_TYPE;
		break;
	    case '2':
	    case '4':
	    case '#':
		type = GAME_TYPE;
		break;
	    default:
		type = 0;
	    }
	    return type;
	} 
	catch (IOException e) {
	    JOptionPane.showMessageDialog(null, "Erreur d'E/S", "Error", JOptionPane.ERROR_MESSAGE);
	    e.printStackTrace();
	    
	}
	finally {
	    try {
		reader.close();
	    } 
	    catch (IOException e) {
		e.printStackTrace();
		JOptionPane.showMessageDialog(null, "Erreur d'E/S", "Error", JOptionPane.ERROR_MESSAGE);

	    }
	}
	throw new IllegalStateException("Unrecognized format");
    }
    
    /**
     * Open ressources
     */
    private void openRessources() {
	try {
	    if (!this.exists()){
		this.createNewFile();
		type = GAME_TYPE;
		writeHeadFile();
	    }
	}
	catch (IOException e) {
	    e.printStackTrace();
	}
    }
    
    /**
     * Write the head of a game file.
     */
    private void writeHeadFile() {
	try {
	    	writer = new BufferedWriter(new FileWriter(this, true));
		writer.write("#2048 Game Save");
		writer.newLine();
		writer.write("#" + new Date());
		writer.newLine();
		writer.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
    
    /**
     * Close resources
     * @throws IOException
     */
    public void closeRessources() throws IOException {
	if (writer != null) {
	    writer.close();
	}
	if (reader != null) {
	    reader.close();
	}
    }
    

    /**
     * Add a string into the file
     * @param boardState
     */
    public void addBoardState(String boardState) {
	if (!isCorrectFormat(boardState)) {
	    JOptionPane.showMessageDialog(null, "Format de fichier invalide!", "Error", JOptionPane.ERROR_MESSAGE);
	    throw new IllegalStateException("Invalid format");
	}
	try {
	    if (countLines() < 2) {
		writeHeadFile();
	    }
	    writer = new BufferedWriter(new FileWriter(this, true));
	    writer.write(boardState);
	    writer.newLine();
	    writer.close();
	} catch (IOException e) {
	    JOptionPane.showMessageDialog(null, "Erreur d'E/S", "Error", JOptionPane.ERROR_MESSAGE);
	    e.printStackTrace();
	}
    }
    
    
    /**
     * Count lines in a file
     * @return the number of lines.
     * @throws IOException
     */
    private int countLines() throws IOException {
	reader = new BufferedReader(new FileReader(this));
	int nbLines = 0;
	for (nbLines = 0; reader.readLine() != null ; nbLines++)
	    ;
	reader.close();
	return nbLines;
    }
    
    /**
     * Checks whether the string is in the correct format
     */
    private boolean isCorrectFormat(String s) {
	Pattern p = Pattern.compile(format);
	return p.matcher(s).matches();
    }
    
    /**
     * Get all the lines of the file into a String[] object.
     * @return The String array of lines.
     * @throws IOException
     * @throws IllegalStateException
     */
    public String[] getLines() throws IOException, IllegalStateException {
	int nbLines = countLines();
	String[] lines= new String[nbLines];
	reader = new BufferedReader(new FileReader(this));
	
	if(!this.exists()) {
	    JOptionPane.showMessageDialog(null, "Le fichier n'existe pas !", "Error", JOptionPane.ERROR_MESSAGE);
	    throw new IllegalStateException("File doesn't exists");
	}
	
	for (int i = 0; i <  nbLines ; i++) {
	    String line = reader.readLine();
	    
	    if(line.equals("")){
		JOptionPane.showMessageDialog(null, "Le fichier n'a pas de lignes !", "Error", JOptionPane.ERROR_MESSAGE);
		throw new IllegalStateException("No lines");
	    }
	    while (line.charAt(0) == '#') {
		line = reader.readLine();
	    }
	    if (!isCorrectFormat(line)) {
		closeRessources();
		JOptionPane.showMessageDialog(null, "Format de fichier invalide !", "Error", JOptionPane.ERROR_MESSAGE);
        	throw new IllegalStateException("Unrecognized format");
	    }
        	lines[i] = line;
	}
	reader.close();
	return lines;
	
    }
    
    /**
     * Read a bit sequence (for Binary files)
     * @return The bit sequence.
     * @throws IOException 
     */
    public byte[] readBinarySequence() throws IOException {
	byte[] byteSequence = new byte[(int)length()];

	reader = new BufferedReader(new FileReader(this));
	for (int i = 0; i < byteSequence.length ; i++) {
	    byte bit = (byte) reader.read();
	    if (bit == '0' || bit == '1') {
		byteSequence[i] = bit;
	    }
	    else {
		JOptionPane.showMessageDialog(null, "Format de fichier invalide !", "Error", JOptionPane.ERROR_MESSAGE);
		reader.close();
		throw new IllegalStateException("Invalid format");
	    }
	}
	return byteSequence;
    }
    
    /**
     * Read a value sequence (for GameFile format)
     */
    public byte[] readValueSequence() throws IOException {
	int nbLines = countLines();
	byte[] byteSequence = new byte[nbLines];
	reader = new BufferedReader(new FileReader(this));
	
	if(!this.exists()) {
	    JOptionPane.showMessageDialog(null, "Le fichier n'existe pas !", "Error", JOptionPane.ERROR_MESSAGE);
	    throw new IllegalStateException("File doesn't exists");
	}
	
	for (int i = 0; i <  nbLines ; i++) {
	    String line = reader.readLine();
	    
	    if(line.equals("")){
		JOptionPane.showMessageDialog(null, "Le fichier n'a pas de lignes !", "Error", JOptionPane.ERROR_MESSAGE);
		throw new IllegalStateException("No lines");
	    }
	    while (line.charAt(0) == '#') {
		line = reader.readLine();
	    }
	    if (!isCorrectFormat(line)) {
		closeRessources();
		JOptionPane.showMessageDialog(null, "Format de fichier invalide !", "Error", JOptionPane.ERROR_MESSAGE);
        	throw new IllegalStateException("Unrecognized format");
	    }
	    
	    byteSequence[i] = ((byte)line.charAt(0));
	    byteSequence[i] -= '0';
	}
	reader.close();
	return byteSequence;
    }

    
    /**
     * Clear the file
     * @throws IOException 
     */
    public void clear() {
	try {
	    writer = new BufferedWriter(new FileWriter(this, false));
	    writer.write("");
	    writer.close();
	    openRessources();
	} catch (IOException e) {
	    e.printStackTrace();
	    javax.swing.JOptionPane.showMessageDialog(null, "Impossible de vider le fichier!", "Erreur", javax.swing.JOptionPane.ERROR_MESSAGE);
	}
    }
}
