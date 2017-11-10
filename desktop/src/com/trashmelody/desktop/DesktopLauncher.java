package com.trashmelody.desktop;

import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.trashmelody.TrashMelody;

public class DesktopLauncher {
    private static int screen_width = 1920;
    private static int screen_height = 1080;
    private static double screen_scale = 0.2;

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.fullscreen = false;
		config.width  = (int) (screen_width  * screen_scale);
		config.height = (int) (screen_height * screen_scale);
		config.useHDPI = true;

		new LwjglApplication(new TrashMelody(), config);
	}
}
