package com.example.tankwargame.GameEntities;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.tankwargame.CollisionDetector;
import com.example.tankwargame.GameObjectStorage;
import com.example.tankwargame.Interfaces.IMovable;
import com.example.tankwargame.R;

import java.util.ArrayList;

public class Shell extends GameObject implements IMovable {

    private Tank mShellOwner;
    private Character mDirection;
    private final long speed = 450;

    Shell(Context context, Tank owner, Character direction, int x, int y) {
        this.mContext = context;
        this.mShellOwner = owner;
        this.posX = x;
        this.posY = y;
        this.mBitmapFile = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.tank_shell);
        this.width = this.mBitmapFile.getWidth();
        this.height = this.mBitmapFile.getHeight();
        this.mDirection = direction;
        switch(direction){
            case 'u':
                isMovingUp = true;
                break;
            case 'd':
                isMovingDown = true;
                break;
            case 'l':
                isMovingLeft = true;
                break;
            case 'r':
                isMovingRight = true;
                break;
            default:
                isMovingRight = true;
                break;
        }
    }


    //Unique Methods
    public void draw(Canvas canvas, Paint paint){
//        canvas.drawBitmap(mBitmapFile, posX, posY, paint);
        canvas.drawBitmap(getBitmapFile(), getX(), getY(), paint);
    }

    private void moveHelper(GameObject currentItem){
        if(!this.equals(currentItem)){
            if(CollisionDetector.checkForCollision(this, currentItem)){
                //YOU CAN ALTERNATE THE FOLLOWING TWO IF STATEMENTS, ONE WILL ALLOW WALLS TO BE DESTROYED AND ONE WILL NOT
//                if(!currentItem.equals(this.mShellOwner)){
                if(!(currentItem instanceof Wall || currentItem.equals(this.mShellOwner))){
                    this.destroy();
                    currentItem.destroy();;
                }else if(currentItem instanceof Wall){
                    this.destroy();
                }
            }
        }
    }

    public void destroy(){
        GameObjectStorage.removeGameObject(this);
        GameObjectStorage.removeMovableObject(this);
        mShellOwner.toggleCanFire();
    }

    //Access Methods
    public Tank getShellOwner(){ return mShellOwner; }

    public Character getDirection(){ return mDirection; }

    //Implement Methods
    public boolean getIsMovingLeft() {
        return isMovingLeft;
    }
    public boolean getIsMovingRight() {
        return isMovingRight;
    }
    public boolean getIsMovingDown() {
        return isMovingDown;
    }
    public boolean getIsMovingUp() {
        return isMovingUp;
    }
    public void moveLeft(long fps, ArrayList<GameObject> listOfPotentialColliders) {
//        posX = posX - (speed / (fps + 1));
        setPosX(getX() - (speed / (fps + 1)));
        for(int iterator = 0; iterator < GameObjectStorage.getGameObjectsSize(); iterator++){
            GameObject currentObject = listOfPotentialColliders.get(iterator);
            moveHelper(currentObject);
        }
    }
    public void moveRight(long fps, ArrayList<GameObject> listOfPotentialColliders) {
//        posX = posX + (speed / (fps + 1));
        setPosX(getX() + (speed / (fps + 1)));
        for(int iterator = 0; iterator < GameObjectStorage.getGameObjectsSize(); iterator++){
            GameObject currentObject = listOfPotentialColliders.get(iterator);
            moveHelper(currentObject);
        }
    }
    public void moveUp(long fps, ArrayList<GameObject> listOfPotentialColliders) {
//        posY = posY - (speed / (fps + 1));
        setPosY(getY() - (speed / (fps + 1)));
        for(int iterator = 0; iterator < GameObjectStorage.getGameObjectsSize(); iterator++){
            GameObject currentObject = listOfPotentialColliders.get(iterator);
            moveHelper(currentObject);
        }
    }
    public void moveDown(long fps, ArrayList<GameObject> listOfPotentialColliders) {
//        posY = posY + (speed / (fps + 1));
        setPosY(getY() + (speed / (fps + 1)));
        for(int iterator = 0; iterator < GameObjectStorage.getGameObjectsSize(); iterator++){
            GameObject currentObject = listOfPotentialColliders.get(iterator);
            moveHelper(currentObject);
        }
    }


}

/*
* TODO
*  BUG FIX >>> There seems to be a collision detection bug for the shells where if you fire several at once and ONE collides
*   with something then it will destroy a bunch of them. LOOK INTO
*
* */