package com.aquasheep.Static.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class StaticPixel {

	/** Location of pixel in x,y coordinates */
	private Vector2 position;
	/** How often the pixel will change colors randomly (in units of changes per frame) */
	private float flickerRate;
	private Color color;
	private boolean paused,colored;
	
	
	public StaticPixel(Vector2 pos) {
		position = pos;
		//Default color is a random grayscale
		float gray = (float)Math.floor(Math.random()*256);
		color = new Color(gray,gray,gray,1f);
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
				color = new Color(gray,(float)Math.floor(Math.random()*256),(float)Math.floor(Math.random()*256),1f);
			else
				color = new Color(gray,gray,gray,1f);
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
	
	//Getters and settors
	public boolean getPaused() {
		return paused;
	}
	
	public boolean getColored() {
		return colored;
	}
	
	public Vector2 getPosition() {
		return position;
	}
}
