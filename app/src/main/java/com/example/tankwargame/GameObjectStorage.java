package com.example.tankwargame;

import java.util.ArrayList;

public class GameObjectStorage {
    public static ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
    public static ArrayList<IMovable> movableGameObjects = new ArrayList<IMovable>();

    public static void addGameObject(GameObject objectToAdd) {
        gameObjects.add(objectToAdd);
    }

    public static void removeGameObject(GameObject objectToRemove) {
        gameObjects.remove(objectToRemove);
    }

    public static void addMovableObject(IMovable objectToAdd) {
        movableGameObjects.add(objectToAdd);
    }

    public static void removeMovableObject(IMovable objectToRemove) {
        movableGameObjects.remove(objectToRemove);
    }

    public static int getGameObjectsSize(){
        return gameObjects.size();
    }

    public static int getMovableObjectsSize(){
        return movableGameObjects.size();
    }

}
