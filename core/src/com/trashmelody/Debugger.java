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
import static io.vavr.API.println;

public class Debugger extends ScreenAdapter{
    private TrashMelody game;
    private static int line_margin = 20;
    private static String osType;

    public static void runDebugger(SpriteBatch batch, BitmapFont font, String current_page){
        // This method will show the debugger interfaces

        debugShow(batch,font,"Debugger v1.0", 1);
        debugShow(batch,font,"Trash Melody v1.0", 2);
        debugShow(batch,font,"Screen Resolution : " + getViewportWidth() + " x " + getViewportHeight() + "    " + "Cursor Coordinates : " + "XXX" + " x " + "YYY", 3);
        debugShow(batch,font, "Current Page : " + current_page,4);
    }

    public static void runAdvancedDebugger(SpriteBatch batch, BitmapFont font, float asset_progress, float delay_progress){
        // This method will show advanced information about game screen.
        debugShow(batch,font,"Asset Loaded : " + asset_progress + " %",5);
        debugShow(batch,font,"Delay Cooldown : " + delay_progress + " %",6);

        if(Gdx.app.getType() == ApplicationType.iOS) {
            osType = "iOS";
        } else if (Gdx.app.getType() == ApplicationType.Android){
            osType = "Android";
        } else if (Gdx.app.getType() == ApplicationType.Applet){
            osType = "Applet";
        } else if (Gdx.app.getType() == ApplicationType.Desktop){
            osType = "Desktop";
        } else if (Gdx.app.getType() == ApplicationType.WebGL){
            osType = "WebGL";
        }

        debugShow(batch,font,"OS used : " + osType,7);
    }

    public static void logScreenResolution() {
        println(String.format("Height = %d", Gdx.graphics.getHeight()));
        println(String.format("Weight = %d", Gdx.graphics.getWidth()));
    }

    private static void debugShow(SpriteBatch batch, BitmapFont font, String text, int line){
        // This method minimize the debug code thing.
        font.draw(batch, text,30,lineMarginCalculate(line));
    }

    private static float lineMarginCalculate(int line_count){
        // Returns the Y coordinates that have been margin.
        return getViewportHeight() - (line_count * line_margin);
    }
}
