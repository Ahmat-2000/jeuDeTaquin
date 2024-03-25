package model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class GamePlay{
    private int [][] grid;
    private int [][] goalGrid;
    private int row, col;
    public GamePlay(int n, int m){
        row = n; 
        col = m;
    }
    public void initialGrid(){
        grid = new int[row][col];
        goalGrid = new int[row][col];
        List<Integer> tempList = new ArrayList<>();

        int k = 1;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                goalGrid[i][j] = k;
                tempList.add(k); 
                k++;
            }
        }
        goalGrid[row - 1][col -1 ] = 0;
        tempList.set(tempList.size() - 1, 0); 

        Collections.shuffle(tempList); // Shuffle the list

        // Convert the shuffled list back into the grid
        int listIndex = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                grid[i][j] = tempList.get(listIndex);
                listIndex++;
            }
        }
    }
   
    public void printGrid(){
        for (int i = 0; i < 2*col + 1; i++) {
            System.out.print("-");
        }
        System.out.println();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print("|" + grid[i][j]);
            }
            System.out.println("|");
        }
        for (int i = 0; i < 2*col + 1; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
   
    public boolean gameOver(){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] != goalGrid[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
   
    public void move(int x, int y){
        if (y + 1 < row && grid[x][y + 1] == 0) {
            grid[x][y + 1] = grid[x][y];
            grid[x][y] = 0;
        }
        else if (y - 1 >=0 && grid[x][y-1] == 0) {
            grid[x][y - 1] = grid[x][y];
            grid[x][y] = 0;
        }
        else if (x + 1 < row && grid[x+1][y] == 0) {
            grid[x + 1][y] = grid[x][y];
            grid[x][y] = 0;
        }
        else if (x - 1 >=0 && grid[x][y] == 0) {
            grid[x-1][y] = grid[x][y];
            grid[x][y] = 0;
        }
        else{
            System.out.println("Impossible to move");
        }
    }

    public boolean handleIput(int x,int y){
        if (x < 0 || x > row -1) {
            System.out.println("\nThe row number should be between 0 and " +(row -1));
            return true;
        }
        if (y < 0 || y > col -1) {
            System.out.println("\nThe column number should be between 0 and " +(col -1));
            return true;
        }
        return false;
    }
    public void play(){

        Scanner input = new Scanner(System.in);
        while (! this.gameOver()) {
            try {
                this.printGrid();
                System.out.print("Give the row of the number between 0 and "+(row -1) + " : ");
                int x = input.nextInt();
                System.out.print("\nGive the column of the number between 0 and "+(col -1) + " : ");
                int y = input.nextInt();
                if (! this.handleIput(x, y)) {
                    this.move(x, y);
                }
            } catch (Exception e) {
                System.out.println("\nYou should enter just numbers not string or char");
            }
        }
        System.out.println("Bravo ! you won. See you ...");
        input.close();
    }

}