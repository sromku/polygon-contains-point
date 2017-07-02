package com.sromku.test;

import com.sromku.polygon.Point;
import com.sromku.polygon.Polygon;

public class Tests
{
	public static void main(String[] args)
	{
		testSimplePolygon();
		
		testPolygonWithHoles();
	}
	
	/**
	 * Create simple polygon and check that the point is inside
	 */
	public static void testSimplePolygon()
	{
		Polygon polygon = Polygon.Builder()
				.addVertex(new Point(1, 3))
				.addVertex(new Point(2, 8))
				.addVertex(new Point(5, 4))
				.addVertex(new Point(5, 9))
				.addVertex(new Point(7, 5))
				.addVertex(new Point(6, 1))
				.addVertex(new Point(3, 1))
				.build();
		
		// Point is inside
		isInside(polygon, new Point(5.5f, 7));
		
		// Point isn't inside
		isInside(polygon, new Point(4.5f, 7));
	}
	
	/**
	 * Create polygon two holes and check that the point is inside
	 */
	public static void testPolygonWithHoles()
	{
		Polygon polygon = Polygon.Builder()
				.addVertex(new Point(1, 2)) // polygon
				.addVertex(new Point(1, 6))
				.addVertex(new Point(8, 7))
				.addVertex(new Point(8, 1))
				.close() 
				.addVertex(new Point(2, 3)) // hole one
				.addVertex(new Point(5, 5))
				.addVertex(new Point(6, 2))
				.close() 
				.addVertex(new Point(6, 6)) // hole two
				.addVertex(new Point(7, 6))
				.addVertex(new Point(7, 5))
				.build();
		
		// Point is inside
		isInside(polygon, new Point(6, 5));
		
		// Point isn't inside
		isInside(polygon, new Point(4, 3));
		
		// Point isn't inside
		isInside(polygon, new Point(6.5f, 5.8f));
	}
	
	/**
	 * Check if point inside the polygon
	 * 
	 * @param polygon
	 * @param point
	 */
	private static void isInside(Polygon polygon, Point point)
	{
		boolean contains = polygon.contains(point);
		System.out.println("The point:" + point.toString() + " is " + (contains ? "" : "not ") + "inside the polygon");
	}
}
