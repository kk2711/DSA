import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import java.awt.GridLayout;


public class WindowPanel extends JPanel {
    
    final int maxRow = 10;
    final int maxCol = 15;
    final int nodeSize = 70;
    final int maxScreenWidth = nodeSize * maxCol;
    final int maxScreenHeight = nodeSize * maxRow;


    // NODES
    Node[][] node = new Node[maxCol][maxRow];
    Node startNode, goalNode, currentNode;


    public WindowPanel(){
        this.setPreferredSize(new Dimension(maxScreenWidth, maxScreenHeight));
        this.setLayout(new GridLayout(maxRow, maxCol));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);

        // PLACE NODES

        int col = 0;
        int row = 0;
       
        while(col < maxCol && row < maxRow){
            node[col][row] = new Node(col, row);
            this.add(node[col][row]);
            col++;

            if(col == maxCol){
                col = 0;
                 row++;
            }
        }

        // PLACE START AND GOAL NODE
        setStartNode(3, 6);
        setGoalNode(11, 5);

        // PLACE SOLID NODES
		setSolidNode(10, 2);
		setSolidNode(10, 3);
		setSolidNode(10, 4);
		setSolidNode(10, 5);
		setSolidNode(10, 6);
		setSolidNode(10, 7);
		setSolidNode(6, 2);
		setSolidNode(7, 2);
		setSolidNode(8, 2);
		setSolidNode(9, 2);
		setSolidNode(11, 7);
		
		setSolidNode(11, 8);
		setSolidNode(11, 9);
		setSolidNode(10, 2);
    }

   
private void setStartNode(int col, int row){
    node[col][row].setAsStartNode();
}

private void setGoalNode(int col, int row){
    node[col][row].setAsGoalNode();
}

private void setSolidNode(int col, int row){
    node[col][row].setAsSolidNode();
}
   

}


