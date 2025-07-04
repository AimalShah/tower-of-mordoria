package io.github.aimalshah.game.towerofmordoria.enemies;

import com.sun.org.apache.xpath.internal.operations.Or;

public class Orc extends Enemy{


    public Orc(){
        this.health = 30;
        this.speed = 2.5f;
    }

    @Override
    protected  String getTexturePath() {
        return "orc3_walk_attack_full.png";
    }

    @Override
    protected int getFrameRow(){
        return 3;
    }

    @Override
    protected float getHeight(){
        return 1.7f;
    }

    @Override
    protected  float getWidth(){
        return 1.7f;
    }

    @Override
    protected  float getFrameTiming(){
        return 0.15f;
    }
}
