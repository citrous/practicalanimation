package jp.citrous.practicalanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import jp.citrous.practicalanimation.view.FireworkView;

/**
 * Created by citrous on 2017/03/04.
 */

public class FireworkAnimationActivity extends AppCompatActivity {

    private FireworkView animatorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.firework);

        animatorView = (FireworkView) findViewById(R.id.animatorView);

        animatorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animatorView.startCustomAnimation();
            }
        });
    }
}
