package com.example.tankwargame;

public interface IMovable {

//    public final long speed = 150;

    public boolean getIsMovingLeft();
    public boolean getIsMovingRight();
    public boolean getIsMovingDown();
    public boolean getIsMovingUp();

    public boolean checkForCollision(GameObject potentialCollider);

    public void moveLeft(long fps);
    public void moveRight(long fps);
    public void moveUp(long fps);
    public void moveDown(long fps);
}
