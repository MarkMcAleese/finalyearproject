/**
 * Created by Carl on 01/03/2017.
 */

package uk.ac.qub.eeecs.pranc.kingsofqueens.gage.game;

import java.util.List;

import uk.ac.qub.eeecs.pranc.kingsofqueens.gage.Game;
import uk.ac.qub.eeecs.pranc.kingsofqueens.gage.engine.audio.Music;
import uk.ac.qub.eeecs.pranc.kingsofqueens.gage.engine.io.AssetStore;
import uk.ac.qub.eeecs.pranc.kingsofqueens.gage.engine.input.TouchEvent;
import uk.ac.qub.eeecs.pranc.kingsofqueens.gage.engine.input.Input;
import uk.ac.qub.eeecs.pranc.kingsofqueens.gage.engine.graphics.IGraphics2D;
import uk.ac.qub.eeecs.pranc.kingsofqueens.gage.engine.io.ElapsedTime;
import uk.ac.qub.eeecs.pranc.kingsofqueens.gage.engine.io.ScreenManager;
import uk.ac.qub.eeecs.pranc.kingsofqueens.gage.world.GameScreen;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.Log;

public class OptionsScreen extends GameScreen
{

    private Rect boundBckBtn,boundFrwBtn,boundTitle;
    AssetStore aStore=mGame.getAssetManager();

    public OptionsScreen(String newName, Game newGame)
    {
        super("Options Screen",newGame);
        aStore.loadAndAddBitmap("Options Title","img/OptionsScreenImages/OptionsTitle.PNG");
        aStore.loadAndAddBitmap("Back Button","img/OptionsScreenImages/back-button.png");
        aStore.loadAndAddBitmap("Forward Button","img/OptionsScreenImages/right-arrow.png");

    }

    public void update(ElapsedTime eTime)
    {


    }

    public void draw(ElapsedTime eTime, IGraphics2D iG2D) {
        try {
            Bitmap title = aStore.getBitmap("Options Title");
            Bitmap forward = aStore.getBitmap("Forward Button");
            Bitmap back = aStore.getBitmap("Back Button");

            if (boundTitle == null || boundFrwBtn == null || boundBckBtn == null) {
                int titleLeft = (iG2D.getSurfaceWidth() - title.getWidth());
                int titleRight = titleLeft + title.getWidth();
                int titleTop = (iG2D.getSurfaceHeight() / 2);
                int titleBottom = titleTop + title.getHeight();

                boundTitle = new Rect(titleLeft, titleTop, titleRight, titleBottom);

                int forwardLeft = (iG2D.getSurfaceWidth() - forward.getWidth());
                int forwardRight = forwardLeft + forward.getWidth();
                int forwardTop = (iG2D.getSurfaceHeight() / 2);
                int forwardBottom = forwardTop + forward.getHeight();

                boundFrwBtn = new Rect(forwardLeft, forwardTop, forwardRight, forwardBottom);

                int backLeft = (iG2D.getSurfaceWidth() - back.getWidth());
                int backRight = backLeft + back.getWidth();
                int backTop = (iG2D.getSurfaceHeight() / 2);
                int backBottom = backTop + back.getHeight();

                boundBckBtn = new Rect(backLeft, backTop, backRight, backBottom);

            }

            iG2D.clear(Color.rgb(255, 255, 255));

            iG2D.drawBitmap(title,null,boundTitle,null);
            iG2D.drawBitmap(forward,null,boundFrwBtn,null);
            iG2D.drawBitmap(back,null,boundBckBtn,null);


        }
        catch(Exception e)
        {
            String error=e.getMessage();
        }
    }
}
