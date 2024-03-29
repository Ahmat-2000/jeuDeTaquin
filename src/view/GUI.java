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
 *
 * @author 21912949
 */
public class GUI extends JFrame {
    private JPanel jPanel1;
    Color screnBackgroundColor = new Color(204, 255, 204);
    Dimension screenDimension = new Dimension(400, 400);

    /**
     * Creates new form GUI
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
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       // Add java Look and Feel Nimbus
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        GamePlay model = new GamePlay(3,3);
        // model.printGrid();
        GridView gridView = new GridView(model);
        GUI screen = new GUI(model,gridView);
        screen.setVisible(true);
    }

}
