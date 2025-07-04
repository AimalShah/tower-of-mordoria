package io.github.aimalshah.game.towerofmordoria;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import io.github.aimalshah.game.towerofmordoria.databasemanager.DBConnection.DAL;
import io.github.aimalshah.game.towerofmordoria.databasemanager.DBConnection.User;

import java.sql.SQLException;

public class VictoryScreen implements Screen {

    private final Main game;
    int id;
    int score;

    private SpriteBatch batch;
    private Texture background;
    private Stage stage;
    private Skin skin;
    DAL db = new DAL();

    public VictoryScreen(Main game , int id , int score) {
        this.game = game;
        this.id = id;
        this.score = score;
    }

    @Override
    public void show() {
        skin = new Skin(Gdx.files.internal("skin/craftacular-ui.json"));
        try {
            TextButton playBtn = new TextButton("Play Again" , skin);


            User user = db.getUserById(id);

            playBtn.addListener(new ClickListener(){
                public void clicked(InputEvent event, float x, float y) {
                    game.setScreen(new GameScreen(game ,user.getUsername(), user.getAvatar(), user.getId(), user.getHighScore()));
                }
            });

            if(score > user.getHighScore()){
                db.updateHighScore(id , score);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        batch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("victory_background.png")); // Rename your generated image to this
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);



        Label victoryLabel = new Label("You Win!", skin, "title");
        TextButton backBtn = new TextButton("Back to Menu", skin);


        backBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
            }
        });

        Table table = new Table();
        table.setFillParent(true);
        table.center();
        table.add(victoryLabel).padBottom(40).row();
        table.add(backBtn).width(400).height(60);
        table.row();


        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
        stage.dispose();
    }
}
