package gr.ds.unipi.regularGrid;

public class RegularGrid {
	private final double minY;
	private final double maxY;
	private final double minX;
	private final double maxX;	
	private Cell[][] cells;
	private final int hSectors;
	private final int vSectors;
	
	public RegularGrid(double minX, double minY, double maxX, double maxY, 
			int hSectors, int vSectors, Point[] points) {
		this.minY = minY;
		this.maxY = maxY;
		this.minX = minX;
		this.maxX = maxX;
		this.hSectors = hSectors;
		this.vSectors = vSectors;
		
		build(points);
	}
	
	public void build(Point[] points ) {			
		double vStep = (maxY - minY) / vSectors;
		double hStep = (maxX - minX) / hSectors;
		
		Point lowerLeft;
		Point upperRight;
		
		cells = new Cell[vSectors][hSectors];
		short id = 0;
		for (int i = 0; i < vSectors; i++) {
			for (int j = 0; j < hSectors; j++) {	
				// the three following cases ensure that there won't be any
				// calculation errors (caused by floating-point precision) that will
				// result in creating a grid slightly smaller than intended
				
				// case when building top-right cell
				if (j == hSectors - 1 && i == vSectors - 1) {
					lowerLeft = new Point(minX + j * hStep, minY + i * vStep);
					upperRight = new Point(maxX, maxY);
					cells[i][j] = new Cell(lowerLeft, upperRight, Short.toString(id++));
					continue;
				} 
				
				// case when building cells on the right side of the grid
				if (j == hSectors -1) {
					lowerLeft = new Point(minX + j * hStep, minY + i * vStep);
					upperRight = new Point(maxX, minY + (i + 1) * vStep);
					cells[i][j] = new Cell(lowerLeft, upperRight, Short.toString(id++));
					continue;
				}
				
				
				// case when building cells on the top side of the grid
				if (i == vSectors - 1) {
					lowerLeft = new Point(minX+ j * hStep, minY + i * vStep);
					upperRight = new Point(minX + (j + 1) * hStep, maxY);
					cells[i][j] = new Cell(lowerLeft, upperRight, Short.toString(id++));
					continue;
				}
				
				// case when building the remaining cells
				lowerLeft = new Point(minX + j * hStep, minY + i * vStep);
				upperRight = new Point(minX + (j + 1) * hStep, minY + (i + 1) * vStep);
				cells[i][j] = new Cell(lowerLeft, upperRight, Short.toString(id++));
			}
		}
		
		for (Point p : points) {
			int j = (int) Math.floor(hSectors * (p.getX()- minX) / (maxX - minX));
			int i = (int) Math.floor(vSectors * (p.getY() - minY) / (maxY - minY));
			if (i == vSectors) {
				i--;
			}
			if (j == hSectors) {
				j--;
			}
			
			cells[i][j].getPoints().add(p);
		}
		
		double meanPoints = 0;
		double varPoints = 0;
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[0].length; j++) {
				if (cells[i][j] != null) {
					meanPoints += cells[i][j].getPoints().size();
					// System.out.println(cells[i][j].toString());
				}
			}
		}
		
		meanPoints /= vSectors * hSectors;
		
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[0].length; j++) {
				if (cells[i][j] != null) {
					varPoints += Math.pow(cells[i][j].getPoints().size() - meanPoints, 2);
					// System.out.println(cells[i][j].toString());
				} else {
					varPoints += Math.pow(meanPoints, 2);
				}
			}
		}
		
		varPoints /= vSectors * hSectors;
		
		System.out.println(String.format("Total cells of grid: %dx%d=%d", hSectors, vSectors,vSectors * hSectors));
		System.out.println(String.format("Mean points per cell: %f", meanPoints));
		System.out.println(String.format("Variance: %f", Math.sqrt(varPoints)));
	}
	
	public int getVSectors() {
		return vSectors;
	}
	
	public int getHSectors() {
		return hSectors;
	}
	
	public Cell[][] getCells() {
		return cells;
	}	
}
