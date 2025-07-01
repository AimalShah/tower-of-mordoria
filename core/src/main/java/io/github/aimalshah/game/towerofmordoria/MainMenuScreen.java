package io.github.aimalshah.game.towerofmordoria;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.aimalshah.game.towerofmordoria.databasemanager.DBConnection.DBConnection;

public class MainMenuScreen implements Screen {

    private final Main game;// de ke zama sa kar neshta(ZEMOON)
    private SpriteBatch spriteBatch;
    private ScreenViewport screenViewport;
    private Skin skin;
    private Stage stage;
    private Dialog dialog;
    private Texture background;


    public MainMenuScreen(final Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        // docs ke ch create ye de ke show ye
        spriteBatch = new SpriteBatch();
        skin = new Skin(Gdx.files.internal("skin/craftacular-ui.json"));
        screenViewport = new ScreenViewport();
        stage = new Stage(screenViewport, spriteBatch);
        Gdx.input.setInputProcessor(stage);
        background = new Texture(Gdx.files.internal("main_image.png"));


        dialog = new Dialog("Game Menu ", skin);
        dialog.getTitleLabel().setAlignment(Align.center);
        Table table = dialog.getContentTable();
        table.pad(10);

        table.row();
        table.defaults().width(250);
        TextButton playButton = new TextButton("Load Game", skin);
        playButton.addListener(new ClickListener() {
            public  void clicked(InputEvent event, float x, float y){
                game.setScreen(new UserScreen(game));
            }
        });

        TextButton textButton = new TextButton("Options", skin);
        TextButton newGameButton = new TextButton("New Game" , skin);
        newGameButton.addListener(new ClickListener(){
            public  void clicked(InputEvent event, float x, float y){
                game.setScreen(new ProfileScreen(game));
            }
        });


        table.add(newGameButton);

        table.row();



        table.add(playButton);
        table.row();
        table.add(textButton);

        table.row();
       TextButton quitButton = new TextButton("Quit", skin);
       quitButton.addListener(new ClickListener() {
           public void clicked(InputEvent event, float x, float y){
               Gdx.app.exit();
           }
       });
        table.add(quitButton);

        dialog.show(stage);


    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.DARK_GRAY);
        screenViewport.apply();

        spriteBatch.begin();
        spriteBatch.draw(background, 0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        spriteBatch.end();

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

        screenViewport.update(width, height, true);
        dialog.setPosition(Math.round((stage.getWidth() - dialog.getWidth()) / 2), Math.round((stage.getHeight() - dialog.getHeight()) / 2));

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
        spriteBatch.dispose();
        stage.dispose();
        skin.dispose();
    }
}
