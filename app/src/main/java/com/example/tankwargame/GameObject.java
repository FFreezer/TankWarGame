package com.example.tankwargame;

import android.graphics.Bitmap;

/*TODO
 * 1. Add width and height paramaters
 * 2. Modify the creation of any currently instantiated game objects to include those paramaters as they will
 *       be used for collision detection
 * 3. Modify move methods to allow for the amount to be divided by the games frame rate to movement is calculated accordingly
 *       and if not messed up by the CPU using inconsistent frame cycle times
 *
 * */

public abstract class GameObject {
    //Store coordinates
    protected long posX, posY;
    //Used when buttons are pressed for GameView.update method
    protected boolean isMovingLeft, isMovingRight, isMovingUp, isMovingDown = false;
    //Visual style for object
    protected Bitmap mBitmapFile;

    //If you decide to do powerups for speed then you'll need to remove the final declaration
    private final long speed = 150;

    //getters
    public long getPosX() {
        return this.posX;
    }
    public long getPosY() { return this.posY; }
    public Bitmap getBitmapFile() { return this.mBitmapFile; }

    /*
    * Motion Events
    * NOTE: fps + 1 means that the application will never attempt to divide by 0
    * */
    public void moveLeft(long fps) { posX = posX - (speed / (fps + 1)); }
    public void moveRight(long fps) { posX = posX + (speed / (fps + 1)); }
    public void moveUp(long fps) { posY = posY - (speed / (fps + 1)); }
    public void moveDown(long fps) { posY = posY + (speed / (fps + 1)); }


    /*
     * Use this method on game objects by passing it another game object and check if there is overlap in their positions
     * */
    public boolean checkCollisions(GameObject potentialCollider) {
        return false;
    }

    /*
     *
     * */
    public boolean checkScreenCollision() {
        return false;
    }
}
