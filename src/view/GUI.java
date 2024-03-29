package view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import model.*;

/**
 * Classe GUI représentant l'interface graphique du jeu de Taquin.
 * Elle hérite de JFrame pour manipuler les propriétés de la fenêtre principale de l'application,
 * telles que la taille, le titre, et le comportement à la fermeture.
 * Cette classe initialise et affiche les composants de l'interface utilisateur du jeu.
 */
public class GUI extends JFrame {

    /**
     * Le panneau principal de l'interface utilisateur où les éléments du jeu seront ajoutés.
     */
    private JPanel jPanel1;

    /**
     * Couleur de fond pour le panneau principal de l'interface utilisateur.
     */
    private Color screnBackgroundColor = new Color(204, 255, 204);

    /**
     * Dimensions de la fenêtre principale de l'interface utilisateur.
     */
    private Dimension screenDimension = new Dimension(400, 400);

    /**
     * Constructeur pour initialiser et configurer la fenêtre principale du jeu de Taquin.
     * 
     * @param model Le modèle de données du jeu de Taquin, gérant la logique du jeu.
     * @param jPanel Le panneau à intégrer dans la fenêtre principale, affichant les composants du jeu.
     */
    public GUI(GamePlay model, JPanel jPanel) {
        jPanel1 = jPanel;
        setTitle("Jeu de Taquin");
        setSize(screenDimension);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBackground(screnBackgroundColor); 
        getContentPane().add(jPanel1);      
        pack();
    } 
      /**
     * Point d'entrée principal de l'application.
     * Cette méthode configure le look-and-feel de l'interface utilisateur à Nimbus (si disponible),
     * crée le modèle du jeu et l'interface utilisateur, et affiche la fenêtre principale.
     * 
     * @param args Les arguments de la ligne de commande (non utilisés).
     */
    public static void main(String[] args) {
        // Configuration du look and feel Nimbus pour l'interface utilisateur
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        // Création du modèle du jeu et de l'interface utilisateur, et affichage de la fenêtre
        GamePlay model = new GamePlay(3, 3); // Création du modèle de jeu avec une grille 3x3
        GridView gridView = new GridView(model); 
        GUI screen = new GUI(model, gridView); 
        screen.setVisible(true); 
    }
}
