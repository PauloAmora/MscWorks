package quadtree;

import model.Data;
import model.Node;
import model.Rect;

public class QuadTree {

	Node root = null;
	int maxX = 500;
	int maxY = 500;

	public boolean insert(int x, int y, Node start) {
		boolean insertParamData = false;
		boolean insertOwnData = false;
	
		if (!start.rect.contains(x, y)) {
			return false;
		}

		if (start.isLeaf() && start.data == null) {
			start.data = new Data();
			start.data.x = x;
			start.data.y = y;
			return true;
		}
		if (start.northEast == null) {
			start.northEast = new Node();
			start.northEast.rect = start.rect.topRight();
			start.northWest = new Node();
			start.northWest.rect = start.rect.topLeft();
			start.southEast = new Node();
			start.southEast.rect = start.rect.bottomRight();
			start.southWest = new Node();
			start.southWest.rect = start.rect.bottomLeft();
		}
		if (start.data != null) {
			Data tempData = new Data();
			tempData = start.data;
			start.data = null;
			if (insert(tempData.x, tempData.y, start.northEast)) {
				insertOwnData = true;
			}
			if (insert(tempData.x, tempData.y, start.northWest)) {
				insertOwnData = true;
			}
			if (insert(tempData.x, tempData.y, start.southEast)) {
				insertOwnData = true;
			}
			if (insert(tempData.x, tempData.y, start.southWest)) {
				insertOwnData = true;
			}
		}
		if (insert(x, y, start.northEast)) {
			insertParamData = true;
		}
		if (insert(x, y, start.northWest)) {
			insertParamData = true;
		}
		if (insert(x, y, start.southEast)) {
			insertParamData = true;
		}
		if (insert(x, y, start.southWest)) {
			insertParamData = true;
		}

		return insertOwnData || insertParamData;
	}

	/*
	 * public Node createNode(int x, int y) { Node n = new Node(); n.data = new
	 * Data(); n.data.x = x; n.data.y = y; return n; }
	 */
	public static void main(String[] args) {
		QuadTree tree = new QuadTree();
		tree.root = new Node();
		tree.root.rect = new Rect(0, 0, 500, 500);
		tree.insert(53, 38, tree.root);
		tree.insert(394, 372, tree.root);
		tree.insert(90, 71, tree.root);
		System.out.println("debug pause");
	}

}
