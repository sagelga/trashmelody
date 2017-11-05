package com.trashmelody.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.trashmelody.TrashMelody;
import org.lwjgl.opengl.Display;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 2880;
		config.height = 1800;
		config.useHDPI = true;

		// Enable native fullscreen on macOS
		System.setProperty("org.lwjgl.opengl.Display.enableOSXFullscreenModeAPI", "true");

		new LwjglApplication(new TrashMelody(), config);
	}
}
