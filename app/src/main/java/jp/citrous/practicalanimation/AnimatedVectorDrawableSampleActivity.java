package jp.citrous.practicalanimation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by citrous on 2017/03/03.
 */

public class AnimatedVectorDrawableSampleActivity extends AppCompatActivity {

    private AnimatedVectorDrawableCompat animatable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.animated_vector_drawable);
        animatable = AnimatedVectorDrawableCompat.create(this, R.drawable.animated_vector_drawable);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageDrawable(animatable);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animatable.start();
            }
        });
    }
}
