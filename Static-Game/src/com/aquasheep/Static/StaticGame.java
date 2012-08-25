package com.aquasheep.Static;

import com.aquasheep.Static.screens.SplashScreen;
import com.badlogic.gdx.Game;

public class StaticGame extends Game {

	@Override
	public void create() {
		setScreen(new SplashScreen(this));
	}
	
}
