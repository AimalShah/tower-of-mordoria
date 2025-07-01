package io.github.aimalshah.game.towerofmordoria.towers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import io.github.aimalshah.game.towerofmordoria.enemies.Enemy;
import io.github.aimalshah.game.towerofmordoria.projectiles.Projectile;

import java.util.ArrayList;
import java.util.List;

public abstract class Tower {
    protected Sprite sprite;
    protected Vector2 position;
    protected Animation<TextureRegion> animation;
    protected float stateTime;
    protected float x, y;
    protected  int damage;
    protected float range = 5f;
    protected float fireCoolDown = 1.0f;
    protected float fireTimer = 0f;
    protected List<Projectile> projectiles = new ArrayList<>();
    protected Sound hitSound  = Gdx.audio.newSound(Gdx.files.internal("sounds/arrow-sound.mp3"));


    public Tower(String towerTexture, float x, float y , int damage) {
        this.position = new Vector2(x, y);
        this.stateTime = 0f;
        this.sprite = new Sprite(new Texture(towerTexture));
        this.sprite.setPosition(x, y);
        this.sprite.setSize(1f, 2f);
        this.damage = damage;
    }

    public void update(float delta , List<Enemy> enemies) {
        stateTime += delta;
        fireTimer += delta;

        if(fireTimer >= fireCoolDown){
            for(Enemy enemy : enemies){
                if(isInRange(enemy.getPosition())){
                    hitSound.play();
                    projectiles.add(new Projectile(new Vector2(position.x, position.y + sprite.getHeight()/2), enemy));
                    fireTimer = 0;
                    break;
                }
            }
        }

        for (int i = 0; i<projectiles.size(); i++) {
            Projectile p = projectiles.get(i);
            p.update(delta);
            if (p.hasHitTarget()) {
                System.out.println("The Enemy Has Been Hit.");
                p.getTarget().takeDamage(damage);

                if(p.getTarget().isDead()){
                    p.getTarget().dispose();
                    enemies.remove(p.getTarget());
                }
                projectiles.remove(i);
                i--;
            }
        }
    }

    public void render(SpriteBatch batch) {
        sprite.draw(batch);
        for(Projectile p : projectiles){
            p.render(batch);
        }
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getX() {
        return x;
    }



    public float getY() {
        return y;
    }

    public float getRange(){
        return  range;
    }

    public void renderRange(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.circle(position.x + sprite.getWidth()/2, position.y + sprite.getHeight()/2, getRange());
    }

    public boolean isInRange(Vector2 enemyPos){
        float CenterX = position.x + sprite.getWidth()/2;
        float CenterY = position.y + sprite.getHeight()/2;

        return CenterX != 0 && CenterY != 0 && enemyPos.dst(CenterX, CenterY) <= getRange();
    }

}
