package com.romkuapps.polygon;

import java.util.ArrayList;
import java.util.List;

/**
 * The 2D polygon with borders
 * 
 * @author Roman Kushnarenko (sromku@gmail.com)
 */
public class Polygon
{
	private BoundingBox _boundingBox = null;
	private List<Vector> _sides = new ArrayList<Vector>();

	private Polygon(List<Vector> sides)
	{
		_sides = sides;
	}

	public static Builder Builder()
	{
		return new Builder();
	}

	/**
	 * Builder of the surface with borders
	 * 
	 * @author Roman Kushnarenko (sromku@gmail.com)</br> Created date: Mar 1, 2013
	 */
	public static class Builder
	{
		private List<Point> _points = new ArrayList<Point>();
		private BoundingBox _boundingBox = null;
		private List<Vector> _sides = new ArrayList<Vector>();

		private boolean _firstPoint = true;
		private boolean _isClosed = false;

		/**
		 * Add points by their order on the border
		 * 
		 * @param point
		 * @return
		 */
		public Builder addPoint(Point point)
		{
			if (_isClosed)
			{
				_points = new ArrayList<Point>();
				_isClosed = false;
			}

			defineBoundingBox(point);
			_points.add(point);

			// set Vectors
			if (_points.size() > 1)
			{
				Vector Vector = new Vector(_points.get(_points.size() - 2), point);
				_sides.add(Vector);
			}

			return this;
		}

		/**
		 * This will create new vector from the last point to the beginning.
		 * 
		 * @return
		 */
		public Builder close()
		{
			validate();

			// add last Vector
			_sides.add(new Vector(_points.get(_points.size() - 1), _points.get(0)));
			_isClosed = true;

			return this;
		}

		/**
		 * Build the polygon shape
		 * 
		 * @return
		 */
		public Polygon build()
		{
			validate();

			// in case you forgot to close
			if (!_isClosed)
			{
				// add last Vector
				_sides.add(new Vector(_points.get(_points.size() - 1), _points.get(0)));
			}
			Polygon polygon = new Polygon(_sides);
			polygon.setBoundingBox(_boundingBox);

			return polygon;
		}

		private void defineBoundingBox(Point point)
		{
			if (_firstPoint)
			{
				_boundingBox = new BoundingBox();
				_boundingBox.xMax = point.x;
				_boundingBox.xMin = point.x;
				_boundingBox.yMax = point.y;
				_boundingBox.yMin = point.y;

				_firstPoint = false;
			}
			else
			{
				// set bounding box
				if (point.x > _boundingBox.xMax)
				{
					_boundingBox.xMax = point.x;
				}
				else if (point.x < _boundingBox.xMin)
				{
					_boundingBox.xMin = point.x;
				}
				if (point.y > _boundingBox.yMax)
				{
					_boundingBox.yMax = point.y;
				}
				else if (point.y < _boundingBox.yMin)
				{
					_boundingBox.yMin = point.y;
				}
			}
		}

		private void validate()
		{
			if (_points.size() < 3)
			{
				throw new RuntimeException("Polygon must have at least 3 points");
			}
		}
	}

	/**
	 * Check if the the given point is inVector the polygon.<br>
	 * 
	 * @param point
	 * @return <code>True</code> if the point is inVector the polygon, otherwise return <code>False</code>
	 */
	public boolean contains(Point point)
	{
		if (inBoundingBox(point))
		{
			Vector ray = createRay(point);
			int intersection = 0;
			for (Vector side : _sides)
			{
				if (intersect(ray, side))
				{
					// System.out.println("intersection++");
					intersection++;
				}
			}

			/*
			 * If the number of intersections is odd, then the point is isInside the polygon
			 */
			if (intersection % 2 == 1)
			{
				return true;
			}
		}
		return false;
	}

	public List<Vector> getSides()
	{
		return _sides;
	}

	/**
	 * By given ray and one side of the polygon, this method will return <code>True</code> if both vectors intersect
	 * 
	 * @param ray
	 * @param side
	 * @return
	 */
	private boolean intersect(Vector ray, Vector side)
	{
		Point intersectPoint = null;

		// if both vectors aren't from the kind of x=1 lines then go into
		if (!ray.isVertical() && !side.isVertical())
		{
			// check if both vectors are parallel. If they are parallel then no intersection point will exist
			if (ray.getA() - side.getA() == 0)
			{
				return false;
			}

			float x = ((side.getB() - ray.getB()) / (ray.getA() - side.getA())); // x = (b2-b1)/(a1-a2)
			float y = side.getA() * x + side.getB(); // y = a2*x+b2
			intersectPoint = new Point(x, y);
		}

		else if (ray.isVertical() && !side.isVertical())
		{
			float x = ray.getStart().x;
			float y = side.getA() * x + side.getB(); // y = a2*x+b2
			intersectPoint = new Point(x, y);
		}

		else if (!ray.isVertical() && side.isVertical())
		{
			float x = side.getStart().x;
			float y = ray.getA() * x + ray.getB(); // y = a2*x+b2
			intersectPoint = new Point(x, y);
		}

		else
		{
			return false;
		}

		// System.out.println("Ray: " + ray.toString() + " ,Side: " + side);
		// System.out.println("Intersect point: " + intersectPoint.toString());

		if (side.isInside(intersectPoint) && ray.isInside(intersectPoint))
		{
			return true;
		}

		return false;
	}

	/**
	 * Create ray
	 * 
	 * @param point
	 * @return
	 */
	private Vector createRay(Point point)
	{
		// create outside point
		float epsilon = (_boundingBox.xMax - _boundingBox.xMin) / 100f;
		Point outsidePoint = new Point(_boundingBox.xMin - epsilon, _boundingBox.yMin);

		Vector vector = new Vector(outsidePoint, point);
		return vector;
	}

	private void setBoundingBox(BoundingBox boundingBox)
	{
		_boundingBox = boundingBox;
	}

	/**
	 * Check if the given point is in bounding box
	 * 
	 * @param point
	 * @return
	 */
	private boolean inBoundingBox(Point point)
	{
		if (point.x < _boundingBox.xMin || point.x > _boundingBox.xMax || point.y < _boundingBox.yMin || point.y > _boundingBox.yMax)
		{
			return false;
		}
		return true;
	}

	private static class BoundingBox
	{
		public float xMax = -1f;
		public float xMin = -1f;
		public float yMax = -1f;
		public float yMin = -1f;
	}
}
