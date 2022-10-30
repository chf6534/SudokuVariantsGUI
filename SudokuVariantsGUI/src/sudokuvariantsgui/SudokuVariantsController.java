package sudokuvariantsgui;

import java.awt.event.*;
import javax.swing.event.*;

/**
 * Project: SudokuVariantsGUI
 * @author Jacob Coring
 */
public class SudokuVariantsController {
    private SudokuVariantsView view;
    private SudokuVariants model;
    
    public SudokuVariantsController(SudokuVariantsView view,SudokuVariants model) {
        this.view = view;
        this.model = model;
        view.initCells(model.getMaxSize());
        view.updateGameList(model.getGameList());
        addMainMenuButtonActions();
        addVariantMenuButtonActions();
        addDifficultyMenuButtonActions();
        addGameMenuButtonActions();
        addSavedGamesMenuButtonActions();
    }
    
    // <editor-fold defaultstate="collapsed" desc="Add Menu Button Actions">
    
    // <editor-fold defaultstate="collapsed" desc="Main">   
    private void addMainMenuButtonActions() {
        view.getResumeGameButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resumeGameButtonActionPerformed();
            }
        });
        
        view.getNewGameButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newGameButtonActionPerformed();
            }
        });
        
        view.getSavedGamesButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                savedGamesButtonActionPerformed();
            }
        });
        
        view.getQuitButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quitButtonActionPerformed();
            }
        });
    }// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Variant">   
    private void addVariantMenuButtonActions() {
        view.getClassicButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                classicButtonActionPerformed();
            }
        });
        
        view.getMiniButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                miniButtonActionPerformed();
            }
        });
        
        view.getMegaButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                megaButtonActionPerformed();
            }
        });
        
        view.getVariantGoBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                variantGoBackButtonActionPerformed();
            }
        });
    }// </editor-fold> 
    
    // <editor-fold defaultstate="collapsed" desc="Difficulty">   
    private void addDifficultyMenuButtonActions() {
        view.getEasyButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                easyButtonActionPerformed();
            }
        });
        
        view.getNormalButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                normalButtonActionPerformed();
            }
        });
        
        view.getHardButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hardButtonActionPerformed();
            }
        });
        
        view.getDifficultyGoBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                difficultyGoBackButtonActionPerformed();
            }
        });
    }// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Game">   
    private void addGameMenuButtonActions() {
        view.getSaveGameButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveGameButtonActionPerformed();
            }
        });
        
        view.getReturnToMenuButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnToMenuButtonActionPerformed();
            }
        });
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Saved Games">   
    private void addSavedGamesMenuButtonActions() {
        view.getLoadGameButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadGameButtonActionPerformed();
            }
        });
        
        view.getDeleteGameButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteGameButtonActionPerformed();
            }
        });
        
        view.getSavedGamesGoBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                savedGamesGoBackButtonActionPerformed();
            }
        });
    }// </editor-fold>
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Menu Button Action Methods"> 
    
    // <editor-fold defaultstate="collapsed" desc="Main">   
    private void resumeGameButtonActionPerformed() {
        view.switchPanel(view.getGameMenu());
    }
    
    private void newGameButtonActionPerformed() {
        view.switchPanel(view.getVariantMenu());
    }
    
    private void savedGamesButtonActionPerformed() {
        view.switchPanel(view.getSavedGamesMenu());
    }
    
    private void quitButtonActionPerformed() {
        System.exit(0);
    }// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Variant">   
    private void classicButtonActionPerformed() {
        model.setSize(9);
        view.switchPanel(view.getDifficultyMenu());
    }
    
    private void miniButtonActionPerformed() {
        model.setSize(6);
        view.switchPanel(view.getDifficultyMenu());
    }
    
    private void megaButtonActionPerformed() {
        model.setSize(12);
        view.switchPanel(view.getDifficultyMenu());
    }
    
    private void variantGoBackButtonActionPerformed() {
        view.switchPanel(view.getMainMenu());
    }// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Difficulty">   
    private void easyButtonActionPerformed() {
        createGame(1);
    }
    
    private void normalButtonActionPerformed() {
        createGame(2);
    }
    
    private void hardButtonActionPerformed() {
        createGame(3);
    }
    
    private void difficultyGoBackButtonActionPerformed() {
        view.switchPanel(view.getVariantMenu());
    }// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Game">   
    private void saveGameButtonActionPerformed() {
        if (model.gameExists()) {
            view.gameUpdated(model.updateGame());
        } else {
            if (model.saveGame(view.enterName())) {
                view.gameSaved(true);
                view.updateGameList(model.getGameList());
            } else {
                view.gameSaved(false);
            }
        }
    }
    
    private void returnToMenuButtonActionPerformed() {
        view.getResumeGameButton().setEnabled(true);
        view.getSolvedText().setVisible(false);
        view.switchPanel(view.getMainMenu());
    }// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Saved Games">   
    private void loadGameButtonActionPerformed() {
        int index = view.getGames().getSelectedIndex();
        if (index < 0) {
            view.noGameSelected();
        } else {
            String name = model.getGameList()[index];
            
            if (model.loadGame(name)) {
                view.displayGame(model.getCurrentGame());
                createText(model.getCurrentGame().getSize());
                view.switchPanel(view.getGameMenu());
            } else {
                view.couldNotLoad();
            }
        }
    }
    
    private void deleteGameButtonActionPerformed() {
        int index = view.getGames().getSelectedIndex();
        if (index < 0) {
            view.noGameSelected();
        } else if (view.confirmDelete() == 0) {
            String name = model.getGameList()[index];

            if (model.deleteGame(name)) {
                view.updateGameList(model.getGameList());
                view.refresh();
            } else {
                view.couldNotDelete();
            }
        }
    }
    
    private void savedGamesGoBackButtonActionPerformed() {
        view.switchPanel(view.getMainMenu());
    }// </editor-fold>
    
    // </editor-fold>
    
    private void createGame(int difficulty) {
        model.createGame(difficulty);
        view.displayGame(model.getCurrentGame());
        createText(model.getCurrentGame().getSize());
        view.switchPanel(view.getGameMenu());
    }
    
    private void createText(int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                view.getCells().get(i).get(j).addCaretListener(new CaretListener() {
                    public void caretUpdate(CaretEvent evt) {
                        isSolved(size);
                    }
                });
            }
        }
    }
    
    private void isSolved(int size) {
        String text;
        int value;
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                text = view.getCells().get(i).get(j).getText();
                value = model.getCurrentGame().getCells().get(i).get(j).getSolutionValue();
                try {
                    if (Integer.parseInt(text) != value) {
                        return;
                    }
                } catch (NumberFormatException e) {     
                    return;
                }
            }
        }
        view.getSolvedText().setVisible(true);
        view.refresh();
    }
}
