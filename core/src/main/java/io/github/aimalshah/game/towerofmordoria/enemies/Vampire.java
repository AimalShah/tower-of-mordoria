package io.github.aimalshah.game.towerofmordoria.enemies;

public class Vampire extends Enemy {
    protected String getTexturePath(){ return  "Test/Walk/Vampires1_Walk_full.png";}
    protected int getFrameRow() { return 3; }
    protected float getFrameTiming() { return 0.2f; }
    protected float getWidth() { return 2.0f; }
    protected float getHeight() { return 2.0f; }
}

