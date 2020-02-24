package com.example.tankwargame;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Shell extends GameObject implements IMovable {

    private Context mContext;
    private boolean isMovingLeft, isMovingRight, isMovingDown, isMovingUp = false;
    private Tank mShellOwner;
    private Character mDirection;
    private final long speed = 250;

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

    public void draw(Canvas canvas, Paint paint){
        canvas.drawBitmap(mBitmapFile, posX, posY, paint);
    }

    //getters
    public Context getContext(){ return mContext; }
    public Tank getShellOwner(){ return mShellOwner; }
    public Character getDirection(){ return mDirection; }



    //Implement Methods
    public boolean getIsMovingLeft() {
        return isMovingLeft; }
    public boolean getIsMovingRight() {
        return isMovingRight; }
    public boolean getIsMovingDown() {
        return isMovingDown; }
    public boolean getIsMovingUp() {
        return isMovingUp; }
    public void moveLeft(long fps) {
        posX = posX - (speed / (fps + 1)); }
    public void moveRight(long fps) {
        posX = posX + (speed / (fps + 1)); }
    public void moveUp(long fps) {
        posY = posY - (speed / (fps + 1)); }
    public void moveDown(long fps) {
        posY = posY + (speed / (fps + 1)); }

    @Override
    public boolean checkForCollision(GameObject potentialCollider) {
        return false;
    }

}
