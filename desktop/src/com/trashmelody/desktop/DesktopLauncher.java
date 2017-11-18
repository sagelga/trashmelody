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
		config.addIcon("GameLogo/MacOS.png", Files.FileType.Internal);
		config.addIcon("GameLogo/Linux.png", Files.FileType.Internal);
		config.addIcon("GameLogo/Windows.png", Files.FileType.Internal);

		System.setProperty("org.lwjgl.opengl.Display.enableOSXFullscreenModeAPI", "true");

		new LwjglApplication(game, config);
	}
}

/*      Please no bug, no crash and Java is our savior
	                                  _
                               _ooOoo_
                              o8888888o
                              88" . "88
                              (| -_- |)
                              O\  =  /O
                           ____/`---'\____
                         .'  \\|     |//  `.
                        /  \\|||  :  |||//  \
                       /  _||||| -:- |||||_  \
                       |   | \\\  -  /'| |   |
                       | \_|  `\`---'//  |_/ |
                       \  .-\__ `-. -'__/-.  /
                     ___`. .'  /--.--\  `. .'___
                  ."" '<  `.___\_<|>_/___.' _> \"".
                 | | :  `- \`. ;`. _/; .'/ /  .' ; |
                 \  \ `-.   \_\_`. _.'_/_/  -' _.' /
       ===========`-.`___`-.__\ \___  /__.-'_.'_.-'===========
                               `=--=-'               kumamon*/
