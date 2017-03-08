package jp.citrous.practicalanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import jp.citrous.practicalanimation.view.MarbleView;

/**
 * Created by citrous on 2017/03/05.
 */

public class MarbleAnimationActivity extends AppCompatActivity {

    private MarbleView animatorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.marbles);

        animatorView = (MarbleView) findViewById(R.id.animatorView);

        animatorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animatorView.startCustomAnimation();
            }
        });
    }
}
