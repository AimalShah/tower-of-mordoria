package io.github.aimalshah.game.towerofmordoria.enemies;

public class Orc extends Enemy{

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
