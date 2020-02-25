package com.example.tankwargame;

import java.util.ArrayList;

public class ObjectStorage {
    private static ArrayList<GameObject> gameObjects;
    private static ArrayList<IMovable> movableGameObjects;

    public static void addGameObject(GameObject objectToAdd){
        gameObjects.add(objectToAdd);
    }
    public static void removeGameObject(GameObject objectToRemove){
        gameObjects.remove(objectToRemove);
    }

    public static void addMovableObject(IMovable objectToAdd){
        movableGameObjects.add(objectToAdd);
    }

    public static void removeMovableObject(IMovable objectToRemove){
        movableGameObjects.remove(objectToRemove);
    }

}
