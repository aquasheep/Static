package com.aquasheep.Static.screens;

import com.aquasheep.Static.StaticGame;
import com.aquasheep.Static.model.World;
import com.aquasheep.Static.view.WorldRenderer;

public class GameScreen extends AbstractScreen {

	private World world;
	private WorldRenderer renderer;
	private float frameCounter;
	
	public GameScreen(StaticGame game) {
		super(game);
	}

	@Override
	public void resize(int w, int h) {
		super.resize(w,h);
		frameCounter = 0;
		world = new World(w, h);
		renderer = new WorldRenderer(world);
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
		
		//Reset frameCounter every second
		frameCounter = (1 + frameCounter) % (1/delta);
		
		world.updatePixels(frameCounter);
		renderer.render(world);
	}
	
}
