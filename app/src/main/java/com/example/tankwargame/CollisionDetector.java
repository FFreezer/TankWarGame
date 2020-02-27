package com.example.tankwargame;

class CollisionDetector {

     static boolean checkForCollision(GameObject gameobject1, GameObject gameobject2){
        return gameobject1.posX < gameobject2.posX + gameobject2.getWidth() &&
                gameobject1.posX + gameobject1.getWidth() > gameobject2.posX &&
                gameobject1.posY < gameobject2.posY + gameobject2.getHeight() &&
                gameobject1.posY + gameobject1.getHeight() > gameobject2.posY;
    }
}
