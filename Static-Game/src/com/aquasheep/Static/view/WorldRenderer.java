package com.aquasheep.Static.view;

import com.aquasheep.Static.model.StaticPixel;
import com.aquasheep.Static.model.World;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;

public class WorldRenderer {
	
	private World world;
	private Pixmap pixmap;
	
	public WorldRenderer(World world) {
		this.world = world;
		pixmap = new Pixmap(world.getWidth(),world.getHeight(), Pixmap.Format.RGBA8888);
	}
	
	public void render(World world) {
		for (StaticPixel pixel : world.getPixels()) {
			//TODO possible optimization: create rgba8888 in StaticPixel not here during every render
			pixmap.drawPixel((int)pixel.getPosition().x, (int)pixel.getPosition().y, Color.rgba8888(pixel.getColor()));
		}
	}

}
