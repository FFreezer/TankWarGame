package com.example.tankwargame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

class GameControls {
    Button mButtonLeft;
    Button mButtonRight;
    Button mButtonDown;
    Button mButtonUp;
    Button mButtonFire;
    Tank mTank;

    GameControls(Button left, Button right, Button up, Button down, Button fire){
        mButtonLeft = left;
        mButtonDown = down;
        mButtonUp = up;
        mButtonRight = right;
        mButtonFire = fire;
    }

    GameControls(Button left, Button right, Button up, Button down, Button fire, Tank tank) {
        mButtonLeft = left;
        mButtonDown = down;
        mButtonLeft = left;
        mButtonRight = right;
        mButtonFire = fire;
        mTank = tank;
    }
}

