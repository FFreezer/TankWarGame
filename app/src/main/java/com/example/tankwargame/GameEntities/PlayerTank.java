package com.example.tankwargame.GameEntities;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.example.tankwargame.Enums.MovingDirection;
import com.example.tankwargame.GameView;
import com.example.tankwargame.Interfaces.IMovable;
import com.example.tankwargame.R;

public class PlayerTank extends Tank implements IMovable {

    public PlayerTank(GameView gameView, Context context, int bitmapResource, int x, int y, MovingDirection direction) {
        super(gameView, context, bitmapResource, x, y, direction);
    }

    @Override
    public void translatePosition(MovingDirection direction) {
        super.translatePosition(direction);
        translatePositionHelper(direction);
    }

    private void translatePositionHelper(MovingDirection direction) {
        switch(direction){
            case UP:
                this.setBitmapFile(mContext, R.drawable.ptankup);
                break;
            case DOWN:
                this.setBitmapFile(mContext, R.drawable.ptankdown);
                break;
            case LEFT:
                this.setBitmapFile(mContext, R.drawable.ptankleft);
                break;
            case RIGHT:
                this.setBitmapFile(mContext, R.drawable.ptankright);
                break;
        }
    }
}
