package com.example.tankwargame.Interfaces;

import com.example.tankwargame.Enums.MovingDirection;
import com.example.tankwargame.GameEntities.GameObject;

import java.util.ArrayList;

public interface IMovable {

    public abstract void translatePosition(MovingDirection direction);

    public abstract boolean getIsMovingLeft();

    public abstract boolean getIsMovingRight();

    public abstract boolean getIsMovingDown();

    public abstract boolean getIsMovingUp();

//    public abstract void moveLeft();
//
//    public abstract void moveRight();
//
//    public abstract void moveUp();
//
//    public abstract void moveDown();

    //public MovingDirection getDirection();
}
