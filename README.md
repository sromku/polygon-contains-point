polygon-contains-point
======================


The point-in-polygon (PIP) problem asks whether a given point in the plane lies inside, outside, or on the boundary of a polygon.
Wiki reference: [Point in polygon](http://en.wikipedia.org/wiki/Point_in_polygon)

## Algorithm
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

    Copyright 2013-present Roman Kushnarenko

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

[![Bitdeli Badge](https://d2weczhvl823v0.cloudfront.net/sromku/polygon-contains-point/trend.png)](https://bitdeli.com/free "Bitdeli Badge")
