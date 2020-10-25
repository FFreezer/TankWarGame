package com.example.tankwargame.GameEntities.Powerups;

import com.example.tankwargame.GameEntities.GameObject;

public abstract class Powerup extends GameObject {

    protected int duration = 5;

    public int getDuration() {
        return duration;
    }

    public void lowerDuration(){
        this.duration = duration - 1;
    }

    public void raiseDuration(){
        this.duration = duration + 1;
    }
}
