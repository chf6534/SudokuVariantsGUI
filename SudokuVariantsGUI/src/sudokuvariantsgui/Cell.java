package sudokuvariantsgui;

/**
 * Project: SudokuVariants
 * @author Shayna Chapman-Moore
 */
public class Cell {

    private int value;
    private int solutionValue;
    private boolean locked;
    
    public Cell(int val,int solVal,boolean locked) {
        this.value = val;
        this.solutionValue = solVal;
        this.locked = locked;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getSolutionValue() {
        return solutionValue;
    }

    public void setSolutionValue(int solutionValue) {
        this.solutionValue = solutionValue;
    }

    public boolean isLocked() {
        return this.locked;
    }
    
    public void lock() {
        this.locked = true;
    }
    
    public void unlock() {
        this.locked = false;
    }
}
