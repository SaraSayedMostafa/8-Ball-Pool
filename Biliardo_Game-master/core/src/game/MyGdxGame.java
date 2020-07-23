package game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Screens.Splash;

public class MyGdxGame extends Game {

   // public static Batch batch;
    public static SpriteBatch batch;
    public static int VirtualWidth=1095;
    public static int VirtualHeight=600;
    private Music music;

        @Override
        public void create () {
            batch=new SpriteBatch();
            setScreen(new Splash());
            music= Gdx.audio.newMusic(Gdx.files.internal("Sounds/Funny-background-music-for-games.mp3"));
            music.setLooping(true);
            music.setVolume(0.1f);
            music.play();
        }

        @Override
        public void render () {
            super.render();

        }

    @Override
    public void dispose() {
        super.dispose();
        music.dispose();
    }
}

