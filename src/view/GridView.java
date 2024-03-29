package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.*;
import model.observerPattern.ModelListener;

public class GridView extends JPanel implements ModelListener{
    private JButton [][] gridBtn;
    private GamePlay model;
    Color greenColor = new Color(51, 204, 255);
    Color blackColor = new Color(51, 51, 51);
    Font btnFont = new Font("Liberation Sans", 1, 18);
    public GridView(GamePlay model){
        this.model = model;
        this.model.addModelListener(this);
        initComponents();
    }
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
    public void btnSetting(JButton btn, int n){
        btn.setBackground(greenColor);
        btn.setFont(btnFont); 
        btn.setText(String.valueOf(n));
        if(n == 0){
            btn.setBackground(blackColor);
            btn.setText("");
        }
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HandleButton(e);
            }
        });
    }
    private void initComponents() {
        gridBtn = new JButton[model.getRow()][model.getRow()];
        for (int i = 0; i < model.getRow(); i++) {
            for (int j = 0; j < model.getRow(); j++) {
                gridBtn[i][j] = new JButton();
                btnSetting(gridBtn[i][j],model.getGrid()[i][j]);
            }
        }
        setGroupMayout();       
    }
    public void setGroupMayout(){
        GroupLayout groupLayout = new GroupLayout(this);
        this.setLayout(groupLayout);
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(groupLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(groupLayout.createSequentialGroup()
                        .addComponent(gridBtn[0][0], GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(gridBtn[0][1], GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(gridBtn[0][2], GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
                    .addGroup(groupLayout.createSequentialGroup()
                        .addComponent(gridBtn[1][0], GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(gridBtn[1][1], GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(gridBtn[1][2], GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
                    .addGroup(groupLayout.createSequentialGroup()
                        .addComponent(gridBtn[2][0], GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(gridBtn[2][1], GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(gridBtn[2][2], GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(98, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(groupLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(gridBtn[0][0], GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
                    .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(gridBtn[0][1], GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
                        .addComponent(gridBtn[0][2], GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)))
                .addGap(37, 37, 37)
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(gridBtn[1][0], GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
                    .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(gridBtn[1][1], GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
                        .addComponent(gridBtn[1][2], GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)))
                .addGap(37, 37, 37)
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(gridBtn[2][0], GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
                    .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(gridBtn[2][1], GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
                        .addComponent(gridBtn[2][2], GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(44, Short.MAX_VALUE))
        );
    }
    private void HandleButton(ActionEvent e) {
       JButton source = (JButton) e.getSource();
       for (int i = 0; i < model.getRow(); i++) {
            for (int j = 0; j < model.getRow(); j++) {
                if(source == gridBtn[i][j]){
                    model.move(i, j);
                    if(model.gameOver()){
                        int res = JOptionPane.showConfirmDialog(this,"Voulez-vous rejouer ?","Le jeu est terminé",JOptionPane.YES_NO_OPTION);
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
    }
}
