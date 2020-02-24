package com.example.tankwargame;

import java.util.ArrayList;

public interface IMovable {

    public boolean getIsMovingLeft();
    public boolean getIsMovingRight();
    public boolean getIsMovingDown();
    public boolean getIsMovingUp();

    public void moveLeft(long fps, ArrayList<GameObject> listOfPotentialColliders);
    public void moveRight(long fps, ArrayList<GameObject> listOfPotentialColliders);
    public void moveUp(long fps, ArrayList<GameObject> listOfPotentialColliders);
    public void moveDown(long fps, ArrayList<GameObject> listOfPotentialColliders);
}
