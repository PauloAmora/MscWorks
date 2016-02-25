package quadtree;

import model.Data;
import model.Node;

public class QuadTree {

	Node root = null;
	Double maxX = 256.0;
	Double maxY = 256.0;
	
	public void addNode(Double x, Double y){
		if((x < -maxX || x>maxX) && (y < -maxY || y > maxY)){
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
			Node parentNode = root;
			Node currentNode = root;
			Double currentX = 0.0;
			Double currentY = 0.0;
			
			
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
					if(parentNode.data != null && parentNode.data.x != null){
						Data tempData = new Data();
						tempData = parentNode.data;
						parentNode.data = null;
						addNode(tempData.x, tempData.y);
					}
					break;
				}
				east = x > currentX;
				north = y > currentY;
				parentNode = currentNode;
				if(east && north){
					currentNode = currentNode.northEast;
				}
				if(east && !north){
					currentNode = currentNode.southEast;
				}
				if(!east && north){
					currentNode = currentNode.northWest;
				}
				if(!east && !north){
					currentNode = currentNode.southWest;
				}
			}
		}
		
	}
	
	public Node createNode(Double x, Double y){
		Node n = new Node();
		n.data = new Data();
		n.data.x = x;
		n.data.y = y;
		return n;
	}
	
	
	public static void main(String[] args) {
		QuadTree tree = new QuadTree();
		tree.addNode(-224.0, -229.0);
		tree.addNode(154.0, 121.0);
		tree.addNode(153.0, 121.0);
		System.out.println("debug pause");
	}
	
}
