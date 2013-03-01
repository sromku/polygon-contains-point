package com.romkuapps.polygon;

/**
 * Vector
 * 
 * @author Roman Kushnarenko (sromku@gmail.com)
 */
public class Vector
{
	private Point _start;
	private Point _end;
	private float _a = Float.NaN;
	private float _b = Float.NaN;
	private boolean _vertical = false;

	public Vector(Point start, Point end)
	{
		_start = start;
		_end = end;

		if (_end.x - _start.x != 0)
		{
			_a = ((_end.y - _start.y) / (_end.x - _start.x));
			_b = _start.y - _a * _start.x;
		}

		else
		{
			_vertical = true;
		}
	}

	/**
	 * Is the point inside the vector
	 * 
	 * @param point
	 * @return
	 */
	public boolean isInside(Point point)
	{
		float maxX = _start.x > _end.x ? _start.x : _end.x;
		float minX = _start.x < _end.x ? _start.x : _end.x;
		float maxY = _start.y > _end.y ? _start.y : _end.y;
		float minY = _start.y < _end.y ? _start.y : _end.y;

		if ((point.x >= minX && point.x <= maxX) && (point.y >= minY && point.y <= maxY))
		{
			return true;
		}
		return false;
	}

	/**
	 * Is the line is vertical
	 * 
	 * @return
	 */
	public boolean isVertical()
	{
		return _vertical;
	}

	public float getA()
	{
		return _a;
	}

	public float getB()
	{
		return _b;
	}

	public Point getStart()
	{
		return _start;
	}

	public Point getEnd()
	{
		return _end;
	}

	@Override
	public String toString()
	{
		return String.format("%s-%s", _start.toString(), _end.toString());
	}
}
