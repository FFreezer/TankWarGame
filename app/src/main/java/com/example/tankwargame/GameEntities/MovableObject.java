package com.example.tankwargame.GameEntities;

import com.example.tankwargame.Enums.MovingDirection;
import com.example.tankwargame.GameView;
import com.example.tankwargame.Interfaces.IMovable;


public abstract class MovableObject extends GameObject implements IMovable {

    protected long speed = 350;

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

}
