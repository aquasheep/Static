package com.aquasheep.Static.controller;

import com.aquasheep.Static.StaticGame;
import com.aquasheep.Static.model.World;
import com.aquasheep.Static.screens.GameScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

public class WorldController implements InputProcessor {

	StaticGame game;
	World world;
	
	public WorldController(StaticGame game, World world) {
		this.game = game;
		this.world = world;
		Gdx.input.setInputProcessor(this);
		Gdx.input.setCursorCatched(true);
	}
	
	public void update() {
		//Update the cursor: 
		//if cursor is off the screen uncatch it; 100 is HUD size
		int cursorX = Gdx.input.getX();
		int cursorY = Gdx.input.getY();
		if (cursorX < -10 || cursorX > world.getWidth()+100+10 
				|| cursorY < -10 || cursorY > world.getHeight()+100+10) {
				Gdx.input.setCursorCatched(false);
				//Set position back b/c setCursorCatched changes it
				Gdx.input.setCursorPosition(cursorX,world.getHeight()-cursorY+100);
			//If cursor is inside screen and not catched, recatch it
				//30 is a buffer distance to prevent infinite catch/recatch
		}
		//In precision mode, holding down mouse or keys does nothing
		if (!world.getPrecision()) {
			isMouseButtonDown();
			isKeyDown();
		}
	}
	
	/** Checks to see if a mouse button is down, and if so, applies current tool */
	public boolean isMouseButtonDown() {
		if (Gdx.input.isButtonPressed(0))
			world.applyTool(0);
		else if (Gdx.input.isButtonPressed(1))
			world.applyTool(1);
		return true;
	}
	
	/** Checks if left or right arrow keys are down to continue fast-forward/rewind operations */
	public boolean isKeyDown() {
		if (Gdx.input.isKeyPressed(Keys.LEFT))
			world.rewind();
		else if (Gdx.input.isKeyPressed(Keys.RIGHT))
			world.fastForward();
		else if (Gdx.input.isKeyPressed(Keys.UP))
			world.addToVolume(5);
		else if (Gdx.input.isKeyPressed(Keys.DOWN))
			world.addToVolume(-5);
		return true;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if (world.getStaticSound()) game.toolChange.play();
		if (keycode == Keys.SHIFT_LEFT || keycode == Keys.SHIFT_RIGHT)
			world.switchTool();
		//Arrow keys will increase tool area for people who have no mousewheel
		else if (keycode == Keys.UP)
			world.addToVolume(5);
		else if (keycode == Keys.DOWN)
			world.addToVolume(-5);
		else if (keycode == Keys.LEFT)
			world.rewind();
		else if (keycode == Keys.RIGHT)
			world.fastForward();
		//Pause/resume the entire screen on spacebar hit 
		else if (keycode == Keys.SPACE)
			world.toggleRendering();
		else if (keycode == Keys.M) {
			if (GameScreen.gameMusic.isPlaying())
				GameScreen.gameMusic.pause();
			else
				GameScreen.gameMusic.play();
		} else if (keycode==Keys.P) {
			world.togglePrecisionMode();
		} else if (keycode==Keys.R) {
			GameScreen.gameMusic.dispose();
			game.setScreen(new GameScreen(game));
		} else if (keycode == Keys.S) {
			world.toggleStaticSound();
		} else if (keycode == Keys.ALT_LEFT || keycode == Keys.ALT_RIGHT) {
			world.toggleToolSelection();
		}
		//Handle color channel changes
		else switch(keycode) {
			case Keys.NUM_0:
				world.setColorChannel(0);
				break;
			case Keys.NUM_1:
				world.setColorChannel(1);
				break;
			case Keys.NUM_2:
				world.setColorChannel(2);
				break;
			case Keys.NUM_3:
				world.setColorChannel(3);
				break;
			case Keys.NUM_4:
				world.setColorChannel(4);
				break;
			case Keys.NUM_5:
				world.setColorChannel(5);
				break;
			case Keys.NUM_6:
				world.setColorChannel(6);
				break;
			case Keys.NUM_7:
				world.setColorChannel(7);
				break;
			default: break;
		}
		return true;
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
