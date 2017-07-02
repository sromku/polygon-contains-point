polygon-contains-point
======================

The point-in-polygon (PIP) problem asks whether a given point in the plane lies inside, outside, or on the boundary of a polygon.
Wiki reference: [Point in polygon](http://en.wikipedia.org/wiki/Point_in_polygon)

## Latest Release

:inbox_tray: Download - [polygon-1.0.2.jar](https://github.com/sromku/polygon-contains-point/releases/download/1.0.2/polygon-1.0.2.jar)

:bookmark_tabs: Changelog - https://github.com/sromku/polygon-contains-point/releases/latest

## Algorith
The number of intersections for a ray passing from the exterior of the polygon to any point; if odd, it shows that the point lies inside the polygon. If it is even, the point lies outside the polygon.

<img src="/assets/simple_polygon.png"/>

## Usage

### Build Polygon

This is the simple version of the polygon. You can build both types of polygons: [concave and non-convex](http://en.wikipedia.org/wiki/Convex_and_concave_polygons)
``` java
Polygon polygon = Polygon.Builder()
        .addVertex(new Point(1, 3))
        .addVertex(new Point(2, 8))
        .addVertex(new Point(5, 4))
        .addVertex(new Point(5, 9))
        .addVertex(new Point(7, 5))
        .addVertex(new Point(6, 1))
        .addVertex(new Point(3, 1))
        .build();
```

### Build Polygon with Holes

First build all the border sides of the polygon. Close the borders and then you can start adding holes into the polygon.
You can add multiple number of holes, just remember to `close()` after each added hole. 
``` java
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
```

<img src="/assets/polygon_with_holes.png"/>

### Check if the point inside

``` java
Point point = new Point(4.5f, 7);
boolean contains = polygon.contains(point);
```

## Tests

Two main tests are attached (not in junit format). Both tests cover polygons with holes and without.

## License

Apache 2.0. See [LICENSE](LICENSE)

## Follow us

[![Twitter URL](https://img.shields.io/twitter/url/http/shields.io.svg?style=social)](https://twitter.com/intent/tweet?text=https://github.com/snatik/polygon-contains-point)
[![Twitter Follow](https://img.shields.io/twitter/follow/snatikteam.svg?style=social)](https://twitter.com/snatikteam)