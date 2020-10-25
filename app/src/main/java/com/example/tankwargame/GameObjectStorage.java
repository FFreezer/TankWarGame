package com.example.tankwargame;

import com.example.tankwargame.GameEntities.GameObject;
import com.example.tankwargame.Interfaces.IMovable;

import java.util.ArrayList;

public abstract class GameObjectStorage {

    private static ArrayList<GameObject> gameObjects = new ArrayList<>();

    private static ArrayList<IMovable> movableGameObjects = new ArrayList<>();

    //GameObjects
    public static ArrayList<GameObject> getAllGameObjects(){return gameObjects;}

    public static void addGameObject(GameObject objectToAdd) {
        gameObjects.add(objectToAdd);
    }

    public static void addGameObject(int index, GameObject element){
        gameObjects.add(index, element);
    }

    public static void removeGameObject(GameObject objectToRemove) {
        gameObjects.remove(objectToRemove);
    }

    public static int getGameObjectsSize() {
        return gameObjects.size();
    }

    public static GameObject getGameObject(int index){ return gameObjects.get(index); }

    public static void clearGameObjects(){
        gameObjects.clear();
    }

    //MovableObjects
    public static ArrayList<IMovable> getAllMovableGameObjects(){return movableGameObjects; }

    public static void addMovableObject(IMovable objectToAdd) {
        movableGameObjects.add(objectToAdd);
    }

    public static void addMovableObject(int index, IMovable element){
        movableGameObjects.add(index, element);
    }

    public static void removeMovableObject(IMovable objectToRemove) {
        movableGameObjects.remove(objectToRemove);
    }

    public static int getMovableObjectsSize() {
        return movableGameObjects.size();
    }

    public static IMovable getMovableGameObject(int index){ return movableGameObjects.get(index); }

    public static void clearMovableGameObjects(){
        movableGameObjects.clear();
    }

}
