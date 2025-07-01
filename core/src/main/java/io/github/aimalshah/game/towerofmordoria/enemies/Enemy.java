package io.github.aimalshah.game.towerofmordoria.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public abstract class Enemy {
    protected Sprite sprite;
    protected Animation<TextureRegion> walkAnimation;
    protected float stateTime = 0f;
    protected float x = 0f;
    protected float y = 11f;
    public float health = 100f;
    protected Texture texture;
    protected static int FRAME_COLS = 6;
    protected static int FRAME_ROWS = 4;


    public Enemy() {
        init();
    }

    protected void init() {
        texture = new Texture(getTexturePath());
        TextureRegion[][] tmp = TextureRegion.split(
            texture,
            texture.getWidth() / FRAME_COLS,
            texture.getHeight() / FRAME_ROWS
        );

        TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS];
        int row = getFrameRow();
        int index = 0;

        for (int i = 0; i < FRAME_COLS; i++) {
            walkFrames[index++] = tmp[row][i];
        }

        walkAnimation = new Animation<>(getFrameTiming(), walkFrames);
        sprite = new Sprite(walkFrames[0]);
        sprite.setSize(getWidth(), getHeight());
        sprite.setPosition(x, y);


    }

    public void render(SpriteBatch batch) {
        sprite.draw(batch);
    }


    public void update(float delta) {
        stateTime += delta;
        TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime, true);
        sprite.setRegion(currentFrame);
        x += 4.5f * delta;
        sprite.setPosition(x, y);
    }

    public void dispose() {
        texture.dispose();
    }

    public float getX() {
        return sprite.getX();
    }

    public Vector2 getPosition() {
        return new Vector2(getX(), y);
    }

    public  void takeDamage(int amount){
        health -= amount;
        if(health <= 0){
            dispose();
        }
    }

    public boolean isDead(){
        return health <= 0;
    }
    protected abstract String getTexturePath();

    protected abstract int getFrameRow();

    protected abstract float getFrameTiming();

    protected abstract float getWidth();

    protected abstract float getHeight();

}
