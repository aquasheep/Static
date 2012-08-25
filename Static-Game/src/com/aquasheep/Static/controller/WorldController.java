package com.aquasheep.Static.controller;

import com.aquasheep.Static.StaticGame;
import com.aquasheep.Static.model.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

public class WorldController implements InputProcessor {

	StaticGame game;
	World world;
	
	public WorldController(StaticGame game, World world) {
		this.game = game;
		this.world = world;
		Gdx.input.setInputProcessor(this);
	}
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		//Buttons - 0-left,1-right,2-middle
		world.applyTool(button);
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/** Scrolling mousewheel changes volume */
	public boolean scrolled(int amount) {
		world.addToVolume(amount*5);
		return true;
	}

}
