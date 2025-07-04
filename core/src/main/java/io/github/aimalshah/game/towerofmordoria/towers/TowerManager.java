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
    private String type;
    private boolean placing = false;
    private Vector2 previewPosition = new Vector2();
    private EnemyKillListner killListener;




    public  TowerManager(Texture towerTexture , String type){
        this.towerTexture = towerTexture;
        this.type = type;
    }

    public void update(float delta , List<Enemy> enemies){
        for(Tower tower : towers){
            tower.update(delta, enemies);
        }
    }
    public void setKillListener(EnemyKillListner listener) {
        this.killListener = listener;
    }

    public void render(SpriteBatch batch){
        for(Tower tower : towers){
            tower.render(batch);
        }

        if(placing){
            batch.draw(towerTexture, previewPosition.x, previewPosition.y , 1,2);
        }
    }

    public void renderRanges( ShapeRenderer shapeRenderer , float range) {

        if(placing){
            shapeRenderer.setColor(Color.RED);
            shapeRenderer.circle(previewPosition.x + 0.5f, previewPosition.y + 1, range);
        }
    }

    public void placeTower(float x , float y){
        Tower tower = null;

        if (type.equals("Archer")) {
            tower = new ArcherTower(x, y, 5);
        } else if (type.equals("Wizard")) {
            tower = new WizardTower(x, y, 15);
        }

        if (tower != null) {
            if (killListener != null) {
                tower.setKillListener(killListener);
            }
            towers.add(tower);
        }

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
