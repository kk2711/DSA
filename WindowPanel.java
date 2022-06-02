import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import java.awt.GridLayout;


public class WindowPanel extends JPanel {
    
    final int maxRow = 15;
    final int maxCol = 10;
    final int nodeSize = 70;
    final int maxScreenWidth = nodeSize * maxRow;
    final int maxScreenHeight = nodeSize * maxCol;


    public WindowPanel(){
        this.setPreferredSize(new Dimension(maxScreenWidth, maxScreenHeight));
        this.setLayout(new GridLayout(maxRow, maxCol));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
    }


}
