package jp.citrous.practicalanimation.model;

import android.graphics.Point;

/**
 * Created by citrous on 2017/03/13.
 */

public class Quadrangle {

    private Point point1;
    private Point point2;
    private Point point3;
    private Point point4;

    public Quadrangle(Point point1, Point point2, Point point3, Point point4) {
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
        this.point4 = point4;
    }

    public Point getPoint1() {
        return point1;
    }

    public Point getPoint2() {
        return point2;
    }

    public Point getPoint3() {
        return point3;
    }

    public Point getPoint4() {
        return point4;
    }
}
