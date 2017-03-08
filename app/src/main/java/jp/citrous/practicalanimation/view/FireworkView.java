package jp.citrous.practicalanimation.view;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import java.util.Random;

import jp.citrous.practicalanimation.R;

/**
 * Created by citrous on 2017/03/04.
 */

public class FireworkView extends View {

    private static final int ANIMATION_DURATION = 1000;

    private Photon[] photons = new Photon[50];
    private float photonRadius;
    private int xParam;
    private int yParam1;
    private int yParam2;

    private ValueAnimator animator = createAnimator();

    public FireworkView(Context context) {
        super(context);
        initParams();
    }

    public FireworkView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initParams();
    }

    public FireworkView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initParams();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public FireworkView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initParams();
    }

    private void initParams() {
        photonRadius = getContext().getResources().getDimensionPixelSize(R.dimen.firework_photon_radius);
        xParam = getContext().getResources().getDimensionPixelSize(R.dimen.firework_photon_x_param);
        yParam1 = getContext().getResources().getDimensionPixelSize(R.dimen.firework_photon_y_param1);
        yParam2 = getContext().getResources().getDimensionPixelSize(R.dimen.firework_photon_y_param2);
    }

    private ValueAnimator createAnimator() {
        ValueAnimator animator = new ValueAnimator();
        animator.setFloatValues(100f);
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
        for (int i = 0; i < photons.length; i++) {
            photons[i] = createPhoton();
        }
        animator.start();
    }

    private Photon createPhoton() {
        Random random = new Random();
        Paint paint = new Paint();
        paint.setARGB(255, random.nextInt(255), random.nextInt(255), random.nextInt(255));
        return new Photon(
                new float[] {random.nextInt(xParam * 2) - xParam},
                new float[] {random.nextInt(yParam1) / 10f, random.nextInt(yParam2) + (yParam2 / 3)},
                photonRadius,
                paint
        );
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (animator.isRunning()) {
            float value = (float) animator.getAnimatedValue("");
            drawPhotons(canvas, value);
        }
    }

    private void drawPhotons(Canvas canvas, float value) {
        for (Photon photon : photons) {
            canvas.drawCircle(calcX(photon, value),
                    calcY(photon, value),
                    photon.getRadius(),
                    photon.getPaint());
        }
    }

    private float calcX(Photon photon, float value) {
        return value / 100 * photon.getXParams()[0] + centerOfX();
    }

    private float calcY(Photon photon, float value) {
        float[] params = photon.getYParams();
        float x = value / 100;
        return (params[0] * x * params[0] * x) - 2 * params[0] * params[1] * x + centerOfY();
    }

    private int centerOfX() {
        return getWidth() / 2;
    }

    private int centerOfY() {
        return getHeight() / 2;
    }
}
