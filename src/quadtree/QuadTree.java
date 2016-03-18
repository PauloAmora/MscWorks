package quadtree;

import java.util.ArrayList;
import java.util.List;

import model.Data;
import model.Node;
import model.Rect;
import model.Region;

public class QuadTree {

	public Node root = null;
	int maxX = 500;
	int maxY = 500;
	public int height = 0;
	public List<Node> treeAsList = null;

	public void toList(List<Node> list, Node toAdd) {
		if (toAdd != null) {
			if (!list.contains(toAdd))
				list.add(toAdd);
			toList(list, toAdd.northEast);
			toList(list, toAdd.northWest);
			toList(list, toAdd.southEast);
			toList(list, toAdd.southWest);
		}
	}

	public void insertOrDelete(int x, int y, Node start) {
		if (this.treeAsList == null)
			this.treeAsList = new ArrayList<Node>();
		toList(this.treeAsList, start);
		boolean isDelete = false;
		for (Node n : this.treeAsList) {
			isDelete = containsData(n, x, y);
			if (isDelete)
				break;
		}
		if (isDelete)
			delete(x, y, start);
		else
			insert(x, y, start);

	}

	public boolean containsData(Node test, int x, int y) {
		return test.data != null ? test.data.x == x && test.data.y == y : false;

	}

	public boolean delete(int x, int y, Node start) {
		Region deleteRegion = null;
		boolean deleteParamData = false;

		if (start == null)
			return false;
		if (start.isLeaf())
			return false;
		if (!start.rect.contains(x, y)) {
			return false;
		}

		if (delete(x, y, start.northEast)) {
			deleteParamData = true;
		}
		if (delete(x, y, start.northWest)) {
			deleteParamData = true;
		}
		if (delete(x, y, start.southEast)) {
			deleteParamData = true;
		}
		if (delete(x, y, start.southWest)) {
			deleteParamData = true;

		}

		if (start.northEast != null) {
			boolean isRemovalInLeaf = false;
			if (!isRemovalInLeaf && start.northEast.data != null && start.northEast.data.equals(x, y)) {
				isRemovalInLeaf = true;
			}
			if (!isRemovalInLeaf && start.northWest.data != null && start.northWest.data.equals(x, y)) {
				isRemovalInLeaf = true;
			}
			if (!isRemovalInLeaf && start.southEast.data != null && start.southEast.data.equals(x, y)) {
				isRemovalInLeaf = true;
			}
			if (!isRemovalInLeaf && start.southWest.data != null && start.southWest.data.equals(x, y)) {
				isRemovalInLeaf = true;
			}

			int countLeaves = 0;
			if (start.northEast.data != null && start.hasOnlyLeaves()) {
				deleteRegion = Region.NORTHEAST;
				countLeaves++;
			}
			if (start.northWest.data != null && start.hasOnlyLeaves()) {
				deleteRegion = Region.NORTHWEST;
				countLeaves++;
			}
			if (start.southEast.data != null && start.hasOnlyLeaves()) {
				deleteRegion = Region.SOUTHEAST;
				countLeaves++;
			}
			if (start.southWest.data != null && start.hasOnlyLeaves()) {
				deleteRegion = Region.SOUTHWEST;
				countLeaves++;
			}
			if (countLeaves > 2) {
					switch (deleteRegion) {
					case NORTHEAST:
						start.northEast.data = null;
						break;
					case NORTHWEST:
						start.northWest.data = null;
						break;
					case SOUTHEAST:
						start.southEast.data = null;
						break;
					case SOUTHWEST:
						start.southWest.data = null;
						break;
					default:
						break;

					}
				}

				if (countLeaves == 2 && isRemovalInLeaf) {
					deleteParamData = false;
					if (start.northEast.data != null && !start.northEast.data.equals(x, y)) {
						start.data = start.northEast.data;
						deleteParamData = true;
					}
					if (start.northWest.data != null && !start.northWest.data.equals(x, y)) {
						start.data = start.northWest.data;
						deleteParamData = true;
					}
					if (start.southEast.data != null && !start.southEast.data.equals(x, y)) {
						start.data = start.southEast.data;
						deleteParamData = true;
					}
					if (start.southWest.data != null && !start.southWest.data.equals(x, y)) {
						start.data = start.southWest.data;
						deleteParamData = true;
					}
					if (deleteParamData) {
						start.northEast = null;
						start.northWest = null;
						start.southEast = null;
						start.southWest = null;
						height--;
					}
					deleteParamData = true;
				}
				if (countLeaves == 1) {
					deleteParamData = false;
					if (start.northEast.data != null) {
						start.data = start.northEast.data;
						deleteParamData = true;
					}
					if (start.northWest.data != null) {
						start.data = start.northWest.data;
						deleteParamData = true;
					}
					if (start.southEast.data != null) {
						start.data = start.southEast.data;
						deleteParamData = true;
					}
					if (start.southWest.data != null) {
						start.data = start.southWest.data;
						deleteParamData = true;
					}
					if (deleteParamData) {
						start.northEast = null;
						start.northWest = null;
						start.southEast = null;
						start.southWest = null;
						height--;
					}
					deleteParamData = true;
				}
			}

		return deleteParamData;

	}

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
			height++;
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
		tree.treeAsList = new ArrayList<Node>();
		tree.insertOrDelete(53, 38, tree.root);
		tree.insertOrDelete(394, 372, tree.root);
		tree.insertOrDelete(90, 71, tree.root);
		tree.insertOrDelete(90, 71, tree.root);
		// tree.insertOrDelete(394, 372, tree.root);
		tree.treeAsList.clear();
		tree.toList(tree.treeAsList, tree.root);
		System.out.println("debug pause");
	}

}
