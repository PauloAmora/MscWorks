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
	public int getNumberOfDataChilds() {
		int numberOfDataChilds = 0;
		if(this.northEast.data != null){
			numberOfDataChilds++;
		}
		if(this.northWest.data != null){
			numberOfDataChilds++;
		}
		if(this.southEast.data != null){
			numberOfDataChilds++;
		}
		if(this.southWest.data != null){
			numberOfDataChilds++;
		}
		return numberOfDataChilds;
	}
	
}
