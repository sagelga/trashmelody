package com.trashmelody.desktop;

//import com.apple.eawt.Application;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.trashmelody.TrashMelody;

public class DesktopLauncher {
    private static float SCREEN_SCALE = 0.5F;

	public static void main (String[] arg) {
	    TrashMelody game = new TrashMelody();
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.fullscreen = false;
		config.width  = (int) (game.WIDTH  * SCREEN_SCALE);
		config.height = (int) (game.HEIGHT * SCREEN_SCALE);
		config.useHDPI = true;
		config.samples = 3;
		config.addIcon("GameLogo/Icon2/MacOS.png", Files.FileType.Internal);
		config.addIcon("GameLogo/Icon2/Linux.png", Files.FileType.Internal);
		config.addIcon("GameLogo/Icon2/Windows.png", Files.FileType.Internal);
		config.addIcon("GameLogo/game-logo.jpeg", Files.FileType.Internal);
		System.setProperty("org.lwjgl.opengl.Display.enableOSXFullscreenModeAPI", "true");

		new LwjglApplication(game, config);
	}
}
