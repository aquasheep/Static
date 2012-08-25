package com.aquasheep.Static.screens;

import com.aquasheep.Static.StaticGame;

public class SplashScreen extends AbstractScreen {
	
	public SplashScreen(StaticGame game) {
		super(game);
	}
	
	@Override
	public void dispose() {
		game.setScreen(new GameScreen(game));
	}

}
