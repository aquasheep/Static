package com.aquasheep.Static.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class StaticPixel {

	/** Location of pixel in x,y coordinates */
	private Vector2 position;
	/** How often the pixel will change colors randomly (in units of frames per change) */
	private float flickerRate;
	private Color color;
	private boolean paused,colored;
	
	
	public StaticPixel(Vector2 pos) {
		position = pos;
		//Default color is a random grayscale
		float gray = (float)Math.floor(Math.random()*256);
		//TODO possible optimization: only use colorRGBA, not the color object
		color = new Color(gray,gray,gray,255);
		flickerRate = 1;
	}
	
	/** Provides new random color of pixel based on flickerRate
	 * 
	 * @param frameCounter
	 */
	public void update(float frameCounter) {
		if (frameCounter >= flickerRate) {
			float gray = (float)Math.floor(Math.random()*256);
			if (colored)
				color = new Color(gray,(float)Math.floor(Math.random()*256),(float)Math.floor(Math.random()*256),255);
			else
				color = new Color(gray,gray,gray,255);
		}
	}
	
	/** Sets the calling StaticPixel to paused or unpaused
	 * 
	 * @return this StaticPixel for chaining
	 */
	public StaticPixel togglePaused() {
		paused = !paused;
		return this;
	}
	
	/** Sets the calling StaticPixel to colored or black and white 
	 * 
	 * @return this StaticPixel for chaining
	 */
	public StaticPixel toggleColored() {
		colored = !colored;
		return this;
	}
	
	//Getters and setters
	public boolean getPaused() {
		return paused;
	}
	
	public boolean getColored() {
		return colored;
	}
	
	public Vector2 getPosition() {
		return position;
	}

	public Color getColor() {
		return color;
	}
}
