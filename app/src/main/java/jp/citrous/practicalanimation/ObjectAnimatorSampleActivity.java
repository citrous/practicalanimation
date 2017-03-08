package jp.citrous.practicalanimation;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by citrous on 2017/03/04.
 */

public class ObjectAnimatorSampleActivity extends AppCompatActivity {

    private AnimatorSet animator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.simple_animation);

        View view = findViewById(R.id.droid_icon);
        animator = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.animator);
        animator.setTarget(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animator.start();
            }
        });
    }
}
