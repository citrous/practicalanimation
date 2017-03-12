package jp.citrous.practicalanimation.view;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import jp.citrous.practicalanimation.R;
import jp.citrous.practicalanimation.model.Quadrangle;
import jp.citrous.practicalanimation.model.QuadrangleEvaluator;

/**
 * Created by citrous on 2017/03/13.
 */

public class PlayPauseIconView extends View {

    private static final int ANIMATION_DURATION = 1000;
    private Quadrangle startQuadrangle1;
    private Quadrangle endQuadrangle1;
    private Quadrangle currentQuadrangle1;
    private ValueAnimator animator1;
    private ValueAnimator animator2;
    private Quadrangle startQuadrangle2;
    private Quadrangle endQuadrangle2;
    private Quadrangle currentQuadrangle2;
    private Path path1 = new Path();
    private Path path2 = new Path();
    private Paint pathPaint;

    public PlayPauseIconView(Context context) {
        super(context);
        initParams();
    }

    public PlayPauseIconView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initParams();
    }

    public PlayPauseIconView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initParams();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PlayPauseIconView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initParams();
    }

    private void initParams() {
        setupQuadrangle1();
        setupQuadrangle2();
        animator1 = createAnimator(startQuadrangle1, endQuadrangle1);
        animator2 = createAnimator(startQuadrangle2, endQuadrangle2);
        pathPaint = new Paint();
        pathPaint.setColor(Color.WHITE);
        pathPaint.setStyle(Paint.Style.FILL);
    }

    private void setupQuadrangle1() {
        Resources res = getResources();
        int startIconLeft = res.getDimensionPixelSize(R.dimen.start_icon1_left);
        int startIconTop = res.getDimensionPixelSize(R.dimen.start_icon1_top);
        int startIconRight = res.getDimensionPixelSize(R.dimen.start_icon1_right);
        int startIconBottom = res.getDimensionPixelSize(R.dimen.start_icon1_bottom);
        int pauseIconLeft = res.getDimensionPixelSize(R.dimen.pause_icon1_left);
        int pauseIconTop = res.getDimensionPixelSize(R.dimen.pause_icon1_top);
        int pauseIconRight = res.getDimensionPixelSize(R.dimen.pause_icon1_right);
        int pauseIconBottom = res.getDimensionPixelSize(R.dimen.pause_icon1_bottom);

        startQuadrangle1 = new Quadrangle(
                new Point(startIconLeft, startIconTop),
                new Point(startIconRight, (int) (startIconTop + (startIconBottom - startIconTop) * 0.25)),
                new Point(startIconRight, (int) (startIconTop + (startIconBottom - startIconTop) * 0.75)),
                new Point(startIconLeft, startIconBottom)
        );
        endQuadrangle1 = new Quadrangle(
                new Point(pauseIconLeft, pauseIconTop),
                new Point(pauseIconRight, pauseIconTop),
                new Point(pauseIconRight, pauseIconBottom),
                new Point(pauseIconLeft, pauseIconBottom)
        );
        currentQuadrangle1 = startQuadrangle1;
    }

    private void setupQuadrangle2() {
        Resources res = getResources();
        int startIconLeft = res.getDimensionPixelSize(R.dimen.start_icon2_left);
        int startIconTop = res.getDimensionPixelSize(R.dimen.start_icon2_top);
        int startIconRight = res.getDimensionPixelSize(R.dimen.start_icon2_right);
        int startIconBottom = res.getDimensionPixelSize(R.dimen.start_icon2_bottom);
        int pauseIcon1Left = res.getDimensionPixelSize(R.dimen.pause_icon2_left);
        int pauseIcon1Top = res.getDimensionPixelSize(R.dimen.pause_icon2_top);
        int pauseIcon1Right = res.getDimensionPixelSize(R.dimen.pause_icon2_right);
        int pauseIcon1Bottom = res.getDimensionPixelSize(R.dimen.pause_icon2_bottom);

        startQuadrangle2 = new Quadrangle(
                new Point(startIconLeft, startIconTop),
                new Point(startIconRight, (startIconBottom + startIconTop) / 2),
                new Point(startIconRight, (startIconBottom + startIconTop) / 2),
                new Point(startIconLeft, startIconBottom)
        );
        endQuadrangle2 = new Quadrangle(
                new Point(pauseIcon1Left, pauseIcon1Top),
                new Point(pauseIcon1Right, pauseIcon1Top),
                new Point(pauseIcon1Right, pauseIcon1Bottom),
                new Point(pauseIcon1Left, pauseIcon1Bottom)
        );
        currentQuadrangle2 = startQuadrangle2;
    }

    private ValueAnimator createAnimator(Quadrangle startValue, Quadrangle endValue) {
        ValueAnimator animator = ValueAnimator.ofObject(new QuadrangleEvaluator(), startValue, endValue);
        animator.setDuration(ANIMATION_DURATION);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                invalidate();
            }
        });
        return animator;
    }

    public void startCustomAnimation() {
        animator1.start();
        animator2.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (animator1.isRunning()) {
            currentQuadrangle1 = (Quadrangle) animator1.getAnimatedValue();
            currentQuadrangle2 = (Quadrangle) animator2.getAnimatedValue();
        }

        path1.reset();
        path1.moveTo(currentQuadrangle1.getPoint1().x, currentQuadrangle1.getPoint1().y);
        path1.lineTo(currentQuadrangle1.getPoint2().x, currentQuadrangle1.getPoint2().y);
        path1.lineTo(currentQuadrangle1.getPoint3().x, currentQuadrangle1.getPoint3().y);
        path1.lineTo(currentQuadrangle1.getPoint4().x, currentQuadrangle1.getPoint4().y);
        canvas.drawPath(path1, pathPaint);

        path2.reset();
        path2.moveTo(currentQuadrangle2.getPoint1().x, currentQuadrangle2.getPoint1().y);
        path2.lineTo(currentQuadrangle2.getPoint2().x, currentQuadrangle2.getPoint2().y);
        path2.lineTo(currentQuadrangle2.getPoint3().x, currentQuadrangle2.getPoint3().y);
        path2.lineTo(currentQuadrangle2.getPoint4().x, currentQuadrangle2.getPoint4().y);
        canvas.drawPath(path2, pathPaint);
    }

}
