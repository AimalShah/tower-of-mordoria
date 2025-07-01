package io.github.aimalshah.game.towerofmordoria;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.aimalshah.game.towerofmordoria.databasemanager.DBConnection.DAL;
import io.github.aimalshah.game.towerofmordoria.databasemanager.DBConnection.User;

import java.util.ArrayList;
import java.util.List;

public class UserScreen implements Screen {
    final Main game;
    DAL db = new DAL();
    ArrayList<User> users;
    SpriteBatch batch;
    Stage stage;
    Dialog dialog;
    Skin skin;


    public UserScreen(final Main game){
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        skin = new Skin(Gdx.files.internal("skin/craftacular-ui.json"));
        stage = new Stage(new ScreenViewport(), batch);
        Gdx.input.setInputProcessor(stage);

        dialog = new Dialog("" , skin);
        dialog.getTitleLabel().setAlignment(Align.center);
        Table table =  dialog.getContentTable();
        users = db.getAllUsers();
        table.add(new Label("Id " , skin)).pad(20);
        table.add(new Label("avatar" , skin)).pad(20);
        table.add(new Label("user name" , skin)).pad(20);
        table.add(new Label("high Score", skin)).pad(20);
        table.add(new Label("Actions" , skin)).pad(20);
        table.row();
        for(User user : users){
            String id = ""+user.getId();
            String score = ""+user.getHighScore();
            TextButton loadBtn = new TextButton("Load" , skin);
            loadBtn.addListener(new ClickListener(){
                public void clicked(InputEvent event , float x,  float y){
                    game.setScreen(new GameScreen(game , user.getUsername(), user.getAvatar(), user.getId(), user.getHighScore()));
                }
            });
            Texture texture = new Texture(Gdx.files.internal("avatars/" + user.getAvatar()));
            TextureRegionDrawable avtImage = new TextureRegionDrawable( new TextureRegion(texture));
            table.add(new Label(id, skin)).pad(20);
            table.add(new ImageButton(new TextureRegionDrawable(new TextureRegion(texture)))).pad(20);
            table.add(new Label(user.getUsername(),skin)).pad(20);
            table.add(new Label(score,skin)).pad(20);
            table.add(loadBtn).pad(20);
            table.add().pad(20);

            table.row();
            System.out.println("USERNAME :  " + user.getUsername());
        }
        table.center();
        dialog.show(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.end();
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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

    }
}
