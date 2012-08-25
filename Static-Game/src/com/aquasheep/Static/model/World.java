package com.aquasheep.Static.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class World {

	private int width,height;
	private Array<StaticPixel> pixels;
	
	public World(int w, int h) {
		width = w;
		height = h;
		
		//Create StaticPixels for every pixel shown
		for (float x = 0; x < width; ++x) {
			for (float y = 0; y < height; ++y) {
				pixels.add(new StaticPixel(new Vector2(x,y)));
			}
		}
	}
	
}
