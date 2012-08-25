package com.aquasheep.Static.model;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class World {

	private int width,height;
	private Array<StaticPixel> pixels = new Array<StaticPixel>();
	private ShapeRenderer renderer;
	
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
	}
	
	/** Calls update function on every StaticPixel object in world */
	public void updatePixels(float frameCounter) {
		for (StaticPixel pixel : pixels) {
			pixel.update(frameCounter);
		}
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
	
}
