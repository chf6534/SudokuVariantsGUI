package sudokuvariantsgui;

import javax.swing.UIManager;

/**
 * Project: SudokuVariantsGUI
 * @author Jacob Coring
 */
public class SudokuVariantsGUI {
    
    public SudokuVariantsGUI() {
        SudokuVariantsView view = new SudokuVariantsView();
        SudokuVariants model = new SudokuVariants();
        new SudokuVariantsController(view,model);
    }
    
    public static void main(String[] args) {
        
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException e) {
        }

        new SudokuVariantsGUI();
    }
}
