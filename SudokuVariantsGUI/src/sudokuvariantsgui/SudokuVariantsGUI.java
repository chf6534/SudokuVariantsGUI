package sudokuvariantsgui;

import javax.swing.UIManager;

/**
 * Project: SudokuVariantsGUI
 * @author Jacob Coring
 */
public class SudokuVariantsGUI {
    
    public SudokuVariantsGUI() {
        new SudokuVariantsController(new SudokuVariantsView(),new SudokuVariants());
    }
    
    public static void main(String[] args) {
        
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException ex) {
            //java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            //java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            //java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            //java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        new SudokuVariantsGUI();
    }
}
