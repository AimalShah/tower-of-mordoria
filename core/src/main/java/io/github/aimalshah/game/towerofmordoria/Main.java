package io.github.aimalshah.game.towerofmordoria;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
   private Music backGroundMusic;
   private boolean isMuted = false;


    @Override
    public void create() {
    backGroundMusic = Gdx.audio.newMusic(Gdx.files.internal("music/Dark_woods.mp3"));
    backGroundMusic.setLooping(true);
    backGroundMusic.setVolume(0.5f);
    backGroundMusic.play();

        this.setScreen(new MainMenuScreen(this));
    }

    public void toggleMute(){
        isMuted = !isMuted;
        backGroundMusic.setVolume(isMuted ? 0f : 0.5f);
    }

    public boolean isMuted() {
        return isMuted;
    }

    public Music getBackgroundMusic() {
        return backGroundMusic;
    }

    public void setBackGroundMusic(String filePath){
        if (backGroundMusic != null) {
            backGroundMusic.stop();
            backGroundMusic.dispose();
        }

        backGroundMusic = Gdx.audio.newMusic(Gdx.files.internal(filePath));
        backGroundMusic.setLooping(true);
        backGroundMusic.setVolume(isMuted ? 0f : 0.5f);
        backGroundMusic.play();
    }
    @Override
    public void render(){
        super.render();
    }

    @Override
    public void dispose(){
        if (backGroundMusic != null) backGroundMusic.dispose();
        if (getScreen() != null) getScreen().dispose();
        super.dispose();
    }




}


