package com.example.tankwargame;

import android.content.Context;

public class Wall extends GameObject {

    final int area = 40;
    int width, height;
    float top, right, bottom, left;

    public Wall(Context context){
        this.width = (int) Math.random();
        this.height = area / this.width;
    }



}
