package io.github.aimalshah.game.towerofmordoria;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import static com.badlogic.gdx.Gdx.gl;


/**
 * First screen of the application. Displayed after the application is created.
 */

public class GameScreen implements Screen {


    final Main game;
    private static final int FRAME_COLS = 6, FRAME_ROWS = 4;

    private FitViewport viewport;
    private Texture map;
    private Texture enemyTextureSheet;
    Animation<TextureRegion> walkAnimation;
    private Sprite enemy;

    float stateTime;
    float enemyX=0;


    private SpriteBatch batch;


    public GameScreen(final Main game) {
        this.game = game;

    }

    @Override
    public void show() {
        viewport = new FitViewport(12, 9);

        map = new Texture("Maps/Map1.png");
        enemyTextureSheet = new Texture("Test/Walk/Vampires1_Walk_full.png");

        TextureRegion[][] tmp = TextureRegion.split(enemyTextureSheet ,
            enemyTextureSheet.getWidth() / FRAME_COLS,
            enemyTextureSheet.getHeight() / FRAME_ROWS);

        TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];

        int index = 0;

        for(int i = 0; i<FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                walkFrames[index++] = tmp[3][j];
            }
        }

        walkAnimation = new Animation<TextureRegion>(0.25f, walkFrames);
        stateTime = 0f;

        batch = new SpriteBatch();

        enemy = new Sprite(walkFrames[0]);
        enemy.setSize(2f,2f);
        enemy.setPosition(0,3.5f);




    }


    @Override
    public void render(float delta) {
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        ScreenUtils.clear(Color.BLACK);

        stateTime += Gdx.graphics.getDeltaTime();

        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);

        TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime, true);
        enemy.setRegion(currentFrame);

        enemyX += 0.5f * delta;
        enemy.setPosition(enemyX,3.5f);
        batch.begin();

        float deltaT = Gdx.graphics.getDeltaTime();
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();
        batch.draw(map, 0, 0, worldWidth, worldHeight);


        enemy.draw(batch);

        batch.end();





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
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {
        map.dispose();
        batch.dispose();

    }


}

