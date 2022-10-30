package sudokuvariantsgui;

import java.util.ArrayList;

/**
 * Project: SudokuVariantsGUI
 * @author Jacob Coring
 */
public class SudokuVariants {
    // The number of variants playable
    //private static final int COUNT = Variants.values().length;
    // Max size of sudoku game
    private final int MAX_SIZE = 12;
    // Max difficulty
    //private static final int MAX_DIFFICULTY = Difficulties.values().length;
    // The structure of the game
    //private Grid game;
    private SudokuDB dB;
    private int size;
    private Game currentGame;
    private String currentGameName;
    private ArrayList<String> gameList = new ArrayList<String>();

    public SudokuVariants() {
        //game = new Grid(9,1);
        //game.generateGrid();
        dB = new SudokuDB(MAX_SIZE);
        updateGameList();
    }
    
    public int getMaxSize() {
        return MAX_SIZE;
    }
    
    public void setSize(int size) {
        this.size = size;
    }
    
    public Game getCurrentGame() {
        return currentGame;
    }
    
    public String[] getGameList() {
        String[] array = new String[gameList.size()];

        for (int i = 0; i < array.length; i++) {
            array[i] = gameList.get(i);
        }

        return array;
    }
    
    public void addGame(String game) {
        gameList.add(game);
    }
    
    private void updateGameList() {
        gameList = dB.getGameList();
    }
    
    public void createGame(int difficulty) {
        currentGame = new Game(size,difficulty);
        currentGameName = "";
    }
    
    public boolean gameExists() {
        return dB.gameExists(currentGameName);
    }
    
    public boolean updateGame() {
        return dB.updateGame(currentGame,currentGameName);
    }
    
    public boolean saveGame(String name) {
        if (gameList.contains(name)) {
            return false;
        } else if(dB.saveGame(currentGame,name)) {
            gameList.add(name);
            currentGameName = name;
            return true;
        } else {
            return false;
        }
    }
    
    public boolean loadGame(String name) {
        Game game = dB.loadGame(name);
        
        if (game != null) {
            currentGameName = name;
            currentGame = game;
            return true;
        } else {
            return false;
        }
    }
    
    public boolean deleteGame(String name) {
        if (dB.deleteGame(name)) {
            gameList.remove(name);
            return true;
        } else {
            return false;
        }
    }
}
