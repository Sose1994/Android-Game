package com.example.sosesahakian.gameengine.CarScroller;

import android.graphics.Bitmap;
import android.graphics.Typeface;

import com.example.sosesahakian.gameengine.Breakout.*;
import com.example.sosesahakian.gameengine.Breakout.CollisionListener;
import com.example.sosesahakian.gameengine.GameEngine;
import com.example.sosesahakian.gameengine.Screen;
import com.example.sosesahakian.gameengine.Sound;
import com.example.sosesahakian.gameengine.TouchEvent;

import java.util.List;

/**
 * Created by sosesahakian on 04/11/2017.
 */

public class GameScreen extends Screen
{
    enum State
    {
        Paused,
        Running,
        GameOver
    }

    World world;
    WorldRenderer worldRenderer;
    State state = State.Running;

    Bitmap background = null;
    float backgroundX = 0;
    Bitmap resume = null;
    Bitmap gameOver = null;

    //Typeface font = null;

    Sound bounceSound = null;
    Sound blockSound = null;
    Sound gameOverSound = null;

    public GameScreen(GameEngine gameEngine)
    {
        super(gameEngine);
        world = new World(gameEngine, new com.example.sosesahakian.gameengine.CarScroller.CollisionListener() {
            @Override
            public void collisionWall() {

            }

            @Override
            public void collisionMonster() {

            }

            @Override
            public void gameOver() {

            }
        });

        worldRenderer = new WorldRenderer(gameEngine, world);

        //Bitmaps
        background = gameEngine.loadBitmap("carscrollerassets/xcarbackground.png");
        resume = gameEngine.loadBitmap("carscrollerassets/resume.png");
        gameOver = gameEngine.loadBitmap("carscrollerassets/gameover.png");
    }

    @Override
    public void update(float deltaTime) {
        if (world.gameOver) {
            state = State.GameOver;
        }

        if (state == State.Paused && gameEngine.getTouchEvents().size() > 0)
        {
            state = State.Running;
            resume();
        }

        if (state == State.GameOver)
        {
            List<TouchEvent> events = gameEngine.getTouchEvents();
            for (int i = 0; i < events.size(); i++)
            {
                if (events.get(i).type == TouchEvent.TouchEventType.Up)
                {
                    gameEngine.setScreen(new MainMenuScreen(gameEngine));
                    return;
                }
            }
        }

        if (state == State.Running && gameEngine.getTouchY(0) < 38 && gameEngine.getTouchX(0) > 280)
        {
            state = State.Paused;
            pause();
            return;
        }

        //Scroll the background image
        backgroundX = backgroundX + 100 * deltaTime;

        if (backgroundX > 2700 - 480)
        {
            backgroundX = 0;
        }

        gameEngine.drawBitmap(background, 0, 0, (int) backgroundX, 0, 480, 320);

        if ( state == State.Running)
        {
            world.update(deltaTime, gameEngine.getAccelerometer()[1]);
        }


    }


    @Override
    public void pause()
    {
        if (state == State.Running) state = State.GameOver.Paused;
        //gameEngine.music.pause();
    }

    @Override
    public void resume()
    {
        //gameEngine.music.play();
    }

    @Override
    public void dispose()
    {

    }
}

