package com.sromku.test;

import com.sromku.polygon.Point;
import com.sromku.polygon.Polygon;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Test class for the addAllVertex method. <br>
 *
 * @author Ioannis-Rafail(John) Mitromaras (GitHub: Johnmaras, Mail: mitromarasg96@gmail.com)
 */
public class TestAddAllVertex {

    @Test
    public void AddAllVertexInside(){
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(1, 3));
        points.add(new Point(2, 8));
        points.add(new Point(5, 4));
        points.add(new Point(5, 9));
        points.add(new Point(7, 5));
        points.add(new Point(6, 1));
        points.add(new Point(3, 1));

        Polygon polygon = Polygon.Builder().addAllVertex(points).build();

        //point inside polygon
        Point containedPoint = new Point(5.5f, 7);
        Assert.assertTrue(polygon.contains(containedPoint));

        //point outside polygon
        Point NotContainedPoint = new Point(4.5f, 7);
        Assert.assertFalse(polygon.contains(NotContainedPoint));
    }

    @Test
    public void AddAllVertexOutside(){
        ArrayList<Point> points = new ArrayList<>();
        points.add(new Point(1, 3)); //polygon
        points.add(new Point(1, 6));
        points.add(new Point(8, 7));
        points.add(new Point(8, 1));

        Polygon.Builder polygonBuilder = Polygon.Builder().addAllVertex(points).close();

        points.clear();
        points.add(new Point(2, 3)); // hole one
        points.add(new Point(5, 5));
        points.add(new Point(6, 2));

        polygonBuilder = polygonBuilder.addAllVertex(points).close();

        points.clear();
        points.add(new Point(6, 6)); // hole two
        points.add(new Point(7, 6));
        points.add(new Point(7, 5));

        polygonBuilder = polygonBuilder.addAllVertex(points);
        
        Polygon polygon = polygonBuilder.build();

        //point inside polygon
        Point containedPoint = new Point(6, 5);
        Assert.assertTrue(polygon.contains(containedPoint));

        //point outside polygon
        Point NotContainedPoint = new Point(4, 3);
        Assert.assertFalse(polygon.contains(NotContainedPoint));

        //point outside polygon
        NotContainedPoint = new Point(6.5f, 5.8f);
        Assert.assertFalse(polygon.contains(NotContainedPoint));
    }
}
