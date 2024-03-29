package controller;

/* import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import model.GamePlay; */

/**
 * Controller
 */
public class Controller /* implements ActionListener  */{
/*     private GamePlay model;
    private JButton [][] gridBtn;
    public Controller(GamePlay m, JButton [][] view){
        model = m;
        gridBtn = view;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        for (int i = 0; i < model.getRow(); i++) {
            for (int j = 0; j < model.getRow(); j++) {
                if(source == gridBtn[i][j]){
                    model.move(i, j);
                    if(model.gameOver()){
                        int res = JOptionPane.showConfirmDialog(null,"Voulez-vous rejouer ?","Le jeu est terminé",JOptionPane.YES_NO_OPTION);
                        if(res == JOptionPane.YES_OPTION)
                        {
                            model.initialGrid();
                        }else{
                            System.exit(1);
                        }
                    }
                }
            }
        }
    } */
}