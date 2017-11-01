package com.trashmelody.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.trashmelody.TrashMelody;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.fullscreen = true;
		config.width = 854;
		config.height = 480;
		config.useHDPI = true;
		new LwjglApplication(new TrashMelody(), config);
	}
}
