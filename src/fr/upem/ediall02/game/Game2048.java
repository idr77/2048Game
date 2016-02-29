package fr.upem.ediall02.game;

import javax.swing.SwingUtilities;

import fr.upem.ediall02.game.model.Game2048Model;
import fr.upem.ediall02.game.view.Window2048;

/**
 * @class Game2048
 * @implements Runnable
 * @author eric
 *
 */
public class Game2048 implements Runnable{
    
    /**
     * Option manager
     */
    private static Options opt;
    
    /**
     * Constructor
     * @param opt
     */
    private Game2048(Options opt) {
	Game2048.opt = opt;
    }
    
    /**
     * Run the game
     */
    @Override
    public void run() {
	new Window2048(new Game2048Model(opt));   
    }
    
    /**
     * Prints the help of the game
     */
    public static void help() {
	System.out.println("-- Avaible options --");
	System.out.println("-- [arg]: Required argument");
	System.out.println("-- {arg}: Optional argument");
	System.out.println("* -s {file} Save the game into a file");
	System.out.println("* -r {file} Play or Replay a game from a file");
	System.out.println("* -a [file] Play a binary file or a game file");
	System.out.println("* -n [file] Create a random game file");
	System.out.println("* -t [size] Specify the size of the board");
	System.out.println("* -k Solve the game with the IA solver");
	System.out.println("* -m Solve the game with the MinMax/AlphaBeta algorithm");
	System.out.println("----------------------------------------------");
	System.out.println(". Press Enter to Start or Restart");
	System.out.println(". Press Z to Undo the last move");
	System.out.println("");
    }
    
    /**
     * Main
     * @param args
     */
    public static void main(String[] args) {
	/* Options management */
	opt = new Options();
	opt = opt.manage(args);	
	SwingUtilities.invokeLater(new Game2048(opt));
    }
}
