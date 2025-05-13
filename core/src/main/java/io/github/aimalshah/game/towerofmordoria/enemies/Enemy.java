package io.github.aimalshah.game.towerofmordoria.enemies;


import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Enemy {

    protected float posX , posY;
    protected float speed;
    protected  int health;
    protected  int maxHealth;
    protected TextureRegion currentFrame;
    protected Animation<TextureRegion> walkAnimation;
    protected float stateTime;

    protected  boolean isDead = false;

    public  Enemy(float x, float y, float speed, int maxHealth, Animation<TextureRegion> walkAnimation ){
        this.posX = x;
        this.posY = y;
        this.speed = speed;
        this.health = maxHealth;
        this.walkAnimation = walkAnimation;
        this.stateTime = 0f;
    }




}
