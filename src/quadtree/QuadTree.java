package quadtree;

import model.Data;
import model.Node;

public class QuadTree {

	static Node root = null;
	int maxX = 500;
	int maxY = 500;
	
	public void addNode(int x, int y, int currentX, int currentY, Node startNode){
		if((x < 0 || x>maxX) && (y < 0 || y > maxY)){
			System.out.println("ERROR!");
			return;
		}
		if(root == null){
			root = new Node();
			root.data = new Data();
			root.data.x = x;
			root.data.y = y;
			return;
		} else {
			boolean isNodeAdded = false;
			boolean east = true;
			boolean north = true;
			Node parentNode = startNode;
			Node currentNode = startNode;
			
			
			
			while(!isNodeAdded){
				if(currentNode == null){
					currentNode = createNode(x,y);
					isNodeAdded = true;
					if(east && north){
						parentNode.northEast = currentNode;
					}
					if(east && !north){
						parentNode.southEast = currentNode;
					}
					if(!east && north){
						parentNode.northWest = currentNode;
					}
					if(!east && !north){
						parentNode.southWest = currentNode;
					}
					if(parentNode.data != null && parentNode.data.x != 0){
						Data tempData = new Data();
						tempData = parentNode.data;
						parentNode.data = null;
						addNode(tempData.x, tempData.y, currentX, currentY, parentNode);
					}
					break;
				}
				east = x > currentX;
				north = y > currentY;
				parentNode = currentNode;
				if(east && north){
					currentNode = currentNode.northEast;
					
						currentX += currentX/2;
						currentY += currentY/2;
					
				}
				if(east && !north){
					currentNode = currentNode.southEast;
				
						currentX += currentX/2;
						currentY -= currentY/2;
					
				}
				if(!east && north){
					currentNode = currentNode.northWest;
					
						currentX -= currentX/2;
						currentY += currentY/2;
					
				}
				if(!east && !north){
					currentNode = currentNode.southWest;
					
						currentX -= currentX/2;
						currentY -= currentY/2;
					
				}
			}
		}
		
	}
	
	public Node createNode(int x, int y){
		Node n = new Node();
		n.data = new Data();
		n.data.x = x;
		n.data.y = y;
		return n;
	}
	
	
	public static void main(String[] args) {
		QuadTree tree = new QuadTree();
		tree.addNode(29, 500-17, 250, 250, root);
		tree.addNode(57, 500-41, 250, 250, root);
		Node rootLocal = root;
		//tree.addNode(153.0, 121.0, 0.0, 0.0);
		System.out.println("debug pause");
	}
	
}
