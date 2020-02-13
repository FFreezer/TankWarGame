package com.example.tankwargame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< Updated upstream
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
=======
>>>>>>> Stashed changes
        gameView = new GameView(this);
        setContentView(gameView);
        gameView.run();
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }
    @Override
    protected void onResume(){
        super.onResume();
        gameView.resume();
    }
}
