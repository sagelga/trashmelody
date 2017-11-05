package com.trashmelody.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.trashmelody.TrashMelody;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.fullscreen = false;

		config.width = 960; // 1920/2
		config.height = 540; // 1080/2

		config.useHDPI = true;
		new LwjglApplication(new TrashMelody(), config);
	}
}
