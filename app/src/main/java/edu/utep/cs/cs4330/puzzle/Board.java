package edu.utep.cs.cs4330.puzzle;


import java.util.LinkedList;
import java.util.Random;

/**
 * A game board consisting of <code>size</code> * <code>size</code> places
 * where battleships can be placed. A place of the board is denoted
 * by a pair of 0-based indices (x, y), where x is a column index
 * and y is a row index. A place of the board can be shot at, resulting
 * in either a hit or miss.
 */
public class Board {
    private static int touchX;
    private static int touchY;
    private static int coorX;
    private static int coorY;
    protected static String [][] strGrid = new String [4][4];
    protected static int [] numGrid = new int [16];
    protected static int count = 0;

    protected static void setGrid(){
        for(int i = 0; i < numGrid.length;i++ ){
            numGrid[i] = i + 1;
        }
    }

    protected static void setBoard(){
        Random r = new Random();
        setGrid();
        int num; // = getRand();
        for(int i = 0; i < 4; i ++){
            num = getRand();
            for(int j = 0; j < 4; j++) {
                //num = getRand();
                if (num == 16)
                    strGrid[i][j] = " ";
                strGrid[i][j] = String.valueOf(num);
            }
        }
        if(!contains(-2)){
            numGrid[r.nextInt(16)] = -2;
            strGrid[r.nextInt(4)][r.nextInt(4)] = " ";
        }
    }

    protected static int getRand(){
        Random r = new Random();
        boolean flag = false;
        while(!flag){
            int num = r.nextInt(16);
            if(num == 16 && contains(num)) {
                int val = getPlace(num);
                numGrid[val] = -2;
                flag = true;
                return num;
            }

            else if(contains(num) && num != 0){
                int val = getPlace(num);
                numGrid[val] = -1;
                flag = true;
                return num;
            }
        }
        return 0;
    }

    protected static int getPlace(int num){
        for(int i = 0; i < numGrid.length; i++)
            if(num == numGrid[i])
                return i;
        return 0;
    }

    protected static boolean contains(int num){
        for(int i = 0; i < numGrid.length; i ++){
            if(numGrid[i] == num)
                return true;
        }
        return false;
    }

    protected static void getCoor(){
        for(int i = 0; i < 4; i ++){
            for(int j = 0; j < 4; j ++){
                if(strGrid[i][j] == " "){
                    coorX = i;
                    coorY = j;
                }
            }
        }
    }

    /**
     * Size of this board. This board has
     * <code>size*size </code> places.
     */
    private final int size;

    public static void setTouch(int x, int y){
        touchX = x;
        touchY = y;
    }

    protected static void updateBoard(){
        getCoor();
        if (touchX == coorX + 1 && touchY == coorY){      //East
            count ++;
            String temp = strGrid[touchX][touchY];
            strGrid[touchX][touchY] = " ";
            strGrid[coorX][coorY] = temp;
        }
        else if (touchX == coorX - 1 && touchY == coorY){ // West
            count ++;
            String temp = strGrid[touchX][touchY];
            strGrid[touchX][touchY] = " ";
            strGrid[coorX][coorY] = temp;
        }
        else if (touchX == coorX && touchY == coorY + 1){ //South
            count ++;
            String temp = strGrid[touchX][touchY];
            strGrid[touchX][touchY] = " ";
            strGrid[coorX][coorY] = temp;
        }
        else if (touchX == coorX && touchY == coorY - 1){ //North
            count ++;
            String temp = strGrid[touchX][touchY];
            strGrid[touchX][touchY] = " ";
            strGrid[coorX][coorY] = temp;
        }
    }

    /** Create a new board of the given size. */
    public Board(int size) {
        this.size = size;
    }

    /** Return the size of this board. */
    public int size() {
        return size;
    }

    // Suggestions:
    // 1. Consider using the Observer design pattern so that a client,
    //    say a BoardView, can observe changes on a board, e.g.,
    //    hitting a place, sinking a ship, and game over.
    //
    // 2. Introduce methods including the following:
    //    public boolean placeShip(Ship ship, int x, int y, boolean dir)
    //    public void hit(Place place)
    //    public Place at(int x, int y)
    //    public Place[] places()
    //    public int numOfShots()
    //    public boolean isGameOver()
    //    ...
}
