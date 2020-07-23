package com.mygdx.game.Screens;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.tween.SpriteAccessor;

public class Splash implements Screen {
    private Sprite splash;
    private SpriteBatch batch;
    private TweenManager TweenManger;
    @Override
    public void show() {
        batch=new SpriteBatch();
        TweenManger=new TweenManager() ;
        Tween.registerAccessor(Sprite.class,new SpriteAccessor());
        Texture splashTeture =new Texture("fonts & buttons/8-Ball-Pool-Cover.jpg");
        splash=new Sprite(splashTeture);
        splash.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        Tween.set(splash,SpriteAccessor.ALPHA).target(0).start(TweenManger);


        Tween.to(splash,SpriteAccessor.ALPHA ,3).target(1).repeatYoyo(1,2).setCallback(new TweenCallback() {
            @Override
            public void onEvent(int i, BaseTween<?> baseTween) {
                ((Game)(Gdx.app.getApplicationListener())).setScreen(new MainMenue());
            }
        }).start(TweenManger);
        TweenManger.update(Float.MIN_VALUE);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        splash.draw(batch);
        batch.end();
        TweenManger.update(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        batch.dispose();
        splash.getTexture().dispose();
    }
}
