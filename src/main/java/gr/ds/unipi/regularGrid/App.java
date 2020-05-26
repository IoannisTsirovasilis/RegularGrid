package gr.ds.unipi.regularGrid;

import java.util.Random;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	int hSectors = 33;
    	int vSectors = 33;
    	int length = 1_000_000;
    	Point[] points = new Point[length];
    	double minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
    	double x, y;
    	Random r = new Random();
    	int i = -1;
        	for (i = 0; i < length; i++) {
        		//x = (2 * Math.random() - 1) * 40;
        		//y = Math.random() * 20;
        		
        		x = r.nextGaussian() * 10;
        		y = r.nextGaussian() * 20;
        		//y = normalY(x, 0, 10);
        		if (x < minX) minX = x;
        		if (x > maxX) maxX = x;
        		if (y < minY) minY = y;
        		if (y > maxY) maxY = y;
        		points[i] = new Point(x, y);
        	}      	
        //}
    	
    	long startTime = System.nanoTime();
        new RegularGrid(minX, minY, maxX, maxY, hSectors, vSectors, points);
        long elapsedTime = System.nanoTime() - startTime;
        System.out.println(String.format("Min X: %f, Min Y: %f, Max X: %f, Max Y: %f", minX, minY, maxX, maxY));
        System.out.println("Total execution time (ms): "  + elapsedTime/1000000);
    }
}
