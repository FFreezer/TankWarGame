package com.example.tankwargame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
//        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Inflate game_buttons.xml layout file into a view & define layout paramaters for it in params
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        View controlsView = LayoutInflater.from(this).inflate(R.layout.game_buttons, null, false);
        // Get references to user interface buttons and populate class with them for gameView class
        Button mButtonLeft = (Button)controlsView.findViewById(R.id.button_left);
        Button mButtonDown = (Button)controlsView.findViewById(R.id.button_down);
        Button mButtonRight = (Button)controlsView.findViewById(R.id.button_right);
        Button mButtonUp = (Button)controlsView.findViewById(R.id.button_up);
        Button mButtonFire = (Button)controlsView.findViewById(R.id.button_fire);
        mControls = new GameControls(mButtonLeft,mButtonRight,mButtonUp,mButtonDown,mButtonFire);

        gameView = new GameView(this, mControls);
        setContentView(gameView);
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
