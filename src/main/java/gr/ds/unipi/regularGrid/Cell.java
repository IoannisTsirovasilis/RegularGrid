package gr.ds.unipi.regularGrid;

import java.util.ArrayList;

public class Cell {
	private final Point upperRight;
	private final Point lowerLeft;
	private final String id;
	private final ArrayList<Point> points;
	
	public Cell(Point lowerLeft, Point upperRight, String id) {
		this.upperRight = upperRight;
		this.lowerLeft = lowerLeft;
		this.id = id;
		points = new ArrayList<Point>();
	}
	
	public Point getUpperRight() {
		return upperRight;
	}
	
	public Point getLowerLeft() {
		return lowerLeft;
	}
	
	public String getId() {
		return id;
	}
	
	public ArrayList<Point> getPoints() {
		return points;
	}
	
	public boolean contains(double lat, double lon) {
		if (lowerLeft.getY() <= lat && lowerLeft.getX() <= lon
				&& upperRight.getY() >= lat && upperRight.getX() >= lon) {
			return true;
		}
		
		return false;
	}
	
	public boolean areEqual(Cell cell) {		
		if (lowerLeft.getX() != cell.lowerLeft.getX() || lowerLeft.getY() != cell.lowerLeft.getY()) {
			return false;
		}
		
		if (upperRight.getX() != cell.upperRight.getX() || upperRight.getY() != cell.upperRight.getY()) {
			return false;
		}
		
		return true;
	}
}
