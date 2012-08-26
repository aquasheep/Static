package com.aquasheep.Static;

import com.aquasheep.Static.screens.SplashScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class StaticGame extends Game {

	public static final String LOG = "Static Game Log";
	public Sound toolChange;
	
	@Override
	public void create() {
		toolChange = Gdx.audio.newSound(Gdx.files.internal("sounds/ToolChange.ogg"));
		setScreen(new SplashScreen(this));
	}
	
}
