package com.aquasheep.Static.view;

import com.aquasheep.Static.model.StaticPixel;
import com.aquasheep.Static.model.World;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class WorldRenderer {
	
	private World world;
	private Pixmap pixmap;
	private ShapeRenderer renderer;
	
	public WorldRenderer(World world) {
		this.world = world;
		renderer = new ShapeRenderer();
	}
	
	public void render(World world, float frameCounter) {
		renderer.begin(ShapeType.Point);
		for (StaticPixel pixel : world.getPixels()) {
			//Only render if it is time for that pixel to render, should save cpu
			if (frameCounter >= pixel.getFlickerRate()) {
				renderer.setColor(pixel.getColor());
				renderer.point(pixel.getPosition().x, pixel.getPosition().y,0);
			}
		}
		renderer.end();
	}

}
