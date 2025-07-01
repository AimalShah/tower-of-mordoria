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
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.aimalshah.game.towerofmordoria.databasemanager.DBConnection.DAL;
import io.github.aimalshah.game.towerofmordoria.databasemanager.DBConnection.User;

public class ProfileScreen implements Screen {
    final Main game;

    private Stage stage;
    private Skin skin;
    private TextField usernameField;
    private String selectedAvatar = "avatar1.png";
    SpriteBatch spriteBatch;
    Dialog dialog;
    Texture bgTexture;


    DAL db = new DAL();

    public ProfileScreen(final Main game){
        this.game = game;
    }

    @Override
    public void show() {
       spriteBatch = new SpriteBatch();
        stage = new Stage(new ScreenViewport() , spriteBatch);
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("skin/craftacular-ui.json"));


         bgTexture = new Texture(Gdx.files.internal("background_menu_image.png"));


         dialog = new Dialog("New Profile" , skin);
         dialog.getTitleLabel().setAlignment(Align.center);
         Table content = dialog.getContentTable();
         content.pad(20);
         content.row();
         content.defaults().width(450);
         Label nameLabel = new Label("Enter Username:", skin);
         usernameField = new TextField("", skin);

         content.add(nameLabel).pad(10);
         content.row();
         content.add(usernameField).width(450).padBottom(20);
         content.row();

         Label chooseLabel = new Label("Select Avatar:", skin);
         content.add(chooseLabel).padBottom(10);
         content.row();

         Table avatarTable = new Table();
         String[] avatarFiles = {"avatar1.png", "avatar2.png", "avatar3.png"};

         for (String file : avatarFiles) {
            Texture texture = new Texture(Gdx.files.internal("avatars/" + file));
            ImageButton avatarButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(texture)));

            avatarButton.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    selectedAvatar = file;
                    System.out.println("Selected : " + selectedAvatar);
                }
            });

            avatarButton.setSize(100 ,100);

            avatarTable.add(avatarButton).pad(5).size(84);
         }

        content.add(avatarTable).center();
        content.row();
        TextButton submitBtn = new TextButton("Create Profile", skin);

        submitBtn.addListener(new ClickListener(){
           public void clicked(InputEvent event, float x, float y){
               System.out.println(usernameField.getText());
               System.out.println(selectedAvatar);
               User user = new User(usernameField.getText(), selectedAvatar , 0);
               db.addUser(user);
               System.out.println("Profile Created Successfully");
                game.setScreen(new GameScreen(game, user.getUsername(), user.getAvatar(), user.getId(), user.getHighScore()));
            }
        });


         content.add(submitBtn);

         content.center();
        dialog.show(stage);
    }


    @Override public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        spriteBatch.draw(bgTexture, 0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        spriteBatch.end();
        stage.act(delta);
        stage.draw();


    }



    @Override public void resize(int width, int height) { stage.getViewport().update(width, height, true); }
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() { stage.dispose(); }
}
