package io.github.aimalshah.game.towerofmordoria;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class GameOverScreen implements Screen {

    final Main game;
    private SpriteBatch batch;
    private BitmapFont font;
    private GlyphLayout layout;

    private Stage stage;
    private Skin skin;

    public GameOverScreen(final Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();

        font = new BitmapFont();
        font.getData().setScale(5f);
        font.setColor(Color.RED);

        layout = new GlyphLayout();
        layout.setText(font, "Game Over");

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("uiskin.json"));

        TextButton restartButton = new TextButton("Restart", skin);
        restartButton.setSize(200, 60);
        restartButton.setPosition(
            (Gdx.graphics.getWidth() - restartButton.getWidth()) / 2,
            Gdx.graphics.getHeight() / 2f - 100
        );
        TextButton startMenuButton = new TextButton("Go Start Menu", skin);
        startMenuButton.setSize(200,60);

        startMenuButton.setPosition(
            (Gdx.graphics.getWidth() - startMenuButton.getWidth()) / 3f,
            Gdx.graphics.getHeight() / 2f - 100
        );

        restartButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
            }
        });

        startMenuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new MainMenuScreen(game));
                dispose();
            }
        });
        stage.addActor(restartButton);
        stage.addActor(startMenuButton);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);

        batch.begin();
        float x = (Gdx.graphics.getWidth() - layout.width) / 2;
        float y = (Gdx.graphics.getHeight() + layout.height) / 2 + 100;
        font.draw(batch, layout, x, y);
        batch.end();

        stage.act(delta);
        stage.draw();
    }

    @Override public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        stage.dispose();
        skin.dispose();
    }
}
