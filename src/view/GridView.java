package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.*;
import model.observerPattern.ModelListener;

/**
 * Classe GridView responsable de l'affichage de la grille du jeu de Taquin.
 * Elle implémente l'interface ModelListener pour pouvoir réagir aux changements du modèle de jeu.
 * Cette classe organise les boutons dans une grille qui représente l'état actuel du jeu.
 */
public class GridView extends JPanel implements ModelListener {
    /**
     * Grille de boutons représentant les cases du jeu de Taquin.
     */
    private JButton [][] gridBtn;

    /**
     * Référence au modèle de jeu, utilisée pour interagir avec la logique du jeu.
     */
    private GamePlay model;

    /**
     * Couleur utilisée pour les boutons actifs de la grille.
     */
    private Color greenColor = new Color(51, 204, 255);

    /**
     * Couleur utilisée pour le bouton représentant la case vide.
     */
    private Color blackColor = new Color(51, 51, 51);

    /**
     * Police de caractères utilisée pour le texte affiché sur les boutons de la grille.
     */
    private Font btnFont = new Font("Liberation Sans", Font.BOLD, 18);

    /**
     * Constructeur de GridView qui initialise la grille en fonction de l'état du modèle de jeu fourni.
     *
     * @param model Le modèle de jeu utilisé pour initialiser la vue.
     */
    public GridView(GamePlay model) {
        this.model = model;
        this.model.addModelListener(this);
        initComponents();
    }

    /**
     * Méthode appelée lorsque l'état du modèle de jeu change.
     * Met à jour la grille pour refléter l'état actuel du jeu.
     *
     * @param source L'objet ayant déclenché la notification de changement.
     */
    @Override
    public void somethingHasChanged(Object source) {
        for (int i = 0; i < model.getRow(); i++) {
            for (int j = 0; j < model.getRow(); j++) {
                gridBtn[i][j].setBackground(greenColor);
                gridBtn[i][j].setText(String.valueOf(model.getGrid()[i][j]));
                if(model.getGrid()[i][j] == 0){
                    gridBtn[i][j].setBackground(blackColor);
                    gridBtn[i][j].setText("");
                }
            }
        }
        this.repaint();
        this.revalidate();
    }

    /**
     * Configure les propriétés d'un bouton de la grille, y compris sa couleur, sa police, et son action lorsqu'il est cliqué.
     *
     * @param btn Le bouton à configurer.
     * @param n La valeur du bouton, 0 représentant la case vide.
     */
    public void btnSetting(JButton btn, int n) {
        btn.setBackground(greenColor);
        btn.setFont(btnFont); 
        btn.setText(String.valueOf(n));
        if (n == 0) {
            btn.setBackground(blackColor);
            btn.setText("");
        }
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleButton(e);
            }
        });
    }

    /**
     * Initialise les composants de la grille, créant un bouton pour chaque case de la grille de jeu.
     */
    private void initComponents() {
        gridBtn = new JButton[model.getRow()][model.getRow()];
        for (int i = 0; i < model.getRow(); i++) {
            for (int j = 0; j < model.getRow(); j++) {
                gridBtn[i][j] = new JButton();
                btnSetting(gridBtn[i][j], model.getGrid()[i][j]);
            }
        }
        setGroupLayout();       
    }

    /**
     * Configure le GroupLayout pour organiser les boutons de la grille.
     * Cette méthode organise les boutons dans une grille selon les dimensions du modèle de jeu.
     */
    public void setGroupLayout() {
        GroupLayout groupLayout = new GroupLayout(this);
        this.setLayout(groupLayout);

        // Horizontal Group
        ParallelGroup horizontalGroup = groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING);
        SequentialGroup horizontalSequentialGroup = groupLayout.createSequentialGroup().addGap(100);  // left gap
        ParallelGroup rowGroup = groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING);

        for (int i = 0; i < model.getRow(); i++) {
            SequentialGroup row = groupLayout.createSequentialGroup();
            for (int j = 0; j < model.getCol(); j++) {
                row.addComponent(gridBtn[i][j], GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE).addGap(20); // column gap
            }
            rowGroup.addGroup(row);
        }
        horizontalSequentialGroup.addGroup(rowGroup).addContainerGap(90, Short.MAX_VALUE); // right gap
        groupLayout.setHorizontalGroup(horizontalGroup.addGroup(horizontalSequentialGroup));

        // Vertical Group
        ParallelGroup verticalGroup = groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING);
        SequentialGroup verticalSequentialGroup = groupLayout.createSequentialGroup().addGap(80);  // top gap
    
        for (int i = 0; i < model.getRow(); i++) {
            // For each row, create a ParallelGroup that aligns components along the baseline
            ParallelGroup columnGroup = groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE);
            for (int j = 0; j < model.getCol(); j++) {
                columnGroup.addComponent(gridBtn[i][j], GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE);
            }
            verticalSequentialGroup.addGroup(columnGroup);
    
            // Add gap after each row, except the last one
            if (i < model.getRow() - 1) {
                verticalSequentialGroup.addGap(20); // row gap
            }
        }
    
        verticalSequentialGroup.addContainerGap(80, Short.MAX_VALUE); // bottom gap
        groupLayout.setVerticalGroup(verticalGroup.addGroup(verticalSequentialGroup));
    }
    

    /**
     * Gère les actions sur les boutons de la grille, en déclenchant des mouvements dans le jeu.
     *
     * @param e L'événement déclenché par le clic sur un bouton.
     */
    private void handleButton(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        for (int i = 0; i < model.getRow(); i++) {
            for (int j = 0; j < model.getRow(); j++) {
                if (source == gridBtn[i][j]) {
                    model.move(i, j);
                    if (model.gameOver()) {
                        int res = JOptionPane.showConfirmDialog(this, "Voulez-vous rejouer ?", "Le jeu est terminé", JOptionPane.YES_NO_OPTION);
                        if (res == JOptionPane.YES_OPTION) {
                            model.initialGrid();
                        } else {
                            System.exit(0);
                        }
                    }
                }
            }
        }
    }
}
