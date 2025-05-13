package io.github.aimalshah.game.towerofmordoria;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.viewport.FitViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {

    public FitViewport viewport;

    @Override
    public void create() {
        viewport = new FitViewport(1920, 1080);

        //font has 15pt, but we need to scale it to our viewport by ratio of viewport height to screen height



        this.setScreen(new GameScreen(this));
    }

    @Override
    public void render(){
        super.render();
    }

    @Override
    public void dispose(){

    }




}
