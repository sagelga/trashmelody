package com.trashmelody.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.trashmelody.TrashMelody;
import com.trashmelody.managers.StatsManager;

import static com.trashmelody.utils.RenderingUtils.getViewportHeight;
import static com.trashmelody.utils.RenderingUtils.getViewportWidth;

public class Debugger extends ScreenAdapter {
    public static StatsManager statsManager;
    private TrashMelody game;

    private static int line_margin = 20;

    public static boolean debug_mode = false;
    private static double frame_count;

    public static void runDebugger(SpriteBatch batch, BitmapFont font){
        // This method will show the debugger interfaces
        debugShow(batch,font,"Press '1' to toggle debug page", 1);
        debugShow(batch,font,"[ Trash Melody ] Debugger v1.0", 2);
        debugShow(batch,font,"Window Resolution : " + getViewportWidth() + " x " + getViewportHeight(),3);
        debugShow(batch,font,"Cursor Coordinates : " + Gdx.input.getX() + " x " + Gdx.input.getY(), 4);
        debugShow(batch,font,"FPS : " + Gdx.graphics.getFramesPerSecond() + " (" + (Gdx.graphics.getDeltaTime() * Gdx.graphics.getFramesPerSecond()) + ")",5);
        debugShow(batch,font,"Current Frames : " + (int) frame_count,6);
        debugShow(batch, font, "Active Threads : " + Thread.activeCount() , 7);
        debugShow(batch,font, "Operating System : " + System.getProperty("os.name") + " " + System.getProperty("os.version") + " (" + getOSType() + ")",8);
        debugShow(batch,font,"Music Volume : "  + statsManager.getCurrentMusicVolume(),9);
        debugShow(batch,font,"Current Music Track" + statsManager.getCurrentMusicTrack(),10);

        frame_count += Gdx.graphics.getDeltaTime() * Gdx.graphics.getFramesPerSecond();
    }

    public static void runDebugger(SpriteBatch batch, BitmapFont font, long time_lapsed){
        runDebugger(batch, font);
        debugShow(batch, font, "Time Lapsed : " + time_lapsed/1000, 11);
    }

    public static void runDebugger(SpriteBatch batch, BitmapFont font, long time_lapsed, float asset_load){
        runDebugger(batch, font);
        debugShow(batch,font,"Assets Loaded : " + asset_load*100 + "% (" + time_lapsed/1000 + " s)",11);
    }

    private static String getOSType() {
        switch (Gdx.app.getType()) {
            case iOS: return "iOS";
            case Android: return "Android";
            case Applet: return "Applet";
            case Desktop: return "Desktop";
            case WebGL: return "WebGL";
        }
        return "Unidentified by Java";
    }

    public static void debugShow(SpriteBatch batch, BitmapFont font, String text, int line){
        // This method minimize the debug code thing.
        font.draw(batch, text,30,lineMarginCalculate(line));
    }

    public static float lineMarginCalculate(int line_count){
        // Returns the Y coordinates that have been margin.
        return getViewportHeight() - (line_count * line_margin);
    }
}
