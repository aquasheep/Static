package com.aquasheep.Static;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

public class StaticDesktopLauncher {

	public static void main(String[] args) {
		/** This receives application events */
		ApplicationListener listener = new StaticGame();
		
		/** The title of the game to be displayed in the window */
		String title = "Static";
		
		/** window's size */
		int width = 800, height = 600;
		
		/** Whether or not to use OpenGL ES 2.0 */
		boolean useOpenGLES20 = false;
		
		/** Start the actual game as an LWJGL application */
		new LwjglApplication(listener,title,width,height,useOpenGLES20);
	}
	
}
