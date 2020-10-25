package com.example.tankwargame;

import com.example.tankwargame.GameEntities.Tank;

public class Player {

    private int mLives = 5;
    private Tank mTank;

    Player() {

    }

    //Get Methods
    public int getLives() {
        return mLives;
    }

    public Tank getTank() {
        return mTank;
    }

//    public void negateLife() {
//        mLives = (mLives > 0) ? mLives-- : mLives;
//    }

    public boolean negateLife(){
        if(mLives != 0)
        {
            mLives--;
            return true;
        }
        else
        {
            return false; //PLAYER HAS RUN OUT OF LIVES
        }
    }

    public void addLife() {
        mLives = (mLives < 5) ? mLives++ : mLives;
    }

    public void setTank(Tank tank) { this.mTank = tank ; }

    public void clearTank(){ this.mTank = null ; }
}
