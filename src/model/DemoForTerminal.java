package model;

/**
 * Classe DemoForTerminal utilisée pour démarrer et exécuter le jeu de Taquin dans le terminal.
 * Cette classe sert de point d'entrée pour jouer au jeu en utilisant une interface de ligne de commande.
 */
public class DemoForTerminal {
    
    /**
     * Méthode principale pour démarrer le jeu.
     * 
     * @param args Arguments de la ligne de commande (non utilisés dans ce contexte).
     */
    public static void main(String[] args) {
        // Crée une nouvelle instance du jeu avec une grille 3x3.
        GamePlay game = new GamePlay(3, 3);

        // Initialise la grille de jeu avec des valeurs mélangées et une case vide.
        game.initialGrid();

        // Démarre la boucle de jeu permettant à l'utilisateur de jouer dans le terminal.
        game.play();
    }
}
