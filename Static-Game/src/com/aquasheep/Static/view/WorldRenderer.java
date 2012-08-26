package com.aquasheep.Static.view;

import com.aquasheep.Static.model.StaticPixel;
import com.aquasheep.Static.model.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class WorldRenderer {
	
	private World world;
	private ShapeRenderer renderer;
	private SpriteBatch spriteBatch;
	private Texture tv;
	
	public WorldRenderer(World world) {
		this.world = world;
		renderer = new ShapeRenderer();
		float width = world.getWidth();
		float height = world.getHeight();
		tv = new Texture(Gdx.files.internal("images/tvTexture.png"));
		spriteBatch = new SpriteBatch();
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
		spriteBatch.draw(tv,0,-1024+600);
		spriteBatch.end();
		//Render mouse circle for tools
		//TODO currently, circle renders faster than background and therefore lags, need to undo circle some way
//		renderer.begin(ShapeType.Circle);
//		renderer.setColor(new Color(1f,0f,0f,0.3f));
//		renderer.circle(Gdx.input.getX(), world.getHeight()-Gdx.input.getY(), world.getVolume());
//		renderer.end();
	}

}
