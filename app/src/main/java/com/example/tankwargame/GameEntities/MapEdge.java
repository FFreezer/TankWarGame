package com.example.tankwargame.GameEntities;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;


public class MapEdge extends Wall {

    public MapEdge(Context context, int screenWidth, int screenHeight, int height, int width, long X, long Y) {
        super(context, screenWidth, screenHeight);
        mContext = context;
        mHeight = height;
        mWidth = width;
        posX = X;
        posY = Y;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
    }
}
