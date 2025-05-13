package io.github.aimalshah.game.towerofmordoria;


import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/** First screen of the application. Displayed after the application is created. */
public class GameScreen implements Screen {
    final Main game;
    private OrthographicCamera camera;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;
    Texture tileset;

    private final int MAP_WIDTH = 1024;  // 32 tiles * 32 pixels
    private final int MAP_HEIGHT = 1024;




    public GameScreen(final Main game){
        this.game = game;

    }

    @Override
    public void show() {
        // Prepare your screen here.

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920 / 1.5f,1080 /1.5f);
        map = new TmxMapLoader().load("Maps/objects.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map, 1f);
        tileset = new Texture("Maps/map1.png");
        tileset.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

    }

    @Override
    public void render(float delta) {
        // Draw your screen here. "delta" is the time since last render in seconds.
        ScreenUtils.clear(Color.BLACK);

        game.viewport.apply();


        camera.position.set(MAP_WIDTH/2f, MAP_HEIGHT/2f , 0);
        camera.update();
        mapRenderer.setView(camera);
        mapRenderer.render();



    }

    @Override
    public void resize(int width, int height) {

        game.viewport.update(width, height, true);
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
        mapRenderer.dispose();
    }
}
