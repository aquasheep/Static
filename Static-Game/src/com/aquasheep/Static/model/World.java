package com.aquasheep.Static.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class World {

	private int width,height;
	private Array<StaticPixel> pixels = new Array<StaticPixel>();
	private ShapeRenderer renderer;
	/** The area to be affected by the current tool */
	private float volume;
	private Tools tool = Tools.PAUSE;
	private Circle toolCircle;
	
	public enum Tools {
		PAUSE,COLOR
	}
	
	public World(int w, int h) {
		width = w;
		height = h;
		
		//Create StaticPixels for every pixel shown
		for (float x = 0; x < width; ++x) {
			for (float y = 0; y < height; ++y) {
				pixels.add(new StaticPixel(new Vector2(x,y)));
			}
		}
		renderer = new ShapeRenderer();
		volume = 10;
		//TODO render this instead of a new circle in the renderer
		toolCircle = new Circle(Gdx.input.getX(),Gdx.input.getY(),volume);
	}
	
	/** Calls update function on every StaticPixel object in world */
	public void updatePixels(float frameCounter) {
		for (StaticPixel pixel : pixels) {
			pixel.update(frameCounter);
		}
		toolCircle.radius = volume;
		toolCircle.x = Gdx.input.getX();
		toolCircle.y = height-Gdx.input.getY();
	}
	
	public Array<StaticPixel> getPixels() {
		return pixels;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public float getVolume() {
		return volume;
	}
	
	public float addToVolume(float toAdd) {
		//TODO render volume control bars like on TV
		if (volume+toAdd >= 1 && volume+toAdd <= 300)
			volume+=toAdd;
		return volume;
	}

	/** Applies whichever current tool is selected to toolCircle area */
	public void applyTool(int button) {
		for (StaticPixel pixel : pixels) {
			if (toolCircle.contains(pixel.getPosition())) {
				System.out.println("Tool area contains "+pixel.getPosition());
				//If the current tool is pause and the middle mouse button was not pressed
				//Must check middle mouse button press to avoid unintended unpausing on laptop mice
				if (tool==Tools.PAUSE && button!=2) {
					pixel.setPaused(button==0);
				}
				else if (tool==Tools.COLOR && button!=2) {
					pixel.setColored(button==0);
				}
			}
		}
	}
	
	public void switchTool() {
		if (tool == Tools.COLOR)
			tool = Tools.PAUSE;
		else if (tool == Tools.PAUSE)
			tool = Tools.COLOR;
	}
}
