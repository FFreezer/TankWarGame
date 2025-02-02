package com.example.tankwargame;

import com.example.tankwargame.GameEntities.GameObject;

public abstract class CollisionDetector {

     public static boolean checkForCollision(GameObject gameobject1, GameObject gameobject2){
        return gameobject1.getX() < gameobject2.getX() + gameobject2.getmWidth() &&
                gameobject1.getX() + gameobject1.getmWidth() > gameobject2.getX() &&
                gameobject1.getY() < gameobject2.getY() + gameobject2.getmHeight() &&
                gameobject1.getY() + gameobject1.getmHeight() > gameobject2.getY();
    }
}
