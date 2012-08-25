package com.aquasheep.Static;

import com.aquasheep.Static.screens.GameScreen;
import com.aquasheep.Static.screens.SplashScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class StaticGame extends Game {

	public static final String LOG = "Static Game Log";
	
	@Override
	public void create() {
		setScreen(new GameScreen(this));
	}
	
}
