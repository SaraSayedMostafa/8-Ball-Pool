package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Screens.BallInfo.*;
import com.mygdx.game.SpaceGame;
import game.MyGdxGame;

public class GameScreen implements Screen {

    public static World world;
    public static Box2DDebugRenderer debugRenderer;
    public static OrthographicCamera camera;
    SpaceGame spaceGame;

    public static FoulSystem foulsystem;
    public static Player player1;
    public static Player player2;
    public BasicTable Basictable;

    //drawing
    public static Array<Body> tmpBodies;
    public static BasicTable table;
    public Sprite Background;
    public static Sprite PocktedArea;
    public Sprite BallAreaForPlayer1,BallAreaForPlayer2;
    public Sprite PlayerName1,PlayerName2;
    public BitmapFont fontName ,fontLabel;
    public BitmapFont VSLabel;
    public Label LabelForPlayer1,LabelForPlayer2 ,GameEnded ;
    public Label.LabelStyle labelStyle;
    public CleanLabel AnimaiteLabel;
    public int counter;

    //public GameScreen(SpaceGame spaceGame) {
    //   this.spaceGame = spaceGame;
 //  }
    @Override
    public void show() {


        world = new World(new Vector2(0,0),true); // x= 0 and y =0 means no gravity
        debugRenderer = new Box2DDebugRenderer();
        camera =new OrthographicCamera(MyGdxGame.VirtualWidth/20,MyGdxGame.VirtualHeight/20);  // zooms in 20 times from the fullscreen

        // initialize the objects and sets the initial position
         Background=new Sprite(new Texture("Assets/Background2.png"));
        Background.setSize( Gdx.graphics.getWidth(),Gdx.graphics.getHeight() );
        Background.setPosition(-Gdx.graphics.getWidth()/40,-Gdx.graphics.getHeight()/40);

        table=new BasicTable(new Sprite(new Texture("Assets/BlueTable.png")));

        player1 = new Player(GetName.Player1Name,new Stick(table.getCueball(),new Sprite(new Texture("Assets/Stick.png"))));
        player2 = new Player(GetName.Player2Name,new Stick(table.getCueball(),new Sprite(new Texture("Assets/Stick.png"))));

        PocktedArea=new Sprite((new Texture("Assets/PocktedArea.png")));
        PocktedArea.setSize(MyGdxGame.VirtualWidth/160,MyGdxGame.VirtualHeight/30);
        PocktedArea.setPosition(MyGdxGame.VirtualWidth/60,-MyGdxGame.VirtualHeight/50);

        BallAreaForPlayer1=new Sprite(new Texture("Assets/BallArea.png"));
        BallAreaForPlayer1.setSize(MyGdxGame.VirtualWidth/90,-MyGdxGame.VirtualHeight/340);
        BallAreaForPlayer1.setPosition(-MyGdxGame.VirtualWidth/60,MyGdxGame.VirtualHeight/51);
        BallAreaForPlayer2=new Sprite(new Texture("Assets/BallArea.png"));
        BallAreaForPlayer2.setSize(MyGdxGame.VirtualWidth/90,-MyGdxGame.VirtualHeight/340);
        BallAreaForPlayer2.setPosition(MyGdxGame.VirtualWidth/200,MyGdxGame.VirtualHeight/51);

        PlayerName1=new Sprite(new Texture("Assets/PlayerName.png"));
        PlayerName1.setSize(MyGdxGame.VirtualWidth/90,-MyGdxGame.VirtualHeight/110);
        PlayerName1.setPosition(-MyGdxGame.VirtualWidth/57,MyGdxGame.VirtualHeight/36);
        PlayerName2=new Sprite(new Texture("Assets/PlayerName.png"));
        PlayerName2.setSize(MyGdxGame.VirtualWidth/90,-MyGdxGame.VirtualHeight/110);
        PlayerName2.setPosition(MyGdxGame.VirtualWidth/220,MyGdxGame.VirtualHeight/36);

        fontName=new BitmapFont(Gdx.files.internal("fonts & buttons/Bigfont.fnt"),false);
        fontName.setColor(Color.WHITE);
        fontName.getData().setScale(0.05f,0.04f);

        VSLabel=new BitmapFont(Gdx.files.internal("fonts & buttons/Bigfont.fnt"),false);
        VSLabel.setColor(Color.BLACK);
        VSLabel.getData().setScale(0.05f,0.04f);

        labelStyle= new Label.LabelStyle();
        fontLabel=new BitmapFont(Gdx.files.internal("fonts & buttons/Bigfont.fnt"),false);
        labelStyle.font=fontLabel;

        foulsystem=new FoulSystem();
        tmpBodies= new Array<Body>();

      // if condition who's turn
        LabelForPlayer1=new Label(player1.getName()+ "IS Your Turn",labelStyle);
        LabelForPlayer1.setBounds(-10,.2f,MyGdxGame.VirtualWidth,2);
        LabelForPlayer1.setFontScale(0.05f,0.05f);

        LabelForPlayer2=new Label( player2.getName()+ " IS Your Turn",labelStyle);
        LabelForPlayer2.setBounds(-10,.2f,MyGdxGame.VirtualWidth,2);
        LabelForPlayer2.setFontScale(0.05f,0.05f);

    }


    public static boolean checkcueball=false;
    public static boolean turnfinished=false;
    public static boolean checkdropedaball=false;
    public static float y=-10;
    @Override

    public void render(float delta) {

        if(checkcueball&&!turnfinished) // when the cueball enters any hole
        {

            if(Player.playeroneturn) {
                player1.Movethecueballfreely(); // moves the cueball in all the table freely
                LabelForPlayer1.draw(MyGdxGame.batch, 0.5f);
                AnimaiteLabel = new CleanLabel(2, LabelForPlayer1);
                AnimaiteLabel.startCountdownFromNow();
            }
            else {
                player2.Movethecueballfreely();
                LabelForPlayer2.draw(MyGdxGame.batch,0.5f);
                AnimaiteLabel=new CleanLabel(2,LabelForPlayer2);
                AnimaiteLabel.startCountdownFromNow();
            }

        }

        else  if(foulsystem.Checkallballsmovement()) //if cueball stopped moving
        {
            foulsystem.Checkfouls(); // check if there are any fouls from the last round and changes the turn if there are

            if(Player.playeroneturn) {
                player1.Controlthecuestick();// power and the movement of the stick
                MyGdxGame.batch.begin();
                LabelForPlayer1.draw(MyGdxGame.batch, 0.5f);
                AnimaiteLabel = new CleanLabel(2, LabelForPlayer1);
                AnimaiteLabel.startCountdownFromNow();
                MyGdxGame.batch.end();
            }
            else {
                player2.Controlthecuestick();
                MyGdxGame.batch.begin();
                LabelForPlayer2.draw(MyGdxGame.batch,0.5f);
                AnimaiteLabel=new CleanLabel(2,LabelForPlayer2);
                AnimaiteLabel.startCountdownFromNow();
                MyGdxGame.batch.end();
            }
        }


        else    //if ball is moving
        {

            player1.getStick().getStick().setTransform(10, 30, 0); // puts the stick away
            player2.getStick().getStick().setTransform(10, 30, 0); // puts the stick away

            turnfinished = true; // when u hit the ball the turn is finished
            foulsystem.Detectifanyfoulsishappening(); //detects the fouls

        }


        // camera and draw stuff
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        world.step(1f/60f, 5, 8); // to make the world feel the time
        MyGdxGame.batch.setProjectionMatrix(camera.combined);
        MyGdxGame. batch.begin();
        Background.draw(MyGdxGame.batch);
        table.getTablesprite().draw(MyGdxGame.batch);
        PocktedArea.draw(MyGdxGame.batch);
        BallAreaForPlayer1.draw(MyGdxGame.batch);
        BallAreaForPlayer2.draw(MyGdxGame.batch);
        PlayerName1.draw(MyGdxGame.batch);
        PlayerName2.draw(MyGdxGame.batch);
        fontName.draw(MyGdxGame.batch,player1.getName(), -MyGdxGame.VirtualWidth/60,MyGdxGame.VirtualHeight/46.99f);
        fontName.draw(MyGdxGame.batch,player2.getName(), MyGdxGame.VirtualWidth/200,MyGdxGame.VirtualHeight/46.99f);
        VSLabel.draw(MyGdxGame.batch," VS ",-MyGdxGame.VirtualWidth/350,MyGdxGame.VirtualHeight/46.99f);
        //



        if(Player.playeroneturn) //change player turn sprites
        {

            LabelForPlayer1.draw(MyGdxGame.batch,0.5f);
            AnimaiteLabel=new CleanLabel(2,LabelForPlayer1);
            AnimaiteLabel.startCountdownFromNow();

        }

        else {

            LabelForPlayer2.draw(MyGdxGame.batch,0.5f);
            AnimaiteLabel=new CleanLabel(2,LabelForPlayer2);
            AnimaiteLabel.startCountdownFromNow();
        }


        foulsystem.Checkifanyballtouchestheholes(); //detects if and ball touched the hole

        GameScreen.PocktedArea.draw(MyGdxGame.batch,delta);

        for(Body body:tmpBodies) //draws the sprites of every body
        {
            if(body.getUserData()!=null && body.getUserData() instanceof Sprite)
            {
                Sprite sprite=(Sprite)body.getUserData();
                sprite.setPosition(body.getPosition().x-sprite.getWidth()/2,body.getPosition().y-sprite.getHeight()/2);
                sprite.setRotation(body.getAngle()* MathUtils.radiansToDegrees);
                sprite.draw(MyGdxGame.batch);
            }
        }


        MyGdxGame.batch.end();
        //debugRenderer.render(world,camera.combined);  // to render the bodies

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

    }

    @Override
    public void dispose() {
        MyGdxGame.batch.dispose();
    }

}
