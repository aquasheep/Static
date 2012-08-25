package com.aquasheep.Static.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class StaticPixel {

	/** Location of pixel in x,y coordinates */
	private Vector2 position;
	/** How often the pixel will change colors randomly (in units of frames per change) */
	private float flickerRate;
	private Color color;
	private boolean paused;
	/** Which color channel is currently enabled:
	 *  0 - grayscale, 1 - red, 2 - green, 3 - blue, 4 - yellow, 5 - purple, 6 - turquoise, 7 - all
	 */
	private int colorChannel = 0;
	
	
	public StaticPixel(Vector2 pos) {
		position = pos;
		//Default color is a random grayscale
		float gray = (float)Math.floor(Math.random()*1f);
		color = new Color(gray,gray,gray,1f);
		flickerRate = 10;
	}
	
	/** Provides new random color of pixel based on flickerRate
	 * 
	 * @param frameCounter
	 */
	//TODO Optimize this function --> this and rendering are two biggest resource hogs
	public void update(float frameCounter) {
		if (frameCounter >= flickerRate) {
			float randColor1 = (float)(Math.random()*1f);
			float randColor2 = (float)(Math.random()*1f);
			switch(colorChannel) {
				case 0:
					//Grayscale
					color.set(randColor1,randColor1,randColor1,1f);
					break;
				case 1:
					//Red
					color.set(randColor1,0,0,1f);
					break;
				case 2:
					//Green
					color.set(0,randColor1,0,1f);
					break;
				case 3:
					//Blue
					color.set(0,0,randColor1,1f);
					break;
				case 4:
					//Yellow
					color.set(randColor1,randColor2,0,1f);
					break;
				case 5:
					//Purple
					color.set(randColor1,0,randColor2,1f);
					break;
				case 6:
					//Turquoise
					color.set(0,randColor1,randColor2,1f);
					break;
				case 7:
					//All colors
					float randColor3 = (float)(Math.random()*1f);
					color.set(randColor1,randColor2,randColor3,1f);
					break;
			}
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
	
	//Getters and setters
	public boolean getPaused() {
		return paused;
	}
	
	public boolean setPaused(boolean b) {
		paused = b;
		return paused;
	}
	
	public Vector2 getPosition() {
		return position;
	}

	public Color getColor() {
		return color;
	}

	public float getFlickerRate() {
		return flickerRate;
	}
	
	public void applyColor(int button, int channel) {
		//If not left mouse button, default to grayscale
		if (button==0)
			colorChannel = channel;
		else
			colorChannel = 0;
	}
	
}
