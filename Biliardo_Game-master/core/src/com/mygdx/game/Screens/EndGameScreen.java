package com.mygdx.game.Screens;

import aurelienribon.tweenengine.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.Screens.BallInfo.ParticleEffects;
import com.mygdx.game.Screens.BallInfo.Player;
import com.mygdx.game.tween.ActorAccessor;
import game.MyGdxGame;

public class EndGameScreen implements Screen {
    private Table table;
    private Stage stage;
    private Skin skin;
    private TextButton buttonExit ,PlayAgain;
    private TextureAtlas atles;
    private TweenManager tweenManager;
    private Label label;
    private Image img1,img2,img3,img4,img5 ,img6,img7 ,img8 ,img9;
    private ParticleEffect effect;
    private ParticleEffects eff;
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

        //if condition who's win
        if(Player.playeroneturn&&GameScreen.player1.getBalls_in_pocket()==7)
        {

            label=new Label( GameScreen.player1.getName()+" WINS ",skin);
            label.setFontScale(3,3);
        }
        else if (Player.playeroneturn&&GameScreen.player1.getBalls_in_pocket()!=7)
        {
            label=new Label( GameScreen.player2.getName()+" WINS ",skin);
            label.setFontScale(3,3);
        }
        else if (!Player.playeroneturn&&GameScreen.player2.getBalls_in_pocket()==7)
        {
            label=new Label( GameScreen.player2.getName()+" WINS ",skin);
            label.setFontScale(3,3);
        }
       else if(!Player.playeroneturn&&GameScreen.player2.getBalls_in_pocket()!=7)
        {

            label=new Label( GameScreen.player1.getName()+" WINS ",skin);
            label.setFontScale(3,3);

        }

        effect=new ParticleEffect();
        effect.load(Gdx.files.internal("effects/explosion.p"),Gdx.files.internal("effects"));
        eff=new ParticleEffects(effect);

        img1=new Image(new Texture("Assets/ini1.png"));
        img2=new Image(new Texture("Assets/ini2.png"));
        img3=new Image(new Texture("Assets/ini3.png"));
        img4=new Image(new Texture("Assets/ini4.png"));
        img5=new Image(new Texture("Assets/ini5.png"));
        img6=new Image(new Texture("Assets/smallline.png"));
        img7=new Image(new Texture("Assets/smallline.png"));
        img8=new Image(new Texture("Assets/smallline2.png"));
        img9=new Image(new Texture("Assets/smallline2.png"));




        buttonExit=new TextButton("Exit",skin,"small");
        buttonExit.pad(25);
        buttonExit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Timeline.createParallel().beginParallel().push(Tween.to(table, ActorAccessor.ALPHA,.25f).target(0))
                        .push(Tween.to(table,ActorAccessor.Y,.25f).target(table.getY()-50)
                                .setCallback(new TweenCallback() {
                                    @Override
                                    public void onEvent(int i, BaseTween<?> baseTween) {
                                        //((Game)(Gdx.app.getApplicationListener())).setScreen(new GetName());
                                             Gdx.app.exit();
                                    }

                                }))
                        .end().start(tweenManager);

            }
        });

        PlayAgain=new TextButton("Play Again",skin,"small");
        PlayAgain.pad(25);
        PlayAgain.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Timeline.createParallel().beginParallel().push(Tween.to(table, ActorAccessor.ALPHA,.25f).target(0))
                        .push(Tween.to(table,ActorAccessor.Y,.25f).target(table.getY()-50)
                                .setCallback(new TweenCallback() {
                                    @Override
                                    public void onEvent(int i, BaseTween<?> baseTween) {
                                        ((Game)(Gdx.app.getApplicationListener())).setScreen(new GetName());

                                    }

                                }))
                        .end().start(tweenManager);

            }
        });

        //fad in  buttons
        Tween.registerAccessor(Actor.class,new ActorAccessor());

        Timeline.createSequence().beginSequence()
                .push(Tween.set(buttonExit,ActorAccessor.ALPHA).target(0))
                .push(Tween.from(PlayAgain,ActorAccessor.ALPHA,.25f).target(0))
                .push(Tween.to(buttonExit,ActorAccessor.ALPHA,.25f).target(1))
                .end().start(tweenManager);

        //fad in table
        Tween.from(table, ActorAccessor.ALPHA,.25f).target(0).start(tweenManager);
        Tween.from(table,ActorAccessor.Y,.25f).target(Gdx.graphics.getHeight()/8).start(tweenManager);


        PlayAgain.setPosition(1070,4);
        table.add(PlayAgain).bottom().right();
        buttonExit.setPosition(0,4);
        table.add(buttonExit).bottom().left();

        eff.draw(MyGdxGame.batch,.5f);
        eff.setPosition(400,560);
        table.add(eff);

        table.debug();
        label.setPosition(400 ,560);
        table.add(label).center();
        img1.setPosition(900,360);
        table.add(img1).right();

        img2.setPosition(750,580);
        table.add(img2).right();

        img3.setPosition(250,550);
        table.add(img3).right();

        img4.setPosition(180,280);
        table.add(img4).left();

        img5.setPosition(420,0);
        table.add(img5).right();

        img6.setPosition(800,420);
        table.add(img6).right();

        img7.setPosition(830,420);
        table.add(img7).right();

        img8.setPosition(80,350);
        table.add(img8).right();

        img9.setPosition(110,350);
        table.add(img9).right();
        stage.addActor(table);

        stage.addActor(eff);
        stage.addActor(label);
        stage.addActor(buttonExit);
        stage.addActor(PlayAgain);
        stage.addActor(img1);
        stage.addActor(img2);
        stage.addActor(img3);
        stage.addActor(img4);
        stage.addActor(img5);
        stage.addActor(img6);
        stage.addActor(img7);
        stage.addActor(img8);
        stage.addActor(img9);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        eff.act(delta);
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
