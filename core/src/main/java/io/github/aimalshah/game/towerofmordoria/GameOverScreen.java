package io.github.aimalshah.game.towerofmordoria;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
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
    private Dialog dialog;
    private ScreenViewport screenViewport;
    private Texture backgorund;

    public GameOverScreen(final Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        screenViewport = new ScreenViewport();
        stage = new Stage(screenViewport, batch);
        Gdx.input.setInputProcessor(stage);
        backgorund = new Texture(Gdx.files.internal("background_gameover_image.png"));

        skin = new Skin(Gdx.files.internal("skin/craftacular-ui.json"));
        dialog = new Dialog("Game Over", skin);
        dialog.getTitleLabel().setAlignment(Align.center);
        Table table = dialog.getContentTable();
        table.pad(20);

        table.row();
        table.defaults().width(250);
        TextButton MenuBtn = new TextButton("Main Menu", skin);
        MenuBtn.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
            }
        });
        table.add(MenuBtn);
        table.row();
        TextButton RestartBtn = new TextButton("Restart", skin);
        RestartBtn.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {

            }
        });
        table.add(RestartBtn);
        table.row();

        TextButton QuitBtn = new TextButton("Quit", skin);
        QuitBtn.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        table.add(QuitBtn);
        dialog.show(stage);

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);
        screenViewport.apply();

        batch.begin();
        batch.draw(backgorund,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.end();

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        screenViewport.update(width, height, true);
        stage.getViewport().update(width, height, true);
        dialog.setPosition(Math.round((stage.getWidth() - dialog.getWidth()) / 2), Math.round(stage.getHeight() - dialog.getHeight()) / 2f);
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
        batch.dispose();
        stage.dispose();
        skin.dispose();
    }
}
