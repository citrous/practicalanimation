package jp.citrous.practicalanimation.view;

import android.graphics.Paint;

/**
 * Created by citrous on 2017/03/05.
 */

public class Photon {

    private float[] xParams;
    private float[] yParams;
    private float radius;
    private Paint paint;

    public Photon(float[] xParams, float[] yParams, float radius, Paint paint) {
        this.xParams = xParams;
        this.yParams = yParams;
        this.radius = radius;
        this.paint = paint;
    }

    public float[] getXParams() {
        return xParams;
    }

    public void setXParams(float[] xParams) {
        this.xParams = xParams;
    }

    public float[] getYParams() {
        return yParams;
    }

    public void setYParams(float[] yParams) {
        this.yParams = yParams;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }
}
