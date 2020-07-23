package com.mygdx.game.Screens;

import aurelienribon.tweenengine.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.tween.ActorAccessor;
import com.mygdx.game.tween.SpriteAccessor;

import javax.swing.*;

public class GetName implements Screen {

    private Stage stage;
    private Skin skin;
    private TextButton buttonOK;
    private TextureAtlas atles;
    private TextField textField1,textField2;
    private TweenManager tweenManager;
    private Image img1,img2,img3,img4,img5 ,img6,img7 ,img8 ,img9;
    private Label NamePlayer1,NmaePlayer2;
   // public static Player Player1,Player2;
    private Table table;
   public static String Player1Name;
   public static String  Player2Name;



    @Override
    public void show() {
        stage=new Stage();
        Gdx.input.setInputProcessor(stage);
        tweenManager=new TweenManager();
        Tween.registerAccessor(Sprite.class,new SpriteAccessor());

        atles=new TextureAtlas(Gdx.files.internal("fonts & buttons/glassy-ui.atlas"));
        skin=new Skin(Gdx.files.internal("fonts & buttons/menuSkin.json"),atles);
        table=new Table(skin);

        table.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("Assets/Background2.png"))));
        table.setFillParent(true);
        table.setDebug(true);
        table.setBounds(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());


        NamePlayer1=new Label( "Player 1 Please enter your name",skin);
        NamePlayer1.setFontScale(0.75f,0.75f);
        textField1=new TextField("",skin);


        NmaePlayer2=new Label( "Player 2 Please enter your name",skin);
        NmaePlayer2.setFontScale(0.75f,0.75f);
        textField2=new TextField("",skin);

        img1=new Image(new Texture("Assets/ini1.png"));
        img2=new Image(new Texture("Assets/ini2.png"));
        img3=new Image(new Texture("Assets/ini3.png"));
        img4=new Image(new Texture("Assets/ini4.png"));
        img5=new Image(new Texture("Assets/ini5.png"));
        img6=new Image(new Texture("Assets/smallline.png"));
        img7=new Image(new Texture("Assets/smallline.png"));
        img8=new Image(new Texture("Assets/smallline2.png"));
        img9=new Image(new Texture("Assets/smallline2.png"));




        buttonOK=new TextButton("OK",skin,"small");
        buttonOK.pad(25);
        buttonOK.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (textField1.getText().trim().isEmpty() || textField2.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "You Should enter your name", "ERROR ", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    Timeline.createParallel().beginParallel().push(Tween.to(table, ActorAccessor.ALPHA, .25f).target(0))
                            .push(Tween.to(table, ActorAccessor.Y, .25f).target(table.getY() - 50)
                                    .setCallback(new TweenCallback() {
                                        @Override
                                        public void onEvent(int i, BaseTween<?> baseTween) {



                                            Player1Name=textField1.getText();
                                            Player2Name =textField2.getText();
                                            ((Game) (Gdx.app.getApplicationListener())).setScreen(new GameScreen());


                                        }

                                    }))
                            .end().start(tweenManager);
            }

            }
        });

        //fad in ok button
        Tween.registerAccessor(Actor.class,new ActorAccessor());
        Tween.from(buttonOK, ActorAccessor.ALPHA,.25f).target(0).start(tweenManager);

        //table fad in
        Tween.from(table,ActorAccessor.ALPHA,.25f).target(0).start(tweenManager);
        Tween.from(table,ActorAccessor.Y,.25f).target(Gdx.graphics.getHeight()/8).start(tweenManager);

        NamePlayer1.setPosition(400 ,550);
        table.add(NamePlayer1).center();
        table.row();
        table.getCell(NamePlayer1).space(15);

        textField1.setPosition(500 ,490);
        table.add(textField1).center();
        table.row();
        table.getCell(textField1).space(15);


        NmaePlayer2.setPosition(400 ,400);
        table.add(NmaePlayer2);
        table.row();
        table.getCell(NmaePlayer2).space(15);

        textField2.setPosition(500 ,340);
        table.add(textField2).center();
        table.row();
        table.getCell(textField2).space(15);



        img1.setPosition(900,360);
        table.add(img1).right();

        img2.setPosition(750,580);
        table.add(img2).right();

        img3.setPosition(250,550);
        table.add(img3).right();

        img4.setPosition(180,280);
        table.add(img4).left();

        img5.setPosition(420,25);
        table.add(img5).right();

        img6.setPosition(800,420);
        table.add(img6).right();

        img7.setPosition(830,420);
        table.add(img7).right();

        img8.setPosition(80,350);
        table.add(img8).right();

        img9.setPosition(110,350);
        table.add(img9).right();

        buttonOK.setPosition(900 ,50);
        table.add(buttonOK).center();
        table.row();
        table.getCell(NmaePlayer2).space(15);

        table.debug();
        stage.addActor(table);
        stage.addActor(NamePlayer1);
        stage.addActor(textField1);
        stage.addActor(NmaePlayer2);
        stage.addActor(textField2);
        stage.addActor(img1);
        stage.addActor(img2);
        stage.addActor(img3);
        stage.addActor(img4);
        stage.addActor(img5);
        stage.addActor(img6);
        stage.addActor(img7);
        stage.addActor(img8);
        stage.addActor(img9);
        stage.addActor(buttonOK);




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
