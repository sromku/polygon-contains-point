package com.romkuapps.test;

import com.romkuapps.polygon.Point;
import com.romkuapps.polygon.Polygon;
import com.romkuapps.polygon.Vector;

public class Driver
{
	public static void main(String[] args)
	{
		Polygon polygon = Polygon.Builder()
				.addPoint(new Point(1, 4))
				.addPoint(new Point(4, 5))
				.addPoint(new Point(6, 1))
				.addPoint(new Point(1, 1))
				.build();

		Point point = new Point(2.5f, 2.5f);
		boolean contains = polygon.contains(point);

		System.out.println("The point:" + point.toString() + " is " + (contains ? "" : "not ") + "inside the polygon");

		System.out.println("Polygon:");
		for (Vector vector : polygon.getSides())
		{
			System.out.println("  " + vector.toString());
		}
	}
}
