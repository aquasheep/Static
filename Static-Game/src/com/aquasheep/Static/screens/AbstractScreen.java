package com.aquasheep.Static.screens;

import com.aquasheep.Static.StaticGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public abstract class AbstractScreen implements Screen {

	protected StaticGame game;
	protected FPSLogger FPSLog;
	protected TextureAtlas atlas;
	
	public AbstractScreen(StaticGame game) {
		this.game = game;
		FPSLog = new FPSLogger();
	}
	
	/** Returns the class name of the calling screen */
	public String getName() {
		return this.getClass().getSimpleName();
	}
	
	public TextureAtlas getAtlas(String name) {
		if (atlas==null) {
			atlas = new TextureAtlas(Gdx.files.internal("image-atlases/"+name+".pack"));
		}
		return atlas;
	}
	
	//Screen implementation
	
	@Override
	public void show() {
		Gdx.app.log(StaticGame.LOG,"Showing screen "+getName());
	}
	
	@Override
	public void render(float delta) {
		FPSLog.log();
//		Gdx.gl.glClearColor(0f,0f,0f,1f);
//		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	}
	

	@Override
	public void resize(int width, int height) {
		Gdx.app.log(StaticGame.LOG,"Resizing screen "+getName()+" to "+width+","+height);
		
	}

	@Override
	public void hide() {
		Gdx.app.log(StaticGame.LOG,"Hiding screen "+getName());
	}

	@Override
	public void pause() {
		Gdx.app.log(StaticGame.LOG,"Pausing screen "+getName());
	}

	@Override
	public void resume() {
		Gdx.app.log(StaticGame.LOG,"Resuming screen "+getName());
	}

	@Override
	public void dispose() {
		Gdx.app.log(StaticGame.LOG,"Disposing screen "+getName());
	}
}
