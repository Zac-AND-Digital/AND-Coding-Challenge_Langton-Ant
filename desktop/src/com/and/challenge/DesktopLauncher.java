package com.and.challenge;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {

	public static void main (String[] arg) {

		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

		config.setTitle("AND Code Challenge - Langton's Ant");
		config.setMaximized(true);
		config.setIdleFPS(Lwjgl3ApplicationConfiguration.getDisplayMode().refreshRate / 4);
		config.setForegroundFPS(0);

		new Lwjgl3Application(new Main(), config);

	}

}
