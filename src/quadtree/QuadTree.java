package quadtree;

import model.Data;
import model.Node;
import model.Rect;

public class QuadTree {

	static Node root = null;
	int maxX = 500;
	int maxY = 500;

	public boolean insert(int x, int y, Node start, Node parent) {
		Node parentNode = start;
		Node currentNode = start;
		if (root == null) {
			root = new Node();
			root.data = new Data();
			root.data.x = x;
			root.data.y = y;
			root.rect = new Rect(0, 0, maxX, maxY);
			return true;
		}
		if (!currentNode.rect.contains(x, y)) {
			return false;
		}
		
		switch(parentNode.rect.detectRegion(x, y)){
		case NORTHEAST:
			currentNode = parentNode.northEast;
			if(currentNode == null){
				currentNode = new Node();
				currentNode.rect = parentNode.rect.topRight();
			}
			parentNode.northEast = currentNode;
			break;
		case NORTHWEST:
			currentNode = parentNode.northWest;
			if(currentNode == null){
				currentNode = new Node();
				currentNode.rect = parentNode.rect.topLeft();
			}
			parentNode.northWest = currentNode;
			break;
		case SOUTHEAST:
			currentNode = parentNode.southEast;
			if(currentNode == null){
				currentNode = new Node();
				currentNode.rect = parentNode.rect.bottomRight();
			}
			parentNode.southEast = currentNode;
			break;
		case SOUTHWEST:
			currentNode = parentNode.southWest;
			if(currentNode == null){
				currentNode = new Node();
				currentNode.rect = parentNode.rect.bottomLeft();
			}
			parentNode.southWest = currentNode;  
			break;
		default:
			break;
			
		}
		if (currentNode.data == null) {
			currentNode.data = new Data();
			currentNode.data.x = x;
			currentNode.data.y = y;
		}
		if(parentNode.data != null){
			Data tmpData = new Data();
			tmpData.x = parentNode.data.x;
			tmpData.y = parentNode.data.y;
			parentNode.data = null;
			insert(tmpData.x,tmpData.y,currentNode, parentNode);
			
		}
		
		
			
		
		
		return true;
	}

	/*
	 * public void addNode(int x, int y, int currentX, int currentY, Node
	 * startNode){ if((x < 0 || x>maxX) && (y < 0 || y > maxY)){
	 * System.out.println("ERROR!"); return; } if(root == null){ root = new
	 * Node(); root.data = new Data(); root.data.x = x; root.data.y = y; return;
	 * } else { boolean isNodeAdded = false; boolean east = true; boolean north
	 * = true; Node parentNode = startNode; Node currentNode = startNode;
	 * 
	 * 
	 * 
	 * while(!isNodeAdded){ if(currentNode == null){ currentNode =
	 * createNode(x,y); isNodeAdded = true; if(east && north){
	 * parentNode.northEast = currentNode; } if(east && !north){
	 * parentNode.southEast = currentNode; } if(!east && north){
	 * parentNode.northWest = currentNode; } if(!east && !north){
	 * parentNode.southWest = currentNode; } if(parentNode.data != null &&
	 * parentNode.data.x != 0){ Data tempData = new Data(); tempData =
	 * parentNode.data; parentNode.data = null; addNode(tempData.x, tempData.y,
	 * currentX, currentY, parentNode); } break; } east = x > currentX; north =
	 * y > currentY; parentNode = currentNode; if(east && north){ currentNode =
	 * currentNode.northEast;
	 * 
	 * currentX += currentX/2; currentY += currentY/2;
	 * 
	 * } if(east && !north){ currentNode = currentNode.southEast;
	 * 
	 * currentX += currentX/2; currentY -= currentY/2;
	 * 
	 * } if(!east && north){ currentNode = currentNode.northWest;
	 * 
	 * currentX -= currentX/2; currentY += currentY/2;
	 * 
	 * } if(!east && !north){ currentNode = currentNode.southWest;
	 * 
	 * currentX -= currentX/2; currentY -= currentY/2;
	 * 
	 * } } }
	 * 
	 * }
	 */
	public Node createNode(int x, int y) {
		Node n = new Node();
		n.data = new Data();
		n.data.x = x;
		n.data.y = y;
		return n;
	}

	public static void main(String[] args) {
		QuadTree tree = new QuadTree();
		tree.insert(29, 500 - 17, root, root);
		tree.insert(57, 500 - 41, root, root);
		Node rootLocal = root;
		// tree.addNode(153.0, 121.0, 0.0, 0.0);
		System.out.println("debug pause");
	}

}
