package com.aquasheep.Static.screens;

import com.aquasheep.Static.StaticGame;
import com.aquasheep.Static.controller.WorldController;
import com.aquasheep.Static.model.World;
import com.aquasheep.Static.view.WorldRenderer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class GameScreen extends AbstractScreen {

	private World world;
	private WorldRenderer renderer;
	private WorldController controller;
	private float frameCounter;
	private int timeElapsed;
	Music music;
	
	public GameScreen(StaticGame game) {
		super(game);
		music = Gdx.audio.newMusic(Gdx.files.internal("sounds/Static1.ogg"));
	}

	@Override
	public void resize(int w, int h) {
		super.resize(w,h);
		frameCounter = 0;
		world = new World(w, h);
		renderer = new WorldRenderer(world);
		controller = new WorldController(game,world);
		music.play();
		music.setVolume(0.4f);
		music.setLooping(true);
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		
		//Reset frameCounter every second
		frameCounter = (1 + frameCounter) % (1/delta);
		if (frameCounter == 0)
			++timeElapsed;
		
		//Continue using tool if mouse button is down
		controller.isMouseButtonDown();
		world.updatePixels(frameCounter);
		renderer.render(world,frameCounter);
	}
	
	@Override
	public void dispose() {
		music.dispose();
	}
	
}
