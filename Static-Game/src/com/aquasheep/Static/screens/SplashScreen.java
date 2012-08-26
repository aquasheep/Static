package com.aquasheep.Static.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.aquasheep.Static.StaticGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;

public class SplashScreen extends AbstractScreen {
	
	Stage stage;
	
	public SplashScreen(StaticGame game) {
		super(game);
		stage = new Stage();
	}
	
	@Override
	public void show() {
		super.show();
		Gdx.input.setInputProcessor(stage);
	}
	
	@Override
	public void resize(int w, int h) {
		stage.clear();
		stage.setViewport(w,h,true);
		
		//Define texture region as a drawable
		AtlasRegion splashRegion = getAtlas("splash-screen").findRegion("StaticIcon");
		Drawable splashDrawable = new TextureRegionDrawable(splashRegion);
		
		//Create actor for splashImage and make it take up whole screen
		Image splashImage = new Image(splashDrawable, Scaling.stretch, Align.bottom | Align.left);
		splashImage.setFillParent(true);
		
		//Change image alpha to 0 to get fadeIn to work
		splashImage.getColor().a = 0f;
		
		splashImage.addAction(delay(0.5f,sequence(fadeIn(0.75f),delay(4.0f,fadeOut(0.75f)),
			new Action() {
				public boolean act(float delta) {
					game.setScreen(new GameScreen(game));
					return true;
				}
		})));
		
		stage.addActor(splashImage);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
	}
	
	@Override
	public void dispose() {
		game.setScreen(new GameScreen(game));
	}

}
