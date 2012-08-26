package com.aquasheep.Static.view;

import com.aquasheep.Static.model.StaticPixel;
import com.aquasheep.Static.model.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class WorldRenderer {
	
	private World world;
	private ShapeRenderer renderer;
	private SpriteBatch spriteBatch;
	private Texture tv;
	private BitmapFont font;
	
	public WorldRenderer(World world) {
		this.world = world;
		renderer = new ShapeRenderer();
		float width = world.getWidth();
		float height = world.getHeight();
		tv = new Texture(Gdx.files.internal("images/tvTexture.png"));
		spriteBatch = new SpriteBatch();
		font = new BitmapFont();
	}
	
	public void render(World world, float frameCounter) {
		renderer.begin(ShapeType.Point);
		for (StaticPixel pixel : world.getPixels()) {
			//Only render if it is time for that pixel to render, should save cpu
			if (frameCounter >= pixel.getFlickerRate() && !pixel.getPaused()) {
				renderer.setColor(pixel.getColor());
				renderer.point(pixel.getPosition().x, pixel.getPosition().y,0);
			}
		}
		//Render the tv screen, and the controls
		renderer.end();
		spriteBatch.begin();
		//Draw the tv to eliminate white space that was necessary to texture the image
		//Draw tv only if in original 800x600 aspect ratio
		if (world.getWidth()==700 && world.getHeight() == 500)
			spriteBatch.draw(tv,0,-1024+600);
		renderText();
		spriteBatch.end();
		
		//Render mouse circle for tools
		//TODO currently, circle renders faster than background and therefore lags, need to undo circle some way
//		renderer.begin(ShapeType.Circle);
//		renderer.setColor(new Color(1f,0f,0f,0.3f));
//		renderer.circle(Gdx.input.getX(), world.getHeight()-Gdx.input.getY(), world.getVolume());
//		renderer.end();
	}
	
	/** Renders UI text */
	private void renderText() {
		//Controls in bottom
		font.setScale(1.5f);
		font.draw(spriteBatch,"Controls:",10,120);
		font.setScale(1.0f);
		font.drawWrapped(spriteBatch, 
				"Left/Right Mouse - apply tool pos/neg ||| Shift - switch tool ||| Up/Down Arrow or Mousewheel - increase tool size\n" +
				"Left/Right Arrows - Rewind/Fast-forward selected static ||| M - toggle music ||| Space - pause screen\n" +
				"0-7 - Set color range for Color tool:\n " +
				"0-greyscale ||| 1-red ||| 2-green ||| 3-blue ||| 4-yellow ||| 5-purple ||| 6-turquoise ||| 7-all colors", 10, 90, 780);
		
		//Tools in top
		font.setScale(1.5f);
		font.draw(spriteBatch,"Available Tools",300,world.getHeight()+90);
		font.drawWrapped(spriteBatch,
				"Pause        Color        Brightness\n" +
				"           Current Tool: "+world.getCurrentToolName(),240,world.getHeight()+60,400);
	}

}
