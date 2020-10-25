package com.example.tankwargame.GameEntities;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.tankwargame.Enums.MovingDirection;
import com.example.tankwargame.GameView;
import com.example.tankwargame.Interfaces.IMovable;


public abstract class MovableObject extends GameObject implements IMovable {

    protected final long defaultSpeed = 450;
    protected long speed = 450;
    protected boolean isMovingLeft,
            isMovingRight,
            isMovingUp,
            isMovingDown = false;
    protected MovingDirection mMovingDirection = null;

    public MovingDirection getMovingDirection(){ return mMovingDirection; }

    public void setMovingDirection(MovingDirection newMovingDirection){ this.mMovingDirection = newMovingDirection; }

    public void translatePosition(MovingDirection direction){
        long fps = GameView.getFps();
        switch(direction){
            case UP:
                mMovingDirection = MovingDirection.UP;
                setPosY(getY() - (speed / (fps + 1)));
                break;
            case DOWN:
                mMovingDirection = MovingDirection.DOWN;
                setPosY(getY() + (speed / (fps + 1)));
                break;
            case LEFT:
                mMovingDirection = MovingDirection.LEFT;
                setPosX(getX() - (speed / (fps + 1)));
                break;
            case RIGHT:
                mMovingDirection = MovingDirection.RIGHT;
                setPosX(getX() + (speed / (fps + 1)));
                break;
            default:
                break;
        }
    }

    public void increaseSpeed(){ this.speed += 100; }

    public void decreaseSpeed(){ this.speed -= 100; }

    public void changeSpeedTo(long newSpeed){
        this.speed = newSpeed;
    }

    public void resetSpeedToDefault(){ this.speed = defaultSpeed; }

    public void toggleIsMovingRight() { this.isMovingRight = !isMovingRight; }

    public void toggleIsMovingLeft() { this.isMovingLeft = !isMovingLeft; }

    public void toggleIsMovingUp() { this.isMovingUp = !isMovingUp; }

    public void toggleIsMovingDown() { this.isMovingDown = !isMovingDown;}

    public boolean getIsMovingLeft(){ return isMovingLeft; }

    public boolean getIsMovingRight() { return isMovingRight; }

    public boolean getIsMovingDown() { return isMovingDown; }

    public boolean getIsMovingUp() { return isMovingUp; }

}
