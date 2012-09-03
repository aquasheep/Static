package com.aquasheep.Static;

import com.badlogic.gdx.backends.lwjgl.LwjglApplet;

public class StaticApplet extends LwjglApplet
{
	private static final long serialVersionUID = 1L;
	
	public StaticApplet()
	{
		super(new StaticGame(), false);
	}
	
}