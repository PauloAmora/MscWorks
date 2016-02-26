package model;

public class Rect {

	public int originX;
	public int originY;
	public int width;
	public int height;
	
	public Rect (int originX, int originY, int width, int height){
		this.originX = originX;
		this.originY = originY;
		this.width = width;
		this.height = height;
	}
	
	public boolean contains (int x, int y){
		return (((originX < x) && (x < (originX + width))) && ((originY < y) && (y < (originY+height))));
	}
	
	public Rect topLeft(){
		return new Rect(originX, originY, width/2, height/2);
	}

	public Rect topRight(){
		return new Rect(originX + width/2, originY, width/2, height/2);
	}
	
	public Rect bottomLeft(){
		return new Rect(originX, originY + height/2, width/2, height/2);
	}
	
	public Rect bottomRight(){
		return new Rect(originX + width/2, originY + height/2, width/2, height/2);
	}
	public Region detectRegion(int x, int y){
		boolean north;
		boolean east;
		if(originX < x && x < width/2){
			east = false;
		} else {
			east = true;
		}
		if (originY < y && y < height/2){
			north = true;
		} else {
			north = false;
		}
		
		if(north && east){
			return Region.NORTHEAST;
		}
		if(!north && east){
			return Region.SOUTHEAST;
		}
		if(north && !east){
			return Region.NORTHWEST;
		}
		if(!north && !east){
			return Region.SOUTHWEST;
		}
		
		return null;
		
	}
	
	
}
