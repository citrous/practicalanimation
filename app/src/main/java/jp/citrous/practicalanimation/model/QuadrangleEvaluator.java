package jp.citrous.practicalanimation.model;

import android.animation.TypeEvaluator;
import android.graphics.Point;

/**
 * Created by citrous on 2017/03/10.
 */

public class QuadrangleEvaluator implements TypeEvaluator<Quadrangle> {

    @Override
    public Quadrangle evaluate(float fraction, Quadrangle startValue, Quadrangle endValue) {

        Point point1 = evaluatePoint(fraction, startValue.getPoint1(), endValue.getPoint1());
        Point point2 = evaluatePoint(fraction, startValue.getPoint2(), endValue.getPoint2());
        Point point3 = evaluatePoint(fraction, startValue.getPoint3(), endValue.getPoint3());
        Point point4 = evaluatePoint(fraction, startValue.getPoint4(), endValue.getPoint4());

        return new Quadrangle(point1, point2, point3, point4);
    }

    private Point evaluatePoint(float fraction, Point startPoint, Point endPoint) {
        return new Point(
                (int) (startPoint.x + fraction * (endPoint.x - startPoint.x)),
                (int) (startPoint.y + fraction * (endPoint.y - startPoint.y))
        );
    }
}



