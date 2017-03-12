package jp.citrous.practicalanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import jp.citrous.practicalanimation.view.PlayPauseIconView;

/**
 * Created by citrous on 2017/03/13.
 */

public class PlayPauseAnimationSampleActivity extends AppCompatActivity {

    private PlayPauseIconView animatorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.playpause);

        animatorView = (PlayPauseIconView) findViewById(R.id.animatorView);

        animatorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animatorView.startCustomAnimation();
            }
        });
    }
}
