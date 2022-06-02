import java.awt.Color;

import javax.swing.JButton;

import javafx.scene.effect.ColorAdjust;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Node extends JButton implements ActionListener {
   int col;
   int row;
   int gCost;
   int hCosht;
   int fCost;
   boolean start;
   boolean goal;
   boolean solid;
   boolean open;
   boolean checked;

   public Node(int col, int row){
       this.col = col;
       this.row = row;

       this.setBackground(Color.WHITE);
       this.setForeground(Color.BLACK);
       addActionListener(this);
   }


   public void setAsStartNode(){
       this.setBackground(Color.BLUE);
       this.setForeground(Color.BLACK);
       this.setText("Start");
        start = true;
   }

   public void setAsGoalNode(){
       this.setBackground(Color.YELLOW);
       this.setForeground(Color.BLACK);
       this.setText("Goal");
       goal = true;
   }

   public void setAsSolidNode(){
       this.setBackground(Color.BLACK);
   }


@Override
public void actionPerformed(ActionEvent e) {
    this.setBackground(Color.ORANGE);
    
}

    
}   
