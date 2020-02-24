package com.example.tankwargame;

import android.util.Log;

class CollisionDetector {

     static boolean checkForTankCollision(Tank tank, GameObject gameobject){
        // collision detected!
//        Log.d("Tank Details : ", "X: " + tank.posX + " Y : " + tank.posY + " Height : " + tank.height + " Width : " + tank.width);
//        Log.d("GameObject Details : ", "X: " + gameobject.posX + " Y : " + gameobject.posY + " Height : " + gameobject.getHeight() + " Width : " + gameobject.getWidth());

        return tank.posX < gameobject.posX + gameobject.getWidth() &&
                tank.posX + tank.getWidth() > gameobject.posX &&
                tank.posY < gameobject.posY + gameobject.getHeight() &&
                tank.posY + tank.getHeight() > gameobject.posY;
    }

}


/*
*   TODO
*    FIX COLLISION DETECTION FOR WALLS
*
* */