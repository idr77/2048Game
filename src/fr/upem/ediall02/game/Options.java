package fr.upem.ediall02.game;

/**
 * @class Options
 * @author eric
 *
 */
public class Options {
    /**
     * Option array
     */
    private boolean[] options;
    
    /**
     * File array 
     */
    String[] files;
    
    /**
     * Constants which represents the different options of the game
     */
    public static final int OPT_SAVE = 0;
    public static final int OPT_REPLAY = 1;
    public static final int OPT_RANDOM = 2;
    public static final int OPT_BINARY = 3;
    public static final int OPT_IASOLVER = 4;
    public static final int OPT_IAMINMAX = 5;
    public static final int OPT_SIZE = 6;
    private static final int NB_OPT = 7;
    
    /**
     * Constants used to represents the files given in argument.
     */
    public static final int FILE_GAME = 0;
    public static final int FILE_BINARY = 1;
    private static final int NB_FILES = 2;
    
    /**
     * Size of the board.
     */
    private int size;
    private static final int DEFAULT_SIZE = 4;
    
    /**
     * Constructor
     */
    public Options() {
	options = new boolean[NB_OPT];
	for (int i = 0; i < NB_OPT ; i++) {
	    options[i] = false;
	}
	files = new String[NB_FILES];
	size = DEFAULT_SIZE;
    } 
    
    /**
     * Manages options following the arguments
     * @param args
     * @return
     */
    public Options manage(String[] args) {
	for (int i = 0; i < args.length ; i++) {
	    String arg = args[i];
	    if (arg.equals("-s")) {
		options[OPT_SAVE] = true;
		if (i+1 < args.length && args[i+1].charAt(0) != '-') {
		    files[FILE_GAME] = args[i+1];
		    i++;
		}
	    }
	    if (arg.equals("-r")) {
		options[OPT_REPLAY] = true;
		if (i+1 < args.length && args[i+1].charAt(0) != '-') {
		    files[FILE_GAME] = args[i+1];
		    i++;
		}
	    }
	    if (arg.equals("-a")) {
		options[OPT_REPLAY] = true;
		options[OPT_BINARY] = true;
		if (i+1 < args.length && args[i+1].charAt(0) != '-') {
		    files[FILE_BINARY] = args[i+1];
		    i++;
		}
		else {
		    throw new IllegalArgumentException();
		}
	    }
	    if (arg.equals("-n")) {
		options[OPT_RANDOM] = true;
		if (i+1 < args.length && args[i+1].charAt(0) != '-') {
		    files[FILE_GAME] = args[i+1];
		    i++;
		}
		else {
		    throw new IllegalArgumentException();
		}
	    }
	    if (arg.equals("-t")) {
		if (i+1 < args.length && args[i+1].charAt(0) != '-') {
		    size = Integer.parseInt(args[i+1]);
		    i++;
		}
		else {
		    size = DEFAULT_SIZE;
		}
	    }
	    if (arg.equals("-h")) {
		Game2048.help();
		System.exit(0);;
	    }
	    if (arg.equals("-k")) {
		options[OPT_IASOLVER] = true;
	    }
	    if (arg.equals("-m")) {
		options[OPT_IAMINMAX] = true;
	    }
	}
	return this;
    }
    
    /**
     * Checks whether an option is enabled or not.
     * @param numOpt
     * @return
     */
    public boolean isEnabled(int numOpt) {
	if (numOpt < OPT_SAVE || numOpt >= NB_OPT){
	    throw new IllegalArgumentException("Unrecognized option");
	}
	return options[numOpt];
    }
    
    /**
     * Get a file given in argument.
     * @param typeFile
     * @return
     */
    public String getFile(int typeFile) {
	if (typeFile < 0 && typeFile >= NB_FILES) {
	    throw new IllegalArgumentException();
	}
	return files[typeFile];
    }
    
    public int getGridSize() {
	return size;
    }
}
