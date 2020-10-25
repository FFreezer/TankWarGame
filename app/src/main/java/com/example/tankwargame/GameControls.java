package com.example.tankwargame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.tankwargame.GameEntities.Tank;

class GameControls {
    private Button mButtonLeft;
    private Button mButtonRight;
    private Button mButtonDown;
    private Button mButtonUp;
    private Button mButtonFire;

    GameControls(Button left, Button right, Button up, Button down, Button fire){
        mButtonLeft = left;
        mButtonDown = down;
        mButtonUp = up;
        mButtonRight = right;
        mButtonFire = fire;
    }

    //Access Methods
    public Button getmButtonDown() { return mButtonDown; }

    public Button getmButtonFire() { return mButtonFire; }

    public Button getmButtonLeft() { return mButtonLeft; }

    public Button getmButtonRight() { return mButtonRight; }

    public Button getmButtonUp() { return mButtonUp; }

    public void assignControlsFor(final Tank tank){
        final Tank innerTank = tank;
        assignLeftControlFor(tank);
        assignRightControlFor(tank);
        assignDownControlFor(tank);
        assignUpControlFor(tank);
        assignFireControlFor(tank);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void assignFireControlFor(Tank tank) {
        final Tank innerTank = tank;
        this.mButtonFire.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    innerTank.fireShell();
                    return true;
                }
                return false;
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void assignUpControlFor(Tank tank) {
        final Tank innerTank = tank;
        this.mButtonUp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        innerTank.toggleIsMovingUp();
                        return true;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        innerTank.toggleIsMovingUp();
                        return true;
                }
                return false;
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void assignDownControlFor(Tank tank) {
        final Tank innerTank = tank;
        this.mButtonDown.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        innerTank.toggleIsMovingDown();
                        return true;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        innerTank.toggleIsMovingDown();
                        return true;
                }
                return false;
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void assignLeftControlFor(Tank tank){
        final Tank innerTank = tank;
        this.mButtonLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        innerTank.toggleIsMovingLeft();
                        return true;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        innerTank.toggleIsMovingLeft();
                        return true;
                }
                return false;
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void assignRightControlFor(Tank tank){
        final Tank innerTank = tank;
        this.mButtonRight.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        innerTank.toggleIsMovingRight();
                        return true;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        innerTank.toggleIsMovingRight();
                        return true;
                }
                return false;
            }
        });
    }

}

