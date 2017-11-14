package com.trashmelody.desktop;

//import com.apple.eawt.Application;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.trashmelody.TrashMelody;

public class DesktopLauncher {
    private static int screen_width = 1920;
    private static int screen_height = 1080;
    private static double screen_scale = 0.5;

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.fullscreen = false;
		config.width  = (int) (screen_width  * screen_scale);
		config.height = (int) (screen_height * screen_scale);
		config.useHDPI = true;
		config.samples = 3;
		config.addIcon("GameLogo/Icon2/MacOS.png", Files.FileType.Internal);
		config.addIcon("GameLogo/Icon2/Linux.png", Files.FileType.Internal);
		config.addIcon("GameLogo/Icon2/Windows.png", Files.FileType.Internal);
		config.addIcon("GameLogo/game-logo.jpeg", Files.FileType.Internal);
		System.setProperty("org.lwjgl.opengl.Display.enableOSXFullscreenModeAPI", "true");

		new LwjglApplication(new TrashMelody(), config);
	}
}
