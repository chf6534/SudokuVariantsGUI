package sudokuvariantsgui;

import java.sql.*;
import java.util.*;

/**
 * Project: SudokuVariantsGUI
 * @author Jacob Coring
 */
public class SudokuDB {
    private Connection conn = null;
    private String url = "jdbc:derby:SudokuDB;create=true"; // url of the DB host
    private String dbUsername = "sudoku";
    private String dbPassword = "sudoku";
    private final String tableName = "SAVEDGAMES";
    private Statement statement;
    private String statementStr;
    private int maxSize;
    
    public SudokuDB(int maxSize) {
        this.maxSize = maxSize;
        dbSetup();
    }
    
    public void dbSetup() {
        try {
            conn = DriverManager.getConnection(url, dbUsername, dbPassword);
            statement = conn.createStatement();

            if (!checkTableExisting()) {
                makeNewTable();
            }
            statement.close();

        } catch (SQLException e) {
            System.out.println("error");
        }
    }

    private boolean checkTableExisting() {
        boolean flag = false;
        try {
            String[] types = {"TABLE"};
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rsDBMeta = dbmd.getTables(null, null, null, null);//types);
            //Statement dropStatement=null;
            while (rsDBMeta.next()) {
                String existingTable = rsDBMeta.getString("TABLE_NAME");
                if (existingTable.compareToIgnoreCase(tableName) == 0) {
                    flag = true;
                }
            }
            if (rsDBMeta != null) {
                rsDBMeta.close();
            }
        } catch (SQLException e) {
        }
        return flag;
    }
    
    private void makeNewTable() throws SQLException {
        statementStr = "CREATE TABLE "+tableName+" (GAMENAME VARCHAR(20), SIZE INT, DIFFICULTY INT";
        for (int i = 0; i < maxSize; i++) {
            statementStr+=", ROW"+i+" VARCHAR("+8*maxSize+")";
        }
        statementStr+=")";
        statement.executeUpdate(statementStr);
    }
    
    public ArrayList<String> getGameList() {
        ArrayList<String> list = new ArrayList<String>();
        
        try {
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT GAMENAME FROM SAVEDGAMES ");
            while (rs.next()) {
                list.add(rs.getString("GAMENAME"));
            }
        } catch (SQLException e) {
        }
        
        return list;
    }
    
    public boolean gameExists(String name) {
        boolean gameExists = false;
        
        try {
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(
                    "SELECT GAMENAME FROM SAVEDGAMES "+
                    "WHERE GAMENAME = '"+name+"'");
            if (rs.next()) {
                gameExists = true;
            }
        } catch (SQLException e) {
        }
        
        return gameExists;
    }
    
    private void createGameValues(List<Cell> row) {
        for (Cell cell : row) {
            statementStr += cell.getValue() + "-" + cell.getSolutionValue() + "-";

            if (cell.isLocked()) {
                statementStr += "] ";
            } else {
                statementStr += "[ ";
            }
        }
    }
    
    public boolean updateGame(Game game,String name) {
        boolean isUpdated = false;
        int size = game.getSize();
        List<List<Cell>> rows = game.getCells();
        try {
            statement = conn.createStatement();
            statementStr = "UPDATE SAVEDGAMES SET ";
            for (List<Cell> row : rows) {
                statementStr += "ROW" + rows.indexOf(row) + " = '";
                createGameValues(row);
                if (rows.indexOf(row) != size - 1) {
                    statementStr += "', ";
                } else {
                    statementStr += "' ";
                }
            }
            statementStr += "WHERE GAMENAME = '"+name+"'";
            statement.executeUpdate(statementStr);
            isUpdated = true;
        } catch (SQLException e) {
        }
        return isUpdated;
    }
    
    public boolean saveGame(Game game, String name) {
        if (name == null) {
            return false;
        }

        boolean gameSaved = false;
        int size = game.getSize(), difficulty = game.getDifficulty();
        List<List<Cell>> rows = game.getCells();
        try {
            statement = conn.createStatement();
            statementStr = "INSERT INTO SAVEDGAMES (GAMENAME,SIZE,DIFFICULTY";
            for (List<Cell> row : rows) {
                statementStr += ", ROW" + rows.indexOf(row);
            }
            statementStr += ") VALUES ('" + name + "'," + size + "," + difficulty;
            for (List<Cell> row : rows) {
                statementStr += ",'";
                createGameValues(row);
                statementStr += "'";
            }
            statementStr += ")";
            statement.executeUpdate(statementStr);
            gameSaved = true;
        } catch (SQLException e) {
        }
        return gameSaved;
    }
    
    public Game loadGame(String name) {
        int size, difficulty;
        int[][] values, solutionValues;
        boolean[][] locked;
        Game game = null;
        try {
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM SAVEDGAMES "
                    + "WHERE GAMENAME = '"+name+"'");
            if (rs.next()) {
                size = rs.getInt(2);
                difficulty = rs.getInt(3);
                values = new int[size][size];
                solutionValues = new int[size][size];
                locked = new boolean[size][size];
                
                StringTokenizer st;
                for (int i = 0; i < size; i++) {
                    st = new StringTokenizer(rs.getString(4+i), " -");
                    for (int j = 0; j < size; j++) {
                        values[i][j] = Integer.parseInt(st.nextToken());
                        solutionValues[i][j] = Integer.parseInt(st.nextToken());
                        if (st.nextToken().equals("]")) {
                            locked[i][j] = true;
                        } else {
                            locked[i][j] = false;
                        }
                    }
                }
                
                game = new Game(size,difficulty);
                game.setCells(values, solutionValues, locked);
            }
        } catch (SQLException e) {
        }
        
        return game;
    }
    
    public boolean deleteGame(String name) {
        boolean isDeleted = false;
        
        try {
            statement = conn.createStatement();
            statement.executeUpdate(
                    "DELETE FROM SAVEDGAMES "+
                    "WHERE GAMENAME = '"+name+"'");
            isDeleted = true;
        } catch (SQLException e) {
        }
        return isDeleted;
    }
}
