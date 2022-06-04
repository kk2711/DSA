import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.ArrayList;




public class WindowPanel extends JPanel {
    
    final int maxRow = 10;
    final int maxCol = 15;
    final int nodeSize = 70;
    final int maxScreenWidth = nodeSize * maxCol;
    final int maxScreenHeight = nodeSize * maxRow;
    int step = 0;

    // NODES
    Node[][] node = new Node[maxCol][maxRow];
    Node startNode, goalNode, currentNode;
    ArrayList<Node> openList = new ArrayList<>();
    ArrayList<Node> checkedList = new ArrayList<>();


    // OTHERS
    boolean goalReached = false;
    


    public WindowPanel(){
        this.setPreferredSize(new Dimension(maxScreenWidth, maxScreenHeight));
        this.setLayout(new GridLayout(maxRow, maxCol));
        this.setBackground(Color.BLACK);
        this.addKeyListener(new KeyHandler(this));
    
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

        // SET COST
        setCostOnNode();
    }

   
private void setStartNode(int col, int row){
    node[col][row].setAsStartNode();
   startNode = node[col][row];
   currentNode = startNode;
}

private void setGoalNode(int col, int row){
    node[col][row].setAsGoalNode();
    goalNode = node[col][row];
}

private void setSolidNode(int col, int row){
    node[col][row].setAsSolidNode();
    
}

private void setCostOnNode(){

    int col = 0;
    int row = 0;
    
    while(col < maxCol && row < maxRow){
        getCost(node[col][row]);
        col++;
        
        if(col == maxCol){
            col = 0;
            row++;
        }
    }
}
   
private void getCost(Node node){

    // GET G COST (Distance from start node)
    int xDistance = Math.abs(node.row - startNode.row);
    int yDistance = Math.abs(node.col - startNode.col); 
    node.gCost = xDistance + yDistance;

    // GET H COST (Distance from goal node)
    xDistance = Math.abs(node.row - goalNode.row);
    yDistance = Math.abs(node.col - goalNode.col);
    node.hCost = xDistance + yDistance;

    // GET F COST (Sum of gCost and hCost)

    node.fCost = node.gCost + node.hCost;

    // DISPLAY COST
    if(node != startNode && node != goalNode){
        node.setText("<html>F:" +node.fCost + "<br>G:" +node.gCost +"</html>");
    }
}

public void search(){
    if(goalReached == false && step < 300){
        int col = currentNode.col;
        int row = currentNode.row;

        currentNode.setAsChecked();
        checkedList.add(currentNode);
        openList.remove(currentNode);

       	// OPEN THE UP NODE
			if(row - 1 >= 0){
				openNode(node[col][row-1]);
			}
			
			// OPEN THE LEFT NODE
			if(col - 1 >= 0){
				openNode(node[col-1][row]);
			}
			
			//OPEN THE DOWN NODE
			if(row + 1 < maxRow){
				openNode(node[col][row+1]);
			}
			
			//OPEN THE RIGHT NODE
			if(col + 1 < maxCol){
				openNode(node[col+1][row]);
			}
			

        // FINT THE BEST NODE
        int bestNodeIndex = 0;
        int bestNodefCost =  999;

        for(int i = 0; i < openList.size(); i++){

            // Check if this node's fcost is better
            if(openList.get(i).fCost < bestNodefCost){
                bestNodeIndex = i;
                bestNodefCost = openList.get(i).fCost;
            }
            // If F cost is equal, check the G cost
            else if(openList.get(i).fCost == bestNodefCost){
                if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost){
                    bestNodeIndex = i;
                }
            }
        }

        // After the loop, we get The best node whick is our next step
        currentNode = openList.get(bestNodeIndex);

        if(currentNode == goalNode){
            goalReached = true;
        }
    }

    step++;
}


public void autoSearch(){
    while(goalReached == false && step < 300){
        int col = currentNode.col;
        int row = currentNode.row;

        currentNode.setAsChecked();
        checkedList.add(currentNode);
        openList.remove(currentNode);

       	// OPEN THE UP NODE
			if(row - 1 >= 0){
				openNode(node[col][row-1]);
			}
			
			// OPEN THE LEFT NODE
			if(col - 1 >= 0){
				openNode(node[col-1][row]);
			}
			
			//OPEN THE DOWN NODE
			if(row + 1 < maxRow){
				openNode(node[col][row+1]);
			}
			
			//OPEN THE RIGHT NODE
			if(col + 1 < maxCol){
				openNode(node[col+1][row]);
			}
			

        // FINT THE BEST NODE
        int bestNodeIndex = 0;
        int bestNodefCost =  999;

        for(int i = 0; i < openList.size(); i++){

            // Check if this node's fcost is better
            if(openList.get(i).fCost < bestNodefCost){
                bestNodeIndex = i;
                bestNodefCost = openList.get(i).fCost;
            }
            // If F cost is equal, check the G cost
            else if(openList.get(i).fCost == bestNodefCost){
                if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost){
                    bestNodeIndex = i;
                }
            }
        }

        // After the loop, we get The best node whick is our next step
        currentNode = openList.get(bestNodeIndex);

        if(currentNode == goalNode){
            goalReached = true;
            trackThePath();
        }
        step++;
    }

   
}

private void openNode(Node node){

    if(node.open == false && node.checked == false && node.solid == false){

        // If the node is not opened yet, add it to the open list
        node.setAsOpen();
        node.parent = currentNode;
        openList.add(node);

       

    }
}

private void trackThePath(){

    // Backtrack the node and draw the path
    Node current = goalNode;

    while(current != startNode){
        current = current.parent;

        if(current != startNode){
            current.setAsPath();
        }
    }
}
}


