package com.example.tankwargame;

import java.util.ArrayList;

public interface IMovable {

    public abstract boolean getIsMovingLeft();

    public abstract boolean getIsMovingRight();

    public abstract boolean getIsMovingDown();

    public abstract boolean getIsMovingUp();

    public abstract void moveLeft(long fps, ArrayList<GameObject> listOfPotentialColliders);

    public abstract void moveRight(long fps, ArrayList<GameObject> listOfPotentialColliders);

    public abstract void moveUp(long fps, ArrayList<GameObject> listOfPotentialColliders);

    public abstract void moveDown(long fps, ArrayList<GameObject> listOfPotentialColliders);

}
