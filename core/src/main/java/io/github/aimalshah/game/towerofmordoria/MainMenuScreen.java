package io.github.aimalshah.game.towerofmordoria;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainMenuScreen implements Screen {

    private final Main game;// de ke zama sa kar neshta(ZEMOON)
    private SpriteBatch spritebatch;
    private ScreenViewport screenViewport;
    private Skin skin;
    private Stage stage;
    private Dialog dialog;



    public MainMenuScreen(final Main game) {
       this.game = game;
    }

    @Override
    public void show() {
        // net ke ch create ye de ke show ye
        SpriteBatch spriteBatch = new SpriteBatch();
        skin = new Skin(Gdx.files.internal("ui/Particle Park UI.json"));
     screenViewport = new ScreenViewport();
     stage = new Stage(screenViewport,spriteBatch);
     Gdx.input.setInputProcessor(stage);


     dialog = new Dialog("Game Menu ",skin);
     dialog.getTitleLabel().setAlignment(Align.center);
     Table table = dialog.getContentTable();
     table.pad(10);

     table.row();
     table.defaults().width(150);
     TextButton textButton=new TextButton("play",skin);
     table.add(textButton);

     table.row();
     textButton = new TextButton("Options",skin);
     table.add(textButton);

     table.row();
     textButton=new TextButton("Quit", skin);
     table.add(textButton);
     dialog.show(stage);


    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.DARK_GRAY);
        screenViewport.apply();
        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    screenViewport.update(width,height,true);
    dialog.setPosition(Math.round((stage.getWidth() - dialog.getWidth()) / 2), Math.round((stage.getHeight() - dialog.getHeight()) / 2));

    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
       spritebatch.dispose();
       stage.dispose();
       skin.dispose();
    }
}
