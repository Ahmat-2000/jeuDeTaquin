package model;

/**
 * Classe TaquinSolver fournissant une méthode pour déterminer si une configuration donnée d'un jeu de Taquin est résolvable.
 */
public class TaquinSolver {

    /**
     * Vérifie si une configuration donnée de la grille du jeu de Taquin est résolvable.
     * 
     * @param grille La grille du jeu de Taquin.
     * @return true si la configuration est résolvable, false sinon.
     */
    public static boolean estResolvable(int[][] grille) {
        int[] listeAplatie = aplatirLaGrille(grille); // Transforme la grille 2D en liste 1D
        int inversions = calculerInversions(listeAplatie); // Calcule le nombre d'inversions
        int n = grille.length; // Taille de la grille

        // Grille de taille impaire
        if (n % 2 != 0) {
            return inversions % 2 == 0;
        } else { // Grille de taille paire
            int positionCaseVide = trouverPositionCaseVideDepuisLeBas(grille);
            // Règles variant selon la position de la case vide pour les grilles paires
            return positionCaseVide % 2 == 0 ? inversions % 2 != 0 : inversions % 2 == 0;
        }
    }

    /**
     * Aplatit la grille 2D en une liste 1D en ignorant la case vide.
     * 
     * @param grille La grille 2D du jeu de Taquin.
     * @return La liste 1D des valeurs de la grille sans la case vide.
     */
    private static int[] aplatirLaGrille(int[][] grille) {
        int n = grille.length;
        int[] liste = new int[n * n - 1]; // La case vide n'est pas incluse
        int index = 0;

        for (int[] ligne : grille) {
            for (int val : ligne) {
                if (val != 0) { // Ignorer la case vide
                    liste[index++] = val;
                }
            }
        }
        return liste;
    }

    /**
     * Calcule le nombre total d'inversions dans la liste aplatie.
     * 
     * @param liste La liste aplatie des valeurs de la grille du Taquin.
     * @return Le nombre total d'inversions.
     */
    private static int calculerInversions(int[] liste) {
        int inversions = 0;

        for (int i = 0; i < liste.length; i++) {
            for (int j = i + 1; j < liste.length; j++) {
                if (liste[i] > liste[j]) {
                    inversions++; // Compter chaque inversion
                }
            }
        }
        return inversions;
    }

    /**
     * Trouve la position de la case vide depuis le bas de la grille.
     * 
     * @param grille La grille du jeu de Taquin.
     * @return La position de la ligne de la case vide, en partant du bas.
     */
    private static int trouverPositionCaseVideDepuisLeBas(int[][] grille) {
        int n = grille.length;

        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (grille[i][j] == 0) {
                    return n - i; // Calcul de la position de la case vide
                }
            }
        }
        return -1; // Ne devrait jamais se produire si la grille est valide
    }

    /**
     * Méthode principale pour tester la résolvabilité d'une configuration de grille donnée.
     * 
     * @param args Arguments de la ligne de commande (non utilisés).
     */
    public static void main(String[] args) {
        int[][] grille = {
            {1, 2, 3},
            {6, 7, 8}, // Exemple de grille avec la case vide
            {5, 4, 0}
        };

        System.out.println("La configuration est " + (estResolvable(grille) ? "résolvable" : "non résolvable"));
        int[][] grille2 = {
            {1, 2, 3},
            {6, 8, 7}, // Exemple de grille avec la case vide
            {5, 4, 0}
        };

        System.out.println("La configuration est " + (estResolvable(grille2) ? "résolvable" : "non résolvable"));
    }
}
