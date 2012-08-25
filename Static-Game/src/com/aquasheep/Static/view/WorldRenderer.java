package com.aquasheep.Static.view;

import com.aquasheep.Static.model.StaticPixel;
import com.aquasheep.Static.model.World;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class WorldRenderer {
	
	private World world;
	private Pixmap pixmap;
	private SpriteBatch spriteBatch;
	
	public WorldRenderer(World world) {
		this.world = world;
		pixmap = new Pixmap(world.getWidth(),world.getHeight(), Pixmap.Format.RGBA8888);
		spriteBatch = new SpriteBatch();
	}
	
	public void render(World world) {
		for (StaticPixel pixel : world.getPixels()) {
			pixmap.drawPixel((int)pixel.getPosition().x, (int)pixel.getPosition().y, pixel.getColor());
		}
		
		spriteBatch.begin();
		spriteBatch.draw(new Texture(pixmap),0,0);
		spriteBatch.end();
	}

}
