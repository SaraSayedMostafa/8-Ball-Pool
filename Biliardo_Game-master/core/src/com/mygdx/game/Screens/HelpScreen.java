package com.mygdx.game.Screens;

import aurelienribon.tweenengine.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.tween.ActorAccessor;

public class HelpScreen implements Screen {
    private Table table;
    private Stage stage;
    private Skin skin;
    private TextButton buttonBack;
    private TextureAtlas atles;
    private TweenManager tweenManager;
    private Label label;
    private Image img1,img2;



    @Override
    public void show() {
        tweenManager=new TweenManager();
        stage=new Stage();
        Gdx.input.setInputProcessor(stage);
        atles=new TextureAtlas(Gdx.files.internal("fonts & buttons/glassy-ui.atlas"));
        skin=new Skin(Gdx.files.internal("fonts & buttons/menuSkin.json"),atles);
        table=new Table(skin);
        table.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("Assets/Background2.png"))));
        table.setFillParent(true);
        table.setDebug(true);
        table.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        img1=new Image(new Texture("Assets/game.jpg"));
        img2=new Image(new Texture("Assets/8ball.png"));
        label=new Label("Eight Ball is a call shot game played with a cue ball and fifteen object balls," +
                "numbered 1 through 15.\n\nOne player must pocket balls of the group numbered 1 through 7 (solid colors)," +
                " while the other player\n\nhas 9 through 15 (stripes). THE PLAYER POCKETING HIS GROUP FIRST AND" +
                " THEN LEGALLY POCKETING THE 8-BALL\n\nWINS THE GAME.A player LOSES THE GAME if he commits any of the following infractions:\n\n" +
                "A. Fouls when pocketing the 8-ball. \n\n" +
                "B. Pockets the 8-ball on the same stroke as the last of his group of balls.\n\n" +
                "C. Pockets the 8-ball in a pocket other than the one designated.\n\n" +
                "D. Pockets the 8-ball when it is not the legal object ball.",skin);

        label.setFontScale(0.65f,0.75f);


        buttonBack=new TextButton("Back",skin,"small");
        buttonBack.pad(25);
        buttonBack.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Timeline.createParallel().beginParallel().push(Tween.to(table, ActorAccessor.ALPHA,.25f).target(0))
                        .push(Tween.to(table,ActorAccessor.Y,.25f).target(table.getY()-50)
                                .setCallback(new TweenCallback() {
                                    @Override
                                    public void onEvent(int i, BaseTween<?> baseTween) {
                                        ((Game)(Gdx.app.getApplicationListener())).setScreen(new MainMenue());

                                    }

                                }))
                        .end().start(tweenManager);

            }
        });

        //fad in back button
        Tween.registerAccessor(Actor.class,new ActorAccessor());
        Tween.from(buttonBack, ActorAccessor.ALPHA,.25f).target(0).start(tweenManager);
        //fad in table
        Tween.from(table,ActorAccessor.ALPHA,.25f).target(0).start(tweenManager);
        Tween.from(table,ActorAccessor.Y,.25f).target(Gdx.graphics.getHeight()/8).start(tweenManager);


        table.add(buttonBack).bottom().left();
        table.debug();
        label.setPosition(10 ,360);
        table.add(label).left();
        img1.setPosition(200,100);
        table.add(img1).right();
        img2.setPosition(900,250);
        table.add(img2).right();
        stage.addActor(table);
        stage.addActor(label);
        stage.addActor(img1);
        stage.addActor(img2);
        stage.addActor(buttonBack);


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
        tweenManager.update(delta);
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

    }
}
