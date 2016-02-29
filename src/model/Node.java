package model;

public class Node {

	public Node northEast;
	public Node southEast;
	public Node southWest;
	public Node northWest;
	public Data data;
	public Rect rect;
	public Node(){
	}
	public boolean isLeaf(){
		return (this.northEast == null) && (this.northWest == null) && (this.southEast == null) && (this.southWest == null);
	}
	public boolean hasOnlyLeaves(){
		return this.northEast.isLeaf() && this.southEast.isLeaf() && this.northWest.isLeaf() && this.northWest.isLeaf();
	}
	
}
