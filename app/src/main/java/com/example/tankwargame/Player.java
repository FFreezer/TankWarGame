package com.example.tankwargame;

public class Player {

    private int mLives = 3;
    private Tank mTank;

    Player(Tank tank){
        mTank = tank;
    }

    //Get Methods
    public int getLives(){
        return mLives;
    }
    public Tank getTank(){
        return mTank;
    }

    public void negateLife() {
        mLives = (mLives > 0) ? mLives-- : mLives;
    }

    public void addLife(){
        mLives = (mLives < 5) ? mLives++ : mLives;
    }
}
