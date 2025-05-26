package io.github.aimalshah.game.towerofmordoria;

import com.badlogic.gdx.Game;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {


    @Override
    public void create() {

        this.setScreen(new MainMenuScreen(this));
    }

    @Override
    public void render(){
        super.render();
    }

    @Override
    public void dispose(){

    }




}
