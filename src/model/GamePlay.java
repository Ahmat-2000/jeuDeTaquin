package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import model.observerPattern.AbstractListenableModel;

/**
 * Classe GamePlay représentant la logique du jeu de Taquin.
 * Elle gère la grille de jeu, les mouvements des tuiles et détermine si le jeu est terminé.
 */
public class GamePlay extends AbstractListenableModel {
    /**  Grille actuelle représentant l'état du jeu.*/
    private int[][] grid;

    /**  Grille cible à atteindre pour gagner.*/
    private int[][] goalGrid;

    /**  Nombre de lignes et de colonnes de la grille.*/
    private int row, col;

    /**
     * Constructeur initialisant le jeu avec une grille de taille spécifiée.
     * 
     * @param n Nombre de lignes de la grille.
     * @param m Nombre de colonnes de la grille.
     */
    public GamePlay(int n, int m) {
        super();
        row = n;
        col = m;
        initialGrid();
    }

    /**  Accesseurs pour obtenir le nombre de lignes et de colonnes.*/
    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    /**
     * Initialise la grille de jeu et la grille cible avec des valeurs de 1 à n*m-1, laissant une case vide.
     * Mélange ensuite la grille de jeu pour commencer le jeu.
     */
    public void initialGrid() {
        grid = new int[row][col];
        goalGrid = new int[row][col];
        List<Integer> tempList = new ArrayList<>();

        // Remplissage de la grille cible et de la liste temporaire.
        int k = 1;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                goalGrid[i][j] = k;
                tempList.add(k);
                k++;
            }
        }

        // Dernière case de la grille cible mise à 0 pour la case vide.
        goalGrid[row - 1][col - 1] = 0;
        tempList.set(tempList.size() - 1, 0);

        // Mélange de la liste temporaire et conversion en grille de jeu.
        Collections.shuffle(tempList);
        int listIndex = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                grid[i][j] = tempList.get(listIndex++);
            }
        }

        // Notification des observateurs du changement de la grille.
        this.fireChange();
    }

    /**
     * Affiche la grille de jeu actuelle dans la console.
     */
    public void printGrid() {
        // Bordure supérieure
        for (int i = 0; i < 2 * col + 1; i++) {
            System.out.print("-");
        }
        System.out.println();

        // Contenu de la grille
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print("|" + grid[i][j]);
            }
            System.out.println("|");
        }

        // Bordure inférieure
        for (int i = 0; i < 2 * col + 1; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     * Vérifie si la grille de jeu actuelle correspond à la grille cible.
     * 
     * @return true si la grille de jeu correspond à la grille cible, false sinon.
     */
    public boolean gameOver() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] != goalGrid[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Déplace la tuile dans la case vide si le mouvement est possible.
     * 
     * @param x Position de la ligne de la tuile à déplacer.
     * @param y Position de la colonne de la tuile à déplacer.
     */
    public void move(int x, int y) {
        // Conditions de mouvement pour chaque direction possible
        if (y + 1 < col && grid[x][y + 1] == 0) { // Mouvement vers la droite
            grid[x][y + 1] = grid[x][y];
            grid[x][y] = 0;
        } else if (y - 1 >= 0 && grid[x][y - 1] == 0) { // Mouvement vers la gauche
            grid[x][y - 1] = grid[x][y];
            grid[x][y] = 0;
        } else if (x + 1 < row && grid[x + 1][y] == 0) { // Mouvement vers le bas
            grid[x + 1][y] = grid[x][y];
            grid[x][y] = 0;
        } else if (x - 1 >= 0 && grid[x - 1][y] == 0) { // Mouvement vers le haut
            grid[x - 1][y] = grid[x][y];
            grid[x][y] = 0;
        } else {
            System.out.println("Impossible to move");
        }
        this.fireChange(); // Notification des observateurs après un mouvement
    }

    /**
     * Valide les entrées utilisateur pour les positions de ligne et de colonne.
     * 
     * @param x Position de la ligne saisie par l'utilisateur.
     * @param y Position de la colonne saisie par l'utilisateur.
     * @return true si l'entrée est invalide, false sinon.
     */
    public boolean handleInput(int x, int y) {
        if (x < 0 || x >= row) {
            System.out.println("\nThe row number should be between 0 and " + (row - 1));
            return true;
        }
        if (y < 0 || y >= col) {
            System.out.println("\nThe column number should be between 0 and " + (col - 1));
            return true;
        }
        return false;
    }

    /**
     * Boucle de jeu principale permettant à l'utilisateur de jouer dans la console.
     */
    public void play() {
        Scanner input = new Scanner(System.in);
        while (!this.gameOver()) {
            try {
                this.printGrid();
                System.out.print("Give the row of the number between 0 and " + (row - 1) + " : ");
                int x = input.nextInt();
                System.out.print("\nGive the column of the number between 0 and " + (col - 1) + " : ");
                int y = input.nextInt();
                if (!this.handleInput(x, y)) {
                    this.move(x, y);
                }
            } catch (Exception e) {
                System.out.println("\nYou should enter just numbers not string or char");
            }
        }
        System.out.println("Bravo! You won. See you ...");
        input.close();
    }

    /**
     * Obtient la grille de jeu actuelle.
     * 
     * @return La grille de jeu sous forme de tableau 2D.
     */
    public int[][] getGrid() {
        return grid;
    }
}
