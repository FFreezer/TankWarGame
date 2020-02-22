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
    protected int posX;
    protected int posY;
    protected Bitmap mBitmapFile;

    public int getPosX() {
        return this.posX;
    }
    public int getPosY(){
        return this.posY;
    }
    public Bitmap getBitmapFile(){ return this.mBitmapFile; }

    public void moveLeft(int amount, long fps){ posX = posX - amount; }
    public void moveRight(int amount, long fps){ posX = posX + amount; }
    public void moveUp(int amount, long fps){ posY = posY + amount; }
    public void moveDown(int amount, long fps){ posY = posY - amount; }


    /*
    * Use this method on game objects by passing it another game object and check if there is overlap in their positions
    * */
    public boolean checkCollisions(GameObject potentialCollider){
        return false;
    }

    /*
    *
    * */
    public boolean checkScreenCollision(){
        return false;
    }
}
