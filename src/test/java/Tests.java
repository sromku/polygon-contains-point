import com.snatik.polygon.Point;
import com.snatik.polygon.Polygon;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Tests {

    @Test
    public void testSimplePolygon() {

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
        assertEquals("", true, polygon.contains(new Point(5.5f, 7)));

        // Point isn't inside
        assertEquals("", false, polygon.contains(new Point(4.5f, 7)));
    }

    /**
     * Create polygon two holes and check that the point is inside
     */
    @Test
    public void testPolygonWithHoles() {
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
        assertEquals(true, polygon.contains(new Point(6, 5)));

        // Point isn't inside
        assertEquals(false, polygon.contains(new Point(4, 3)));

        // Point isn't inside
        assertEquals(false, polygon.contains(new Point(6.5f, 5.8f)));
    }

    @Test
    public void testPolygonFigure6() {

        // example 1
        System.out.println("Figure 6a");
        Polygon polygon = Polygon.Builder()
                .addVertex(new Point(1, 3))
                .addVertex(new Point(9, 3))
                .addVertex(new Point(9, 7))
                .addVertex(new Point(7, 5))
                .addVertex(new Point(5, 7))
                .addVertex(new Point(3, 5))
                .addVertex(new Point(1, 7))
                .addVertex(new Point(1, 3))
                .build();

        // Point is inside
        assertEquals(true, polygon.contains(new Point(5, 5)));

        // example 2
        System.out.println("Figure 6b");
        polygon = Polygon.Builder()
                .addVertex(new Point(1, 3))
                .addVertex(new Point(3, 5))
                .addVertex(new Point(5, 3))
                .addVertex(new Point(7, 5))
                .addVertex(new Point(9, 3))
                .addVertex(new Point(9, 7))
                .addVertex(new Point(1, 7))
                .build();

        // Point is inside
        assertEquals(true, polygon.contains(new Point(5, 5)));

    }

    /**
     * Test issue: https://github.com/sromku/polygon-contains-point/issues/1
     */
    @Test
    public void testMapCoordinates1() {

        Polygon polygon = Polygon.Builder()
                .addVertex(new Point(42.499148, 27.485196))
                .addVertex(new Point(42.498600, 27.480000))
                .addVertex(new Point(42.503800, 27.474680))
                .addVertex(new Point(42.510000, 27.468270))
                .addVertex(new Point(42.510788, 27.466904))
                .addVertex(new Point(42.512116, 27.465350))
                .addVertex(new Point(42.512000, 27.467000))
                .addVertex(new Point(42.513579, 27.471027))
                .addVertex(new Point(42.512938, 27.472668))
                .addVertex(new Point(42.511829, 27.474922))
                .addVertex(new Point(42.507945, 27.480124))
                .addVertex(new Point(42.509082, 27.482892))
                .addVertex(new Point(42.536026, 27.490519))
                .addVertex(new Point(42.534470, 27.499703))
                .addVertex(new Point(42.499148, 27.485196))
                .build();

        assertEquals(true, polygon.contains(new Point(42.508956f, 27.483328f)));
        assertEquals(true, polygon.contains(new Point(42.505f, 27.48f)));

    }

    /**
     * Test issue: https://github.com/sromku/polygon-contains-point/issues/1
     */
    @Test
    public void testMapCoordinates2() {

        Polygon polygon = Polygon.Builder()
                .addVertex(new Point(40.481171, 6.4107070)) // NE
                .addVertex(new Point(40.480248, 6.4101200)) // SE. This point doesn't fail the test anymore
                .addVertex(new Point(40.480237, 6.4062790)) // SW
                .addVertex(new Point(40.481161, 6.4062610)) // NW
                .build();

        assertEquals(true, polygon.contains(new Point(40.480890f, 6.4081030f)));

    }

    /**
     * Test issue: https://github.com/sromku/polygon-contains-point/issues/3
     */
    @Test
    public void testParallel() {

        Polygon polygon = Polygon.Builder()
                .addVertex(new Point(0, 0))
                .addVertex(new Point(0, 1))
                .addVertex(new Point(1, 2))
                .addVertex(new Point(1, 99))
                .addVertex(new Point(100, 0))
                .build();

        assertEquals(true, polygon.contains(new Point(3, 4)));
        assertEquals(true, polygon.contains(new Point(3, 4.1)));
        assertEquals(true, polygon.contains(new Point(3, 3.9)));

    }

    @Test
    public void testBorders() {

        /*
         * Unfortunately, this method won't work if the point is on the edge of the polygon.
         * https://en.wikipedia.org/wiki/Point_in_polygon#Ray_casting_algorithm
         */

        Polygon polygon = Polygon.Builder()
                .addVertex(new Point(-1, -1))
                .addVertex(new Point(-1, 1))
                .addVertex(new Point(1, 1))
                .addVertex(new Point(1, -1))
                .build();

        // it's false !
        assertEquals(false, polygon.contains(new Point(0, 1)));

        polygon = Polygon.Builder()
                .addVertex(new Point(-1, -1))
                .addVertex(new Point(-1, 1))
                .addVertex(new Point(1, 1))
                .addVertex(new Point(1, -1))
                .build();

        // it's true !
        assertEquals(true, polygon.contains(new Point(-1, 0)));

    }
}
