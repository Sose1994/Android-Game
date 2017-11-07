package com.example.sosesahakian.gameengine;

/**
 * Created by sosesahakian on 02/10/2017.
 */

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;

import java.util.List;

public class TestScreen extends Screen
{
    Bitmap bob = null;
    int bobX = 0;
    int bobY = 500;
    TouchEvent event = null;
    Sound sound = null;
    Music music = null;
    boolean isPlaying = false;
    private int framesPerSecond = 0;

    public TestScreen(GameEngine gameEngine)
    {
        super(gameEngine);
        bob = gameEngine.loadBitmap("bob.png");
        sound = gameEngine.loadSound("blocksplosion.wav");
        music = gameEngine.loadMusic("breakoutassets/music.ogg");
        music.setLooping(true);
        music.play();
        isPlaying = true;
    }

    @Override
    public void update(float deltaTime)
    {
        gameEngine.clearFrameBuffer(Color.BLUE);
        //gameEngine.drawBitmap(bob, 10, 10);
        //gameEngine.drawBitmap(bob, 100, 200, 0, 0, 64, 64);

        /*
        for( int pointer = 0; pointer < 5; pointer++ )
        {
            if( gameEngine.isTouchDown(pointer) )
            {
                gameEngine.drawBitmap(sose, gameEngine.getTouchX(pointer), gameEngine.getTouchY(pointer));
            }
        }
        */

        bobX = bobX + (int)(100 * deltaTime);

        if ( bobX > gameEngine.getFrameBufferWidth()) bobX = 0 - bob.getWidth();

        List<TouchEvent> touchEvents = gameEngine.getTouchEvents();
        int stop = touchEvents.size();

        if(stop == 0 && event != null)
        {
            gameEngine.drawBitmap(bob, gameEngine.getTouchX(event.pointer), gameEngine.getTouchY(event.pointer));
        }

        for(int i = 0; i < stop; i++)
        {
            event = touchEvents.get(i);
            //Log.d("TestScreen", "*** Event touch type: " + event.type + ", x: " + event.x + ", y: " + event.y);
            gameEngine.drawBitmap(bob, gameEngine.getTouchX(event.pointer), gameEngine.getTouchY(event.pointer));
            if(event.type == TouchEvent.TouchEventType.Down)
            {
                sound.play(1);

            }
        }

        if (gameEngine.isTouchDown(0))
        {
            if (music.isPlaying())
            {
                music.pause();
                isPlaying = false;
            }
            else
            {
                music.play();
                isPlaying = true;
            }

        }

        /*
        float accX = gameEngine.getAccelerometer()[0];
        float accY = gameEngine.getAccelerometer()[1];
        //accX = 0; accY = 0;
        float x    = gameEngine.getFrameBufferWidth() / 2 + (accX/10) * gameEngine.getFrameBufferWidth();
        float y    = gameEngine.getFrameBufferHeight() / 2 + (accY/10) * gameEngine.getFrameBufferHeight();
        gameEngine.drawBitmap(sose, (int) (x - (sose.getWidth()/2)), (int) (y - (sose.getHeight()/2)));
        */

    }

    @Override
    public void pause()
    {
        Log.d("TestScreen", "*** Pausing");
        music.pause();
    }

    @Override
    public void resume()
    {
        Log.d("TestScreen", "*** Resuming");
        if (!isPlaying)
        {
            music.play();
            isPlaying = true;
        }
    }

    @Override
    public void dispose()
    {
        Log.d("TestScreen", "*** Disposed");
        music.dispose();
    }
}