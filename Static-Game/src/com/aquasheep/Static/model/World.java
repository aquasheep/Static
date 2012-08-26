package com.aquasheep.Static.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class World {

	private int width,height;
	private Array<StaticPixel> pixels = new Array<StaticPixel>();
	/** The area to be affected by the current tool */
	private float volume;
	private Tools tool = Tools.PAUSE;
	private Circle toolCircle;
	/** Which color channel the color tool is currently using */
	private int currentColorChannel = 7;
	private boolean rendering = true;
	
	public enum Tools {
		PAUSE,COLOR,BRIGHTNESS
	}
	
	//TODO add hue/saturation adjustment for brighter and darker colors
	//TODO more optimizations to allow to run on Android
	//TODO fix android not displaying the game
	//TODO re-insert splash screen
	public World(int w, int h) {
		width = w-100; //Save 100 pixels for HUD
		height = h-100;
		
		//Create StaticPixels for every pixel except 100 for HUD
		for (float x = 100; x < width; ++x) {
			for (float y = 100; y < height; ++y) {
				pixels.add(new StaticPixel(new Vector2(x,y)));
			}
		}
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
		//Compensate (height-) for Gdx starting in top-left when openGL is bottom-left
		//Also compensate (+100) for tv frame size
		toolCircle.y = height-Gdx.input.getY()+100;
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
				//If the current tool is pause and the middle mouse button was not pressed
				//Must check middle mouse button press to avoid unintended unpausing on laptop mice
				if (tool==Tools.PAUSE && button!=2) {
					pixel.setPaused(button==0);
				}
				else if (tool==Tools.COLOR && button!=2) {
					pixel.applyColor(button,currentColorChannel);
				}
				else if (tool==Tools.BRIGHTNESS && button!=2)
					//Left mouse button brightens
					if (button==0)
						pixel.adjustBrightness(0.02f);
					//Right mouse button darkens
					else if (button==1)
						pixel.adjustBrightness(-0.02f);
			}
		}
	}
	
	public void switchTool() {
		if (tool == Tools.PAUSE)
			tool = Tools.COLOR;
		else if (tool == Tools.COLOR)
			tool = Tools.BRIGHTNESS;
		else if (tool == Tools.BRIGHTNESS)
			tool = Tools.PAUSE;
	}
	
	public void setColorChannel(int newChannel) {
		currentColorChannel = newChannel;
	}
	
	public int getColorChannel() {
		return currentColorChannel;
	}

	/** Increase rate of change for pixels in area of tool */
	public void fastForward() {
		for (StaticPixel pixel : pixels) {
			if (toolCircle.contains(pixel.getPosition())) {
				pixel.adjustFlickerRate(-1);
			}
		}
	}
	
	/** Decrease rate of change for pixels in area of tool*/
	public void rewind() {
		for (StaticPixel pixel : pixels) {
			if (toolCircle.contains(pixel.getPosition())) {
				pixel.adjustFlickerRate(1);
			}
		}
	}

	public boolean getRendering() {
		return rendering;
	}
	
	public void toggleRendering() {
		rendering = !rendering;
	}

	public String getCurrentToolName() {
		if (tool==Tools.PAUSE)
			return "Pause";
		else if (tool == Tools.COLOR)
			return "Color";
		else if (tool == Tools.BRIGHTNESS)
			return "Brightness";
		return "Invalid tool";
	}
}
