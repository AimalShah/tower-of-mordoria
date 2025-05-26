package io.github.aimalshah.game.towerofmordoria.towers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ArcherTower extends Tower {
    public ArcherTower(Texture towerTexture, float x, float y){
        super(createAnimation(towerTexture), x,y);
    }

    private static Animation<TextureRegion> createAnimation(Texture texture){
        TextureRegion[][] tmp = TextureRegion.split(
            texture,
            texture.getWidth() / 6,
            texture.getHeight()
        );

        TextureRegion[] frames = new TextureRegion[6];

        int index = 0;
        for (int i =0; i<6; i++){
            frames[index++] = tmp[0][i];
        }

        return  new Animation<TextureRegion>(0.2f, frames);
    }
}
