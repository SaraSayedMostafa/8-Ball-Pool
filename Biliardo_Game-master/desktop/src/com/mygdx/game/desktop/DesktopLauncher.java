package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import game.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
  // joson file + daata buttons , puctuer,text
		config.title=" 8 Ball Pool ";
		config.width=1280;
		config.height=720;
		config.useGL30=true;

		//config.width=SpaceGame.VirtualWidth;
	//	config.height=SpaceGame.VirtualHeight;
		new LwjglApplication(new MyGdxGame(), config);
	}
}
