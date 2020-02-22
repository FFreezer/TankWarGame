package com.example.tankwargame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    GameView gameView;
    //Added
//    FrameLayout game;
    RelativeLayout mGameButtons;
    GameControls mControls;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Button mButtonLeft = findViewById(R.id.button_left);
        Button mButtonDown = findViewById(R.id.button_down);
        Button mButtonRight = findViewById(R.id.button_right);
        Button mButtonUp = findViewById(R.id.button_up);
        Button mButtonFire = findViewById(R.id.button_fire);
        mControls = new GameControls(mButtonLeft,mButtonRight,mButtonUp,mButtonDown,mButtonFire);
        gameView = new GameView(this, mControls);

        setContentView(gameView);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        View controlsView = LayoutInflater.from(this).inflate(R.layout.game_buttons, null, false);
        addContentView(controlsView, params);

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
