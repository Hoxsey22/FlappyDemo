package com.hoxsey.flappydemo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hoxsey.flappydemo.states.GameStateManager;
import com.hoxsey.flappydemo.states.MenuState;

public class FlappyDemo extends ApplicationAdapter {
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	public static final String TITLE = "FlappyBird";
	private GameStateManager gsm;
	private SpriteBatch batch;
	private Music music;
	public Hud hud;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager(batch);
		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		music.setLooping(true);
		music.setVolume(0.1f);
		music.play();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new MenuState(gsm));
		//hud = new Hud(batch);

	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
		//hud.stage.draw();
	}
	
	@Override
	public void dispose () {
		super.dispose();
		batch.dispose();
		music.dispose();
	}
}
