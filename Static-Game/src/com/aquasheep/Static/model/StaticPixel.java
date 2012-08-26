package com.aquasheep.Static.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class StaticPixel {

	/** Location of pixel in x,y coordinates */
	private Vector2 position;
	
	/** How often the pixel will change colors randomly (in units of frames per change) */
	private float flickerRate;
	
	/** Controls how bright the colors are on average; - is darker, + is lighter, 0 is normal */
	private float brightness = 0f;
	
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
		if (frameCounter >= flickerRate && !paused) {
			float randColor1 = (float)(Math.random()*1f);
			float randColor2 = (float)(Math.random()*1f);
			switch(colorChannel) {
				case 0:
					//Grayscale
					color.set(randColor1+brightness,randColor1+brightness,randColor1+brightness,1f);
					break;
				case 1:
					//Red
					color.set(randColor1+brightness,brightness,brightness,1f);
					break;
				case 2:
					//Green
					color.set(brightness,randColor1+brightness,brightness,1f);
					break;
				case 3:
					//Blue
					color.set(brightness,brightness,randColor1+brightness,1f);
					break;
				case 4:
					//Yellow
					color.set(randColor1+brightness,randColor2+brightness,brightness,1f);
					break;
				case 5:
					//Purple
					color.set(randColor1+brightness,brightness,randColor2+brightness,1f);
					break;
				case 6:
					//Turquoise
					color.set(brightness,randColor1+brightness,randColor2+brightness,1f);
					break;
				case 7:
					//All colors
					float randColor3 = (float)(Math.random()*1f);
					color.set(randColor1+brightness,randColor2+brightness,randColor3+brightness,1f);
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
	
	/** Change current colorChannel attribute (affects which color ranges are allowed in static)
	 * 
	 * @param button - mouse button pressed
	 * @param channel - new colorChannel
	 */
	public void applyColor(int button, int channel) {
		//If not left mouse button, default to grayscale
		if (button==0)
			colorChannel = channel;
		else
			colorChannel = 0;
	}

	/** If flickerRate will remain within a reasonable range, adjust it 
	 * 
	 * @param add amount to adjust
	 */
	public void adjustFlickerRate(int add) {
		if (flickerRate + add >= 1 && flickerRate + add <= 30)
			flickerRate+=add;
	}

	public float getBrightness() {
		return brightness;
	}

	public void adjustBrightness(float add) {
		if (brightness+add >= -0.9 && brightness+add <= 0.9)
			brightness += add;
	}
	
}
