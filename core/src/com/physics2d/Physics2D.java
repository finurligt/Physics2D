package com.physics2d;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.physics2d.states.GameStateManager;
import com.physics2d.states.MenuState;

public class Physics2D extends ApplicationAdapter {
	public static final int WIDTH = 1600;
	public static final int HEIGHT = 960;

	public static final String title = "Physics2D";
	private GameStateManager gsm;
	private SpriteBatch sb;

	Texture img;
	
	@Override
	public void create () {
		gsm = new GameStateManager();
		sb = new SpriteBatch();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(sb);
	}
	
	@Override
	public void dispose () {
		sb.dispose();
		img.dispose();
	}
}
