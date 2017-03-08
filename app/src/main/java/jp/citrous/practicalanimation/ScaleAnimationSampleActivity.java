package jp.citrous.practicalanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by citrous on 2017/03/03.
 */

public class ScaleAnimationSampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.simple_animation);

        findViewById(R.id.droid_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.animate().scaleX(2.0f).scaleY(2.0f).start();
            }
        });
    }
}
