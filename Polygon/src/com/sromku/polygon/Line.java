package com.sromku.polygon;

/**
 * Line is defined by starting point and ending point on 2D dimension.<br>
 * 
 * @author Roman Kushnarenko (sromku@gmail.com)
 */
public class Line
{
	private final Point _start;
	private final Point _end;
	private float _a = Float.NaN;
	private float _b = Float.NaN;
	private boolean _vertical = false;

	public Line(Point start, Point end)
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
	 * Indicate whereas the point lays on the line.
	 * 
	 * @param point
	 *            - The point to check
	 * @return <code>True</code> if the point lays on the line, otherwise return <code>False</code>
	 */
	public boolean isInside(Point point)
	{
        if (_end.x == _start.x) {
            	if (point.y == _end.y) return false;
            	float ty = (point.y - _start.y) / (_end.y - _start.y);
            	return (ty >= 0) && (ty <= 1);
        }
        if (_end.y == _start.y) {
            	if (point.x == _end.x) return false;
            	float tx = (point.x - _start.x) / (_end.x - _start.x);
            	return (tx >= 0) && (tx <= 1);
        }
        float tx = (point.x - _start.x) / (_end.x - _start.x);
        float ty = (point.y - _start.y) / (_end.y - _start.y);
        return (tx == ty) && (tx >= 0) && (tx <= 1);
	}

	/**
	 * Indicate whereas the line is vertical. <br>
	 * For example, line like x=1 is vertical, in other words parallel to axis Y. <br>
	 * In this case the A is (+/-)infinite.
	 * 
	 * @return <code>True</code> if the line is vertical, otherwise return <code>False</code>
	 */
	public boolean isVertical()
	{
		return _vertical;
	}

	/**
	 * y = <b>A</b>x + B
	 * 
	 * @return The <b>A</b>
	 */
	public float getA()
	{
		return _a;
	}

	/**
	 * y = Ax + <b>B</b>
	 * 
	 * @return The <b>B</b>
	 */
	public float getB()
	{
		return _b;
	}

	/**
	 * Get start point
	 * 
	 * @return The start point
	 */
	public Point getStart()
	{
		return _start;
	}

	/**
	 * Get end point
	 * 
	 * @return The end point
	 */
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
