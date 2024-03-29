package model;

public class DemoForTerminal {
    
    public static void main(String[] args) {
        GamePlay game = new GamePlay(3, 3);
        game.initialGrid();
        game.play();
    }
}
