package io.github.aimalshah.game.towerofmordoria.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import io.github.aimalshah.game.towerofmordoria.enemies.Enemy;

public class Projectile {
    private Vector2 position;
    private Vector2 targetPosition;
    private float speed = 10f;
    private Texture texture;
    private Enemy target;

    public Projectile(Vector2 position, Enemy target){
        this.position = new Vector2(position);
        this.target = target;
        this.targetPosition = target.getPosition();
        this.texture = new Texture("arrow.png");
    }

    public  void update(float delta){
        if(target != null){
            targetPosition = target.getPosition();
        }

        Vector2 direction = new Vector2(targetPosition).sub(position).nor();
        position.mulAdd(direction, speed * delta);
    }

    public void render(SpriteBatch batch){
        batch.draw(texture, position.x, position.y, 0.3f,0.3f);
    }

    public boolean hasHitTarget(){
        return  target != null && position.dst(target.getPosition()) < 0.5f;
    }

    public Enemy getTarget(){
        return target;
    }

    public void dispose(){
        texture.dispose();
    }
}
