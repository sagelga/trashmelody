package com.trashmelody.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.files.FileHandle;
import com.trashmelody.TrashMelody;
import lt.ekgame.beatmap_analyzer.beatmap.mania.ManiaBeatmap;
import lt.ekgame.beatmap_analyzer.beatmap.osu.OsuBeatmap;
import lt.ekgame.beatmap_analyzer.difficulty.ManiaDifficulty;
import lt.ekgame.beatmap_analyzer.difficulty.OsuDifficulty;
import lt.ekgame.beatmap_analyzer.parser.BeatmapException;
import lt.ekgame.beatmap_analyzer.parser.BeatmapParser;
import lt.ekgame.beatmap_analyzer.performance.Performance;
import lt.ekgame.beatmap_analyzer.performance.scores.Score;
import lt.ekgame.beatmap_analyzer.utils.Mod;
import lt.ekgame.beatmap_analyzer.utils.Mods;

import java.io.File;
import java.io.FileNotFoundException;

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
		config.addIcon("GameLogo/game-logo-MacOS.png", Files.FileType.Internal);
		config.addIcon("GameLogo/game-logo-Linux.png", Files.FileType.Internal);
		config.addIcon("GameLogo/game-logo-Windows.png", Files.FileType.Internal);
		config.addIcon("GameLogo/game-logo.jpeg", Files.FileType.Internal);

		new LwjglApplication(new TrashMelody(), config);
	}
}
