package sudokuvariantsgui;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Project: SudokuVariants
 * @author Jacob Coring
 */
public class Game {
    
    // Collection of collection of cells in each row
    private List<List<Cell>> rows;
    // Collection of collection of cells in each column
    private List<List<Cell>> columns;
    // Collection of collection of cells in each subgrid
    private List<List<Cell>> subGrids;
    // The number of cells in each row and column
    private final int SIZE;
    // For modifying the grid to match the difficulty
    private final int DIFFICULTY;
    // For random sudoku generation
    private final Random random;
    // For checking number of unique solutions when creating game
    private int solutions;
    
    public Game(int size, int difficulty) {
        DIFFICULTY = difficulty;
        SIZE = size;
        random = new Random();
        generateCells();
    }

    public int getSize() {
        return SIZE;
    }
    
    public int getDifficulty() {
        return DIFFICULTY;
    }
    
    // Return list of cells
    public List<List<Cell>> getCells() {
        return rows;
    }
    
    public void setCells(int[][] vals,int[][] solVals,boolean[][] isLocked) {
        
        Cell cell;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                cell = new Cell(vals[i][j],solVals[i][j],isLocked[i][j]);

                rows.get(i).set(j, cell);
                columns.get(j).set(i, cell);
                subGrids.get(getSubgridIndex(i, j)).set(getIndexInSubgrid(i, j), cell);
            }
        }
    }
    
    private void generateCells() {
        
        Cell cell;
        rows = new ArrayList<>(SIZE);
        columns = new ArrayList<>(SIZE);
        subGrids = new ArrayList<>(SIZE);
        
        for (int i = 0; i < SIZE; i++) {
            rows.add(new ArrayList<>(SIZE));
            columns.add(new ArrayList<>(SIZE));
            subGrids.add(new ArrayList<>(SIZE));
            for (int j = 0; j < SIZE; j++) {
                rows.get(i).add(null);
                columns.get(i).add(null);
                subGrids.get(i).add(null);
            }
        }
        
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                cell = new Cell(0,0,true);

                rows.get(i).set(j, cell);
                columns.get(j).set(i, cell);
                subGrids.get(getSubgridIndex(i, j)).set(getIndexInSubgrid(i, j), cell);
            }
        }
        
        generateGrid();
    }
    
    public void generateGrid() {
        createSolution();
        modifySolution();
    }
    
    private boolean createSolution() {
        boolean br = false;
        int curRow = 0;
        int curCol = 0;
        
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (rows.get(row).get(col).getSolutionValue() == 0) {
                    for (int val : shuffledValues()) {
                        if (checkList(rows.get(row),val,0)) {
                            if (checkList(columns.get(col),val,0)) {
                                if (checkList(subGrids.get(getSubgridIndex(row,col)),val,0)) {
                                    rows.get(row).get(col).setSolutionValue(val);
                                    columns.get(col).get(row).setSolutionValue(val);
                                    subGrids.get(getSubgridIndex(row,col)).get(getIndexInSubgrid(row,col)).setSolutionValue(val);

                                    if (checkGrid(0)) {
                                        return true;
                                    } else {
                                        if (createSolution()) {
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    curRow = row;
                    curCol = col;           
                    br = true;
                    break;
                }
            }
            if (br) {
                br = false;
                break;
            }
        }
        rows.get(curRow).get(curCol).setSolutionValue(0);
        return false;
    }
    
    private boolean checkGrid(int flag) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (flag == 0) {
                    if (rows.get(i).get(j).getSolutionValue() == 0) {
                        return false;
                    }
                } else {
                    if (rows.get(i).get(j).getValue() == 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    private void modifySolution() {
        int row, column, reps = DIFFICULTY*SIZE/2;
        
        // Set all values visible to user
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                rows.get(i).get(j).setValue(rows.get(i).get(j).getSolutionValue());
            }
        }
        
        while (reps > 0) {
            do {
                row = random.nextInt(SIZE);
                column = random.nextInt(SIZE);
            } while (rows.get(row).get(column).getValue() == 0);
            
            rows.get(row).get(column).setValue(0);
            rows.get(row).get(column).unlock();
            solutions = 0;
            checkIfUnique();
            
            if (solutions != 1) {
                rows.get(row).get(column).setValue(rows.get(row).get(column).getSolutionValue());
                rows.get(row).get(column).lock();
                reps--;
            }
        }
    }
    
    private boolean checkIfUnique() {
        boolean br = false;
        int currentRow = 0;
        int currentCol = 0;
        
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (rows.get(row).get(col).getValue() == 0) {
                    for (int val : IntStream.range(1,SIZE).toArray()) {
                        if (checkList(rows.get(row),val,1)) {
                            if (checkList(columns.get(col),val,1)) {
                                if (checkList(subGrids.get(getSubgridIndex(row,col)),val,1)) {
                                    rows.get(row).get(col).setValue(val);
                                    columns.get(col).get(row).setValue(val);
                                    subGrids.get(getSubgridIndex(row,col)).get(getIndexInSubgrid(row,col)).setValue(val);

                                    if (checkGrid(1)) {
                                        solutions++;
                                        break;
                                    } else {
                                        if (checkIfUnique()) {
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    currentRow = row;
                    currentCol = col;           
                    br = true;
                    break;
                }
            }
            if (br) {
                br = false;
                break;
            }
        }
        rows.get(currentRow).get(currentCol).setValue(0);
        return false;
    }
    
    private List<Integer> shuffledValues() {
        
        List<Integer> vals = new ArrayList<>(SIZE);
        for (int i = 1; i <= SIZE; i++) {
            vals.add(i);
        }
        Collections.shuffle(vals);
        
        return vals;
    }
    
    private boolean checkList(List<Cell> cells,int val,int flag) {
        
        for (Cell cell : cells) {
            if (flag == 0) {
                if (cell.getSolutionValue() == val) {
                    return false;
                }
            } else {
                if (cell.getValue() == val) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private int getSubgridIndex(int row,int col) {
        
        int index, rowIndex, colIndex, subgridCols = (int)Math.floor(Math.sqrt(SIZE));
        
        rowIndex = row/subgridCols;
        colIndex = col*subgridCols/SIZE;
        index = subgridCols*rowIndex + colIndex;
        
        return index;
    }
    
    private int getIndexInSubgrid(int row,int col) {
        
        int index, rowIndex, colIndex, subgridCols = (int)Math.floor(Math.sqrt(SIZE));
        
        rowIndex = row % subgridCols;
        colIndex = col % (SIZE/subgridCols);
        index = SIZE*rowIndex/subgridCols + colIndex;
        
        return index;
    }
}
