package jp.citrous.practicalanimation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private static Class<?> activities[] = {
            TranslationAnimationSampleActivity.class,
            ScaleAnimationSampleActivity.class,
            RotationAnimationSampleActivity.class,
            AlphaAnimationSampleActivity.class,
            AnimatedVectorDrawableSampleActivity.class,
            ObjectAnimatorSampleActivity.class,
            FireworkAnimationActivity.class,
            MarbleAnimationActivity.class
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        LinearLayout layoutParent = (LinearLayout) findViewById(R.id.activity_main);
        for (Class<?> activity : activities) {
            layoutParent.addView(createActivityButton(activity));
        }
    }

    private Button createActivityButton(final Class<?> cls) {
        Button button = new Button(this);
        button.setAllCaps(false);
        button.setText(cls.getSimpleName());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, cls));
            }
        });
        return button;
    }
}
