package io.github.aimalshah.game.towerofmordoria;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.aimalshah.game.towerofmordoria.enemies.Enemy;
import io.github.aimalshah.game.towerofmordoria.enemies.Orc;
import io.github.aimalshah.game.towerofmordoria.towers.TowerManager;

import java.util.ArrayList;
import java.util.List;

import static com.badlogic.gdx.Gdx.gl;


/**
 * First screen of the application. Displayed after the application is created.
 */

public class GameScreen implements Screen {


    final Main game;

    private FitViewport viewport;
    private ScreenViewport uiViewPort;

    private Texture map;

    private SpriteBatch batch;
    private List<Enemy> enemies;
    private float spawnTimer = 0f;
    private final float spawnInterval = 1f;
    private final int maximumEnemies = 5;
    private int enemiesSpawned = 0;
    private TowerManager towerManager;
    private Texture towerTexture;
    private ShapeRenderer shapeRenderer;

    private BitmapFont font;
    private Stage uiStage;
    private Skin skin;
    private Label towerLabel;
    private TextButton selectTowerButton;


    public GameScreen(final Main game) {
        this.game = game;

    }

    @Override
    public void show() {
        viewport = new FitViewport(36, 24);

        map = new Texture("Maps/map.png");
        batch = new SpriteBatch();

        enemies = new ArrayList<>();

        towerTexture = new Texture("archer_tower.png");
        towerManager = new TowerManager(towerTexture);
        shapeRenderer = new ShapeRenderer();

    }


    @Override
    public void render(float delta) {

        input();
        draw();
        logic(delta);


    }

    private void input() {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            towerManager.startPlacing();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            towerManager.cancelPlacing();
        }

    }

    private void logic(float delta) {

        if(enemiesSpawned == maximumEnemies){
            if(enemies.isEmpty()){
                this.game.setScreen(new GameOverScreen(game));
            }
        }
        //Enemy Logic
        if (enemiesSpawned < maximumEnemies) {
            spawnTimer += delta;
            if (spawnTimer >= spawnInterval) {
                spawnEnemy();
                spawnTimer = 0;
            }
        }

        for(int i = 0; i < enemies.size(); i++){
            Enemy enemy = enemies.get(i);

            if(enemy.getX() > viewport.getWorldWidth()){
                game.setScreen(new GameOverScreen(game));
            }
        }

        for (Enemy enemy : enemies) {
            enemy.update(delta);
        }


        //Tower Logic
        if (towerManager.isPlacing()) {
            Vector2 screenTouch = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            Vector2 worldTouch = viewport.unproject(screenTouch);
            towerManager.updatePreviewPosition(worldTouch);

            if (Gdx.input.justTouched()) {
                towerManager.handleInput(worldTouch);
            }
        }
        towerManager.update(delta , enemies);


    }

    private void draw() {
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        ScreenUtils.clear(Color.BLACK);

        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);

        //Batch Begin
        batch.begin();

        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();
        batch.draw(map, 0, 0, worldWidth, worldHeight);


        towerManager.render(batch);


        for (Enemy enemy : enemies) {
            enemy.render(batch);
        }




        batch.end();

        shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        towerManager.renderRanges(shapeRenderer);
        shapeRenderer.end();
        //Batch End

    }


    private void spawnEnemy() {
        Enemy enemy = new Orc();
        enemies.add(enemy);
        enemiesSpawned++;
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        batch.dispose();
        for (Enemy enemy : enemies) {
            enemy.dispose();
        }
        towerTexture.dispose();
        shapeRenderer.dispose();
        shapeRenderer.dispose();


    }


}

