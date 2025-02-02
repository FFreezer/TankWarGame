package com.example.tankwargame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    private GameView mGameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Game Launch Code Starts Here
        //Inflate game_buttons.xml layout file into a view & define layout paramaters for it in params
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        View controlsView = LayoutInflater.from(this).inflate(R.layout.game_buttons, null, false);
        // Get references to user interface buttons and populate class with them for mGameView class
        Button mButtonLeft = controlsView.findViewById(R.id.button_left);
        Button mButtonDown = controlsView.findViewById(R.id.button_down);
        Button mButtonRight = controlsView.findViewById(R.id.button_right);
        Button mButtonUp = controlsView.findViewById(R.id.button_up);
        Button mButtonFire = controlsView.findViewById(R.id.button_fire);
        GameControls mControls = new GameControls(mButtonLeft,mButtonRight,mButtonUp,mButtonDown,mButtonFire);

        mGameView = new GameView(this, mControls);
        setContentView(mGameView);
        addContentView(controlsView, layoutParams);
        mGameView.run();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGameView.pause();
    }
    @Override
    protected void onResume(){
        super.onResume();
        mGameView.resume();
    }

    @Override
    protected void onStop(){
        super.onStop();
        GameObjectStorage.clearGameObjects();
        GameObjectStorage.clearMovableGameObjects();
    }
}
