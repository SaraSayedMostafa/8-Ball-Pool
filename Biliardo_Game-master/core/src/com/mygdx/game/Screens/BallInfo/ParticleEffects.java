package com.mygdx.game.Screens.BallInfo;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ParticleEffects extends Actor {
    ParticleEffect effect;

    public ParticleEffects(ParticleEffect effect) {
        this.effect = effect;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        effect.draw(batch);
       // if(effect.isComplete());
       // effect.reset();
    }


    @Override
    public void act(float delta) {
        super.act(delta);
        effect.setPosition(600,400);
        effect.update(delta);
        effect.start();
    }


}
