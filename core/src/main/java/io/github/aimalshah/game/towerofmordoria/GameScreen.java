package io.github.aimalshah.game.towerofmordoria;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.aimalshah.game.towerofmordoria.enemies.Enemy;
import io.github.aimalshah.game.towerofmordoria.enemies.Orc;
import io.github.aimalshah.game.towerofmordoria.enemies.Vampire;
import io.github.aimalshah.game.towerofmordoria.towers.TowerManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.badlogic.gdx.Gdx.gl;
import static com.badlogic.gdx.Gdx.graphics;


public class GameScreen implements Screen {


    final Main game;
    String username;
    String avatar;
    int high_score;
    int id;
    int points = 200;

    private FitViewport viewport;
    private ScreenViewport uiViewPort;
    private Texture map;
    private SpriteBatch batch;
    private List<Enemy> enemies;
    private float spawnTimer = 0f;
    private final float spawnInterval = 1f;
    private final int maximumEnemies = 5;
    private int enemiesSpawned = 0;
    private TowerManager archerTowerManager;
    private TowerManager wizardTowerManger;
    private Texture archerTowerTexture;
    private ShapeRenderer shapeRenderer;
    private BitmapFont font;
    private Stage uiStage;
    private Skin skin;
    private Label towerLabel;
    private TextButton selectTowerButton;
    private Boolean isWaveStart = false;
    private int currentWave = 1;
    private int totalWaves = 5;
    private int playerHelath = 100;
    private ProgressBar playerHealthBar;
    private Dialog dialog;


    public GameScreen(final Main game, String username, String avatar, int id, int high_score) {

        this.game = game;
        this.id = id;
        this.avatar = avatar;
        this.high_score = high_score;
        this.username = username;

    }

    @Override
    public void show() {
        Texture archerTowerTexture = new Texture(Gdx.files.internal("tower_card.png"));
        Texture wizardTowerTexture = new Texture(Gdx.files.internal("wizard_card.png"));

        skin = new Skin(Gdx.files.internal("skin/craftacular-ui.json"));
        game.setBackGroundMusic("music/dark_dungun.mp3");

        viewport = new FitViewport(36, 24);
        uiViewPort = new ScreenViewport();
        uiStage = new Stage(uiViewPort);
        Gdx.input.setInputProcessor(uiStage);


        TextureRegionDrawable archerTowerDrawable = new TextureRegionDrawable(new TextureRegion(archerTowerTexture));
        TextureRegionDrawable wizardTowerDrawable = new TextureRegionDrawable(new TextureRegion(wizardTowerTexture));


        map = new Texture("Maps/map.png");
        batch = new SpriteBatch();

        enemies = new ArrayList<>();


        archerTowerManager = new TowerManager(new Texture("placement_tower.png"), "Archer");
        wizardTowerManger = new TowerManager(new Texture("placement_wizard_tower.png"), "Wizard");

        shapeRenderer = new ShapeRenderer();

        ImageButton archerTowerBtn = new ImageButton(archerTowerDrawable);
        ImageButton wizardTowerBtn = new ImageButton(wizardTowerDrawable);

        TextButton startWaveBtn = new TextButton("Start Wave", skin);


        ProgressBar.ProgressBarStyle style = skin.get("default-horizontal", ProgressBar.ProgressBarStyle.class);
        style.background.setMinHeight(20);
        style.knobBefore.setMinHeight(20);

        Label userNameLabel = new Label(username, skin);
        Texture avatarTexture = new Texture(Gdx.files.internal("avatars/" + avatar));
        TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(avatarTexture));
        ImageButton avatarImage = new ImageButton(drawable);
        avatarImage.setSize(1, 1);

        Image currencyImage = new Image(new Texture(Gdx.files.internal("diamond.png")));
        currencyImage.setSize(1.1f, 1.1f);

        playerHealthBar = new ProgressBar(0f, 100f, 10f, false, skin);
        playerHealthBar.setValue(playerHelath); // current health
        playerHealthBar.setAnimateDuration(0.25f);
        playerHealthBar.setStyle(style);

        archerTowerBtn.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                archerTowerManager.startPlacing();
            }
        });
        wizardTowerBtn.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                wizardTowerManger.startPlacing();
            }
        });

        Label archerTowerPrice = new Label("PRICE : 50 ", skin);


        archerTowerBtn.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, @Null Actor fromActor) {
                archerTowerBtn.setSize(240, 240);
                System.out.println("Hover!!!");
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, @Null Actor toActor) {
                archerTowerBtn.setSize(200, 220);
                System.out.println("Hover Out!!!");
            }
        });
        wizardTowerBtn.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, @Null Actor fromActor) {
                wizardTowerBtn.setSize(240, 240);
                System.out.println("Hover!!!");
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, @Null Actor toActor) {
                wizardTowerBtn.setSize(200, 220);
                System.out.println("Hover Out!!!");
            }
        });

        startWaveBtn.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                isWaveStart = true;
                startWaveBtn.remove();
            }
        });


        Table tableTop = new Table(skin);
        Table tableBottom = new Table(skin);
        Table tableRight = new Table(skin);
        Table tableTopRight = new Table(skin);

        tableTopRight.setFillParent(true);
        tableTopRight.pad(4);
        tableTopRight.add(new Label("HIGH SCORE : " + high_score,skin));
        tableTopRight.top().right();



        tableTop.setFillParent(true);
        tableTop.top().left();

        tableTop.pad(4);
        tableTop.add(avatarImage).left().pad(0).space(20);
        tableTop.add(userNameLabel).width(100).left().pad(0).space(0);
        tableTop.row();
        tableTop.add(currencyImage).size(80, 80).left().padBottom(2).padTop(2).pad(0).space(0);
        tableTop.add(new Label("" + points, skin)).left().space(0);
        tableTop.row();
        tableTop.add(playerHealthBar).size(260, 20).pad(0).colspan(2);



        tableBottom.setFillParent(true);
        tableBottom.pad(2);
        tableBottom.add(archerTowerPrice);
        tableBottom.add(new Label("PRICE : 100",skin));
        tableBottom.row();
        tableBottom.add(archerTowerBtn).size(200, 220).pad(20);
        tableBottom.add(wizardTowerBtn).size(200, 220).pad(20);
        tableBottom.left().bottom();

        tableRight.setFillParent(true);
        tableRight.add(startWaveBtn).pad(10);
        tableRight.right().bottom();


        uiStage.addActor(tableRight);
        uiStage.addActor(tableTop);
        uiStage.addActor(tableBottom);
        uiStage.addActor(tableTopRight);

    }


    @Override
    public void render(float delta) {
        input();
        logic(delta);
        draw(delta);


    }

    private void input() {

        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            archerTowerManager.cancelPlacing();
            wizardTowerManger.cancelPlacing();
        }

    }

    private void logic(float delta) {

        if (enemiesSpawned == maximumEnemies) {
            if (enemies.isEmpty()) {
                this.game.setScreen(new GameOverScreen(game));
            }
        }

        if (isWaveStart) {
            startWave(delta);
        }

        Iterator<Enemy> iterator = enemies.iterator();
        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();
            if (enemy.getX() > viewport.getWorldWidth()) {
                playerHelath = Math.max(0, playerHelath - 20);
                playerHealthBar.setValue(playerHelath);
                enemy.dispose();
                iterator.remove();
            }
        }

        for (Enemy enemy : enemies) {
            enemy.update(delta);
        }


        //Tower Logic
        if (archerTowerManager.isPlacing()) {
            Vector2 screenTouch = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            Vector2 worldTouch = viewport.unproject(screenTouch);
            archerTowerManager.updatePreviewPosition(worldTouch);

            if (Gdx.input.justTouched()) {
                archerTowerManager.handleInput(worldTouch);
            }
        }

        if (wizardTowerManger.isPlacing()) {
            Vector2 screenTouch = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            Vector2 worldTouch = viewport.unproject(screenTouch);
            wizardTowerManger.updatePreviewPosition(worldTouch);

            if (Gdx.input.justTouched()) {
                wizardTowerManger.handleInput(worldTouch);
            }
        }
        archerTowerManager.update(delta, enemies);
        wizardTowerManger.update(delta, enemies);


    }

    private void draw(float delta) {
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        ScreenUtils.clear(Color.BLACK);

        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);

        //Batch Begin
        batch.begin();
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();
        batch.draw(map, 0, 0, worldWidth, worldHeight);


        archerTowerManager.render(batch);
        wizardTowerManger.render(batch);


        for (Enemy enemy : enemies) {
            enemy.render(batch);
        }
        batch.end();

        shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        archerTowerManager.renderRanges(shapeRenderer);
        wizardTowerManger.renderRanges(shapeRenderer);
        shapeRenderer.end();

        uiStage.act(graphics.getDeltaTime());
        uiStage.draw();

    }

    private void startWave(float delta) {
        if (enemiesSpawned < maximumEnemies) {
            spawnTimer += delta;
            if (spawnTimer >= spawnInterval) {
                spawnEnemy();
                spawnTimer = 0;
            }
        }
    }

    private void spawnEnemy() {
        Enemy enemy = new Vampire();
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
        shapeRenderer.dispose();
        shapeRenderer.dispose();


    }


}

