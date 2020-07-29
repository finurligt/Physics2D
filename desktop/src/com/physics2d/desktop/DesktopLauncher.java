package com.physics2d.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.physics2d.Physics2D;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Physics2D.WIDTH;
		config.height = Physics2D.HEIGHT;
		config.title = Physics2D.title;
		new LwjglApplication(new Physics2D(), config);
	}
}
