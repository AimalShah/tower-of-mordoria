package io.github.aimalshah.game.towerofmordoria.towers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import io.github.aimalshah.game.towerofmordoria.enemies.Enemy;

import java.util.ArrayList;
import java.util.List;

public class TowerManager {
    private List<Tower> towers = new ArrayList<>();
    private Texture towerTexture;

    private boolean placing = false;
    private Vector2 previewPosition = new Vector2();




    public  TowerManager(Texture towerTexture){
        this.towerTexture = towerTexture;
    }

    public void update(float delta , List<Enemy> enemies){
        for(Tower tower : towers){
            tower.update(delta, enemies);
        }
    }

    public void render(SpriteBatch batch){
        for(Tower tower : towers){
            tower.render(batch);
        }

        if(placing){
            batch.draw(new Texture("placement_tower.png"), previewPosition.x, previewPosition.y , 1,2);
        }
    }

    public void renderRanges( ShapeRenderer shapeRenderer) {
        for(Tower tower : towers) {
            tower.renderRange(shapeRenderer);
        }

        if(placing){
            shapeRenderer.setColor(Color.RED);
            shapeRenderer.circle(previewPosition.x + 0.5f, previewPosition.y + 1, 5f);
        }
    }

    public void placeTower(float x , float y){
        towers.add(new ArcherTower(towerTexture, x,y));
        placing = false;
    }

    public void handleInput(Vector2 worldTouch){
        float gridX = (int)(worldTouch.x);
        float gridY = (int)(worldTouch.y);
        placeTower(gridX,gridY);
    }

    public void startPlacing() {
        placing = true;
    }

    public void cancelPlacing() {
        placing = false;
    }

    public boolean isPlacing() {
        return placing;
    }

    public void updatePreviewPosition(Vector2 worldPosition) {
        previewPosition.set((int)(worldPosition.x), (int)(worldPosition.y));
    }
}
