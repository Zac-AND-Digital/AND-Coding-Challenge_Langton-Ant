package com.and.challenge;

import com.and.challenge.constant.CameraResolution;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {

	public static void main (String[] arg) {

		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

		config.setTitle("AND Code Challenge - Langton's Ant");
		config.setMaximized(true);
		config.useVsync(true);
		config.setIdleFPS(Lwjgl3ApplicationConfiguration.getDisplayMode().refreshRate / 4);
		config.setForegroundFPS(Lwjgl3ApplicationConfiguration.getDisplayMode().refreshRate);

		new Lwjgl3Application(new Main(), config);

	}

}
