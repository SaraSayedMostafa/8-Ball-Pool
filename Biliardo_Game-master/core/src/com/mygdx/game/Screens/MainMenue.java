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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.tween.ActorAccessor;

public class MainMenue implements Screen {
    private Stage stage;
    private Table table;
    private TextButton buttonPlay,buttonExit,buttonhelp;
    private Skin skin;
   // private Label heading;
    private TextureAtlas atles;
    private TweenManager tweenManager;

    @Override
    public void show() {
        tweenManager=new TweenManager();

        stage=new Stage();

        Gdx.input.setInputProcessor(stage);
        atles=new TextureAtlas(Gdx.files.internal("fonts & buttons/glassy-ui.atlas"));
        skin=new Skin(Gdx.files.internal("fonts & buttons/menuSkin.json"),atles);
        table=new Table(skin);

        table.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("fonts & buttons/8-Ball-Pool-Cover.jpg"))));
        table.setFillParent(true);
        table.setDebug(true);


        table.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        //create buttons

        buttonExit= new TextButton("Exit",skin,"default");
        buttonExit.pad(20);
        buttonExit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Timeline.createParallel().beginParallel().push(Tween.to(table,ActorAccessor.ALPHA,.75f).target(0))
                        .push(Tween.to(table,ActorAccessor.Y,.75f).target(table.getY()-50)
                                .setCallback(new TweenCallback() {
                                    @Override
                                    public void onEvent(int i, BaseTween<?> baseTween) {
                                          Gdx.app.exit();
                                    }

                                }))
                        .end().start(tweenManager);

            }
        });
        buttonPlay=new TextButton("Play",skin,"default");
        buttonPlay.pad(20);
        buttonPlay.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game)(Gdx.app.getApplicationListener())).setScreen(new GetName());
              //  ((Game) (Gdx.app.getApplicationListener())).setScreen(new GameScreen());
            }
        });
        buttonhelp=new TextButton("Help",skin,"default");
        buttonhelp.pad(20);
        buttonhelp.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                ((Game)(Gdx.app.getApplicationListener())).setScreen(new HelpScreen());



            }
        });

        //create animations

        Tween.registerAccessor(Actor.class,new ActorAccessor());
        // button fad in
        Timeline.createSequence().beginSequence()
                .push(Tween.set(buttonhelp,ActorAccessor.ALPHA).target(0))
                .push(Tween.set(buttonExit,ActorAccessor.ALPHA).target(0))
                .push(Tween.from(buttonPlay,ActorAccessor.ALPHA,.25f).target(0))
                .push(Tween.to(buttonhelp,ActorAccessor.ALPHA,.25f).target(1))
                .push(Tween.to(buttonExit, ActorAccessor.ALPHA,.25f).target(1))
                .end().start(tweenManager);
        //table fad in
        Tween.from(table,ActorAccessor.ALPHA,.75f).target(0).start(tweenManager);
        Tween.from(table,ActorAccessor.Y,.75f).target(Gdx.graphics.getHeight()/8).start(tweenManager);

        buttonPlay.setPosition(100 ,450);
        table.add(buttonPlay).left();
        table.row();
        table.getCell(buttonPlay).space(15);

        buttonhelp.setPosition(100,300);
        table.add(buttonhelp).left();
        table.row();
        table.getCell(buttonhelp).space(15);


        buttonExit.setPosition(100,150);
        table.add(buttonExit).left();

        table.debug();
        stage.addActor(table);
        stage.addActor(buttonPlay);
        stage.addActor(buttonhelp);
        stage.addActor(buttonExit);



    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //  Table.drawDebug(stage);



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
        stage.dispose();
        atles.dispose();
        skin.dispose();
    }
}
