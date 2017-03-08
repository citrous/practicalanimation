package jp.citrous.practicalanimation.view;

import android.animation.Animator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import java.util.Random;

import jp.citrous.practicalanimation.R;

/**
 * Created by citrous on 2017/03/05.
 */

public class MarbleView extends View {

    private static final int CIRCLE_COUNT = 20;
    private static final int MAX_DURATION = 1000;

    private static final String VELOCITY_X = "velocityX";
    private static final String VELOCITY_Y = "velocityY";
    private static final String PROGRESS = "progress";

    private ValueAnimator[] animators = new ValueAnimator[CIRCLE_COUNT];
    private Paint[] paints = new Paint[CIRCLE_COUNT];
    private boolean isRunning = false;

    private int maxDistance;
    private float circleRadius;

    public MarbleView(Context context) {
        super(context);
        initParams();
    }

    public MarbleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initParams();
    }

    public MarbleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initParams();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MarbleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initParams();
    }

    private void initParams() {
        maxDistance = getContext().getResources().getDimensionPixelSize(R.dimen.marble_max_distance);
        circleRadius = getContext().getResources().getDimensionPixelSize(R.dimen.marble_max_circle_radius);
    }

    private ValueAnimator createAnimator() {
        Random random = new Random();
        ValueAnimator animator = ValueAnimator.ofPropertyValuesHolder(
                PropertyValuesHolder.ofFloat(VELOCITY_X, (random.nextFloat() - 0.5f) * 2),
                PropertyValuesHolder.ofFloat(VELOCITY_Y, (random.nextFloat() - 0.5f) * 2),
                PropertyValuesHolder.ofFloat(PROGRESS, 1.0f));
        animator.setDuration(random.nextInt(MAX_DURATION / 2) + MAX_DURATION / 2);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                invalidate();
            }
        });
        return animator;
    }

    private Paint createPaint() {
        Random random = new Random();
        Paint paint = new Paint();
        paint.setARGB(255, random.nextInt(255), random.nextInt(255), random.nextInt(255));
        return paint;
    }

    private void createAndStart(final int index) {
        paints[index] = createPaint();
        animators[index] = createAnimator();
        animators[index].addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                createAndStart(index);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        animators[index].start();
    }

    public void startCustomAnimation() {
        if (isRunning) return;

        isRunning = true;
        for (int i = 0; i < animators.length; i++) {
            createAndStart(i);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (isRunning) {
            drawPhotons(canvas);
        }
    }

    private void drawPhotons(Canvas canvas) {
        for (int i = 0; i < animators.length; i++) {
            float velocityX = (float) animators[i].getAnimatedValue(VELOCITY_X);
            float velocityY = (float) animators[i].getAnimatedValue(VELOCITY_Y);
            float progress = (float) animators[i].getAnimatedValue(PROGRESS);
            paints[i].setAlpha(255 - (int) (255 * progress));
            canvas.drawCircle(maxDistance * velocityX + centerOfX(),
                    maxDistance * velocityY + centerOfY(),
                    circleRadius * progress,
                    paints[i]);
        }
    }

    private int centerOfX() {
        return getWidth() / 2;
    }

    private int centerOfY() {
        return getHeight() / 2;
    }
}
