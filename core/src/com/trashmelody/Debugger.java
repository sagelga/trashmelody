package com.trashmelody;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Application.ApplicationType;
import com.trashmelody.Assets;
import com.trashmelody.TrashMelody;

import javax.inject.Inject;

import static com.trashmelody.Utils.*;

import static com.trashmelody.Utils.*;

public class Debugger extends ScreenAdapter{
    private TrashMelody game;
    private static final float X_VAL = getViewportWidth();
    private static final float Y_VAL = getViewportHeight();

    private static int line_margin = 20;
    public static boolean debug_mode = true;
    private static double frame_count;
    public static void runDebugger(SpriteBatch batch, BitmapFont font, String current_page){
        // This method will show the debugger interfaces

        debugShow(batch,font,"Press '1' to toggle debug page", 1);
        debugShow(batch,font,"[ Trash Melody ] Debugger v1.0", 2);
        debugShow(batch,font,"True Screen Resolution : " + X_VAL + " x " + Y_VAL,3);
//        debugShow(batch,font,"Dedicated Screen Resolution : " + DesktopLauncher.screen_width +" x " + DesktopLauncher.screen_height + " (" + DesktopLauncher.screen_scale + ") ",4);
        debugShow(batch,font,"Cursor Coordinates : " + Gdx.input.getX() + " x " + Gdx.input.getY(), 4);
        debugShow(batch,font,"FPS : " + Gdx.graphics.getFramesPerSecond() + " (" + (Gdx.graphics.getDeltaTime() * Gdx.graphics.getFramesPerSecond()) + ")",5);
        debugShow(batch,font,"Current Frames : " + (int) frame_count,6);
//        debugShow(batch,font,"Frame Loaded : " + Gdx.graphics.getDeltaTime() * 60,6);
        debugShow(batch, font, "Threading Use : " + Thread.activeCount() , 7);
        debugShow(batch,font,"Current Page : " + current_page,8);

        frame_count += Gdx.graphics.getDeltaTime() * Gdx.graphics.getFramesPerSecond();
    }

    private static String osType;

    public static void runAdvancedDebugger(SpriteBatch batch, BitmapFont font, float asset_progress, float delay_progress){

        // Show the user basic platform.
        if      (Gdx.app.getType() == ApplicationType.iOS)      { osType = "iOS"; }
        else if (Gdx.app.getType() == ApplicationType.Android)  { osType = "Android"; }
        else if (Gdx.app.getType() == ApplicationType.Applet)   { osType = "Applet"; }
        else if (Gdx.app.getType() == ApplicationType.Desktop)  { osType = "Desktop"; }
        else if (Gdx.app.getType() == ApplicationType.WebGL)    { osType = "WebGL"; }

        debugShow(batch,font,
                "Operating System : " + System.getProperty("os.name") + " "
                     + System.getProperty("os.version") + " (" + osType + ")",9);
        // This method will show advanced information about game screen.
        if (delay_progress != 0) {
            debugShow(batch, font, "Delay Cool Down : " + delay_progress + " %", 10);
        }
    }

    private static void debugShow(SpriteBatch batch, BitmapFont font, String text, int line){
        // This method minimize the debug code thing.
        font.draw(batch, text,30,lineMarginCalculate(line));
    }
    private static float lineMarginCalculate(int line_count){
        // Returns the Y coordinates that have been margin.
        return Y_VAL - (line_count * line_margin);
    }
}
