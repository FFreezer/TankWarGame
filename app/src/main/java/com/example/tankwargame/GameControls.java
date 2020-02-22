package com.example.tankwargame;

import android.content.Context;
import android.widget.Button;

class GameControls {
    Button mButtonLeft;
    Button mButtonRight;
    Button mButtonDown;
    Button mButtonUp;
    Button mButtonFire;

    GameControls(Button left, Button right, Button up, Button down, Button fire){
        mButtonLeft = left;
        mButtonDown = down;
        mButtonUp = up;
        mButtonRight = right;
        mButtonFire = fire;
    }
}
