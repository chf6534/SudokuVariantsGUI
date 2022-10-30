package sudokuvariantsgui;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Project: SudokuVariantsGUI
 * @author Jacob Coring
 */
public class SudokuVariantsView extends JFrame {
    
    private JPanel mainMenu;
    private JPanel variantMenu;
    private JPanel difficultyMenu;
    private JPanel gameMenu;
    private JPanel savedGamesMenu;
   
    private JLabel mainMenuText;
    private JButton resumeGameButton;
    private JButton newGameButton;
    private JButton savedGamesButton;
    private JButton quitButton;
    
    private JLabel variantMenuText;
    private JButton classicButton;
    private JButton miniButton;
    private JButton megaButton;
    private JButton variantGoBackButton;
    
    private JLabel difficultyMenuText;
    private JButton easyButton;
    private JButton normalButton;
    private JButton hardButton;
    private JButton difficultyGoBackButton;
    
    private JLabel gameMenuText;
    private JPanel cellPanel;
    private ArrayList<ArrayList<JTextField>> cells;
    private JLabel solvedText;
    private JButton saveGameButton;
    private JButton returnToMenuButton;
    
    private JLabel savedGamesMenuText;
    private JScrollPane gameList;
    private JList<String> games;
    private JButton loadGameButton;
    private JButton deleteGameButton;
    private JButton savedGamesGoBackButton;
    
    public SudokuVariantsView() {
        initMenus();
        initMainApp();
    }
    
    // <editor-fold defaultstate="collapsed" desc="Initialise Main App">   
    private void initMainApp() {
        setTitle("Sudoku Variants");
        setBounds(
            Toolkit.getDefaultToolkit().getScreenSize().width/2 - 250,
            Toolkit.getDefaultToolkit().getScreenSize().height/2 - 300,
            500,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Initialise Menus">   
    private void initMenus() {
        initMainMenu();
        initVariantMenu();
        initDifficultyMenu();
        initGameMenu();
        initSavedGamesMenu();
    }

    private void initMainMenu() {
        mainMenu = new JPanel();
        mainMenuText = new JLabel("Sudoku Variants");
        resumeGameButton = new JButton("Resume Game");
        newGameButton = new JButton("New Game");
        savedGamesButton = new JButton("Saved Games");
        quitButton = new JButton("Quit");
        
        mainMenu.setLayout(null);
        mainMenuText.setFont(new Font("Comic Sans MS", 0, 36));
        resumeGameButton.setEnabled(false);
        
        mainMenuText.setBounds(100,60,274,51);
        resumeGameButton.setBounds(170,170,140,40);
        newGameButton.setBounds(170,260,140,40);
        savedGamesButton.setBounds(170,350,140,40);
        quitButton.setBounds(170,440,140,40);
        
        mainMenu.add(mainMenuText);
        mainMenu.add(resumeGameButton);
        mainMenu.add(newGameButton);
        mainMenu.add(savedGamesButton);
        mainMenu.add(quitButton);
        getContentPane().add(mainMenu);
    }

    private void initVariantMenu() {
        variantMenu = new JPanel();
        variantMenuText = new JLabel("Select Variant");
        classicButton = new JButton("Classic");
        miniButton = new JButton("Mini");
        megaButton = new JButton("Mega");
        variantGoBackButton = new JButton("Go Back");
        
        variantMenu.setLayout(null);
        variantMenuText.setFont(new Font("Comic Sans MS", 0, 36));
        
        variantMenuText.setBounds(120,60,244,51);
        classicButton.setBounds(170,170,140,40);
        miniButton.setBounds(170,260,140,40);
        megaButton.setBounds(170,350,140,40);
        variantGoBackButton.setBounds(170,440,140,40);
        
        variantMenu.add(variantMenuText);
        variantMenu.add(classicButton);
        variantMenu.add(miniButton);
        variantMenu.add(megaButton);
        variantMenu.add(variantGoBackButton);
    }

    private void initDifficultyMenu() {
        difficultyMenu = new JPanel();
        difficultyMenuText = new JLabel("Select Difficulty");
        easyButton = new JButton("Easy");
        normalButton = new JButton("Normal");
        hardButton = new JButton("Hard");
        difficultyGoBackButton = new JButton("Go Back");
        
        difficultyMenu.setLayout(null);
        difficultyMenuText.setFont(new Font("Comic Sans MS", 0, 36));
        
        difficultyMenuText.setBounds(100,60,288,51);
        easyButton.setBounds(170,170,140,40);
        normalButton.setBounds(170,260,140,40);
        hardButton.setBounds(170,350,140,40);
        difficultyGoBackButton.setBounds(170,440,140,40);
        
        difficultyMenu.add(difficultyMenuText);
        difficultyMenu.add(easyButton);
        difficultyMenu.add(normalButton);
        difficultyMenu.add(hardButton);
        difficultyMenu.add(difficultyGoBackButton);
    }

    private void initGameMenu() {
        gameMenu = new JPanel();
        gameMenuText = new JLabel("Sudoku");
        cellPanel = new JPanel();
        cells = new ArrayList();
        solvedText = new JLabel("Congratulations! You solved the puzzle.");
        saveGameButton = new JButton("Save Game");
        returnToMenuButton = new JButton("Return to Menu");
        
        gameMenu.setLayout(null);
        gameMenuText.setFont(new Font("Comic Sans MS", 0, 36));
        cellPanel.setLayout(null);
        cellPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        solvedText.setVisible(false);
        
        gameMenuText.setBounds(180,20,123,51);
        cellPanel.setBounds(60,80,360,360);
        saveGameButton.setBounds(60,480,140,40);
        returnToMenuButton.setBounds(280,480,140,40);
        solvedText.setBounds(120,450,260,21);
        
        gameMenu.add(gameMenuText);
        gameMenu.add(cellPanel);
        gameMenu.add(saveGameButton);
        gameMenu.add(returnToMenuButton);
        gameMenu.add(solvedText);
    }

    private void initSavedGamesMenu() {
        savedGamesMenu = new JPanel();
        savedGamesMenuText = new JLabel("Saved Games");
        games = new JList();
        gameList = new JScrollPane();
        loadGameButton = new JButton("Load Game");
        deleteGameButton = new JButton("Delete Game");
        savedGamesGoBackButton = new JButton("Go Back");
        
        savedGamesMenu.setLayout(null);
        savedGamesMenuText.setFont(new Font("Comic Sans MS", 0, 36));
        games.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        gameList.setViewportView(games);
        
        savedGamesMenuText.setBounds(130,60,244,51);
        gameList.setBounds(140,140,200,150);
        loadGameButton.setBounds(60,350,140,40);
        deleteGameButton.setBounds(280,350,140,40);
        savedGamesGoBackButton.setBounds(170,440,140,40);
        
        savedGamesMenu.add(savedGamesMenuText);
        savedGamesMenu.add(gameList);
        savedGamesMenu.add(loadGameButton);
        savedGamesMenu.add(deleteGameButton);
        savedGamesMenu.add(savedGamesGoBackButton);
    }// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Get Menu Methods">   
    public JPanel getMainMenu() {
        return mainMenu;
    }
    
    public JPanel getVariantMenu() {
        return variantMenu;
    }
    
    public JPanel getDifficultyMenu() {
        return difficultyMenu;
    }
    
    public JPanel getGameMenu() {
        return gameMenu;
    }
    
    public JPanel getSavedGamesMenu() {
        return savedGamesMenu;
    }// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Get Menu Component Methods">   
    
    // <editor-fold defaultstate="collapsed" desc="Main">   
    public JButton getResumeGameButton() {
        return resumeGameButton;
    }
    
    public JButton getNewGameButton() {
        return newGameButton;
    }
    
    public JButton getSavedGamesButton() {
        return savedGamesButton;
    }
    
    public JButton getQuitButton() {
        return quitButton;
    }// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Variant">   
    public JButton getClassicButton() {
        return classicButton;
    }
    
    public JButton getMiniButton() {
        return miniButton;
    }
    
    public JButton getMegaButton() {
        return megaButton;
    }
    
    public JButton getVariantGoBackButton() {
        return variantGoBackButton;
    }// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Difficulty">   
    public JButton getEasyButton() {
        return easyButton;
    }
    
    public JButton getNormalButton() {
        return normalButton;
    }
    
    public JButton getHardButton() {
        return hardButton;
    }
    
    public JButton getDifficultyGoBackButton() {
        return difficultyGoBackButton;
    }// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Game">   
    public JPanel getCellPanel() {
        return cellPanel;
    }
    
    public ArrayList<ArrayList<JTextField>> getCells() {
        return cells;
    }
    
    public JLabel getSolvedText() {
        return solvedText;
    }
    
    public JButton getSaveGameButton() {
        return saveGameButton;
    }
    
    public JButton getReturnToMenuButton() {
        return returnToMenuButton;
    }// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Saved Games">   
    public JList getGames() {
        return games;
    }
    
    public JButton getLoadGameButton() {
        return loadGameButton;
    }
    
    public JButton getDeleteGameButton() {
        return deleteGameButton;
    }
    
    public JButton getSavedGamesGoBackButton() {
        return savedGamesGoBackButton;
    }// </editor-fold>
    
    // </editor-fold>
    
    public void initCells(int size) {
        for (int i = 0; i < size; i++) {
            cells.add(new ArrayList<>());
            for (int j = 0; j < size; j++) {
                JTextField cell = new JTextField();
                cell.setHorizontalAlignment(JTextField.CENTER);
                cells.get(i).add(cell);
            }
        }
    }
    
    public void refresh() {
        repaint();
        revalidate();
    }
    
    public void switchPanel(JPanel panel) {
        getContentPane().remove(0);
        getContentPane().add(panel);
        repaint();
        revalidate();
    }
    
    public void displayGame(Game game) {
        int value, size = game.getSize(), sideLength = 360/size;
        Cell gameCell;
        JTextField cell;
                
        cellPanel.removeAll();
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                gameCell = game.getCells().get(i).get(j);
                cell = cells.get(i).get(j);
                
                if ((value = gameCell.getValue()) != 0) {
                    cell.setText(value+"");
                } else {
                    cell.setText("");
                }
                
                cell.setEditable(!gameCell.isLocked());
                setBorder(cell,i,j,size);
                cell.setFont(new Font("Segoe UI", 0, 216/size));
                cell.setBounds(sideLength*j, sideLength*i, sideLength, sideLength);
                cellPanel.add(cell);
            }
        }
    }
    
    private void setBorder(JTextField cell, int row, int column, int size) {
        int rowIndex, colIndex, subgridRows = (int)Math.floor(Math.sqrt(size));
        
        rowIndex = row % subgridRows;
        colIndex = column % (size/subgridRows);
        
        int top = 1, left = 1,bottom = 1, right = 1;
        
        if (row == 0) {
            top = 3;
        }
        if (column == 0) {
            left = 3;
        }
        if (subgridRows-rowIndex-1 == 0) {
            bottom = 3;
        }
        if (size/subgridRows-colIndex-1 == 0) {
            right = 3;
        }
        
        cell.setBorder(BorderFactory.createMatteBorder(top,left,bottom,right,new Color(0, 0, 0)));
    }
    
    public String enterName() {
        String name, dialog = "Enter name:";
        
        do {
            name = JOptionPane.showInputDialog(dialog);
            if (name == null) {
                return null;
            }
            dialog = "Invalid name. Please enter valid name:";
        } while (name.trim().equals("") || name.length() > 20);
        return name;
    }
    
    public void gameUpdated(boolean isUpdated) {
        String dialog;
        
        if (isUpdated) {
            dialog = "Game is updated.";
        } else {
            dialog = "Error updateing game, please try again";
        }
        
        JOptionPane.showMessageDialog(this,dialog);
    }
    
    public void gameSaved(boolean isSaved) {
        String dialog;
        
        if (isSaved) {
            dialog = "Game is saved.";
        } else {
            dialog = "A game with that name already exists!";
        }
        
        JOptionPane.showMessageDialog(this,dialog);
    }
    
    public void updateGameList(String[] gameList) {
        games.setListData(gameList);
    }
    
    public void noGameSelected() {
        JOptionPane.showMessageDialog(this,"No game is selected!");
    }
    
    public void couldNotLoad() {
        JOptionPane.showMessageDialog(this,"Could not load game!");
    }
    
    public void couldNotDelete() {
        JOptionPane.showMessageDialog(this,"Could not delete game!");
    }
    
    public int confirmDelete() {
        return JOptionPane.showConfirmDialog(this,"Are you sure you want to delete this game?");
    }
}
