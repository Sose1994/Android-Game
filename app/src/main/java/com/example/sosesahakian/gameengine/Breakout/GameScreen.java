package com.example.sosesahakian.gameengine.Breakout;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;

import com.example.sosesahakian.gameengine.GameEngine;
import com.example.sosesahakian.gameengine.Music;
import com.example.sosesahakian.gameengine.Screen;
import com.example.sosesahakian.gameengine.Sound;
import com.example.sosesahakian.gameengine.TouchEvent;

import java.util.List;

/**
 * Created by sosesahakian on 06/10/2017.
 */

public class GameScreen extends Screen
{
    enum State
    {
        Pause,
        Running,
        GameOver
    }

    World world = null;
    WorldRenderer worldRenderer = null;

    Bitmap gameOver = null;
    Bitmap resume = null;
    Bitmap background = null;
    Music music = null;
    Sound explosion = null;
    Sound gameOverSound = null;
    Sound bounce = null;
    Sound blockSound = null;

    State state = State.Running;

    Typeface font = null;


    public GameScreen(GameEngine gameEngine)
    {
        super(gameEngine);
        world = new World(gameEngine, new CollisionListener()
        {

            @Override
            public void collisionWall()
            {
                bounce.play(1);
            }

            @Override
            public void collisionPaddle()
            {
                bounce.play(1);
            }

            @Override
            public void collisionBlock()
            {
                bounce.play(1);
            }
        });
        worldRenderer = new WorldRenderer(gameEngine, world);

        background = gameEngine.loadBitmap("breakoutassets/background.png");
        music = gameEngine.loadMusic("breakoutassets/music.ogg");
        explosion = gameEngine.loadSound("breakoutassets/explosion.ogg");
        resume = gameEngine.loadBitmap("breakoutassets/resume.png");
        gameOver = gameEngine.loadBitmap("breakoutassets/gameover.png");
        gameOverSound= gameEngine.loadSound("breakoutassets/gameover.wav");
        font = gameEngine.loadFont("breakoutassets/font.ttf");
        bounce = gameEngine.loadSound("breakoutassets/bouncs.wav");
        blockSound = gameEngine.loadSound("breakoutassets/blocksplosion.wav");

        music.setLooping(true);
        music.play();

    }

    @Override
    public void update(float deltaTime)
    {
        if (world.gameOver)
        {
            state = State.GameOver;
        }

        if (state == State.Pause && gameEngine.getTouchEvents().size() > 0)
        {
            state = State.Running;
            resume();
        }

        if (state == State.GameOver && gameEngine.getTouchEvents().size() > 0)
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

        if (state == State.Running && gameEngine.getTouchY(0) < 38 && gameEngine.getTouchX(0) > 320-38)
        {
            state = State.Pause;
            return;
        }

        if (state == State.Running)
        {
            world.update(deltaTime, gameEngine.getAccelerometer()[0]);
        }


        gameEngine.drawBitmap(background, 0, 0);

        worldRenderer.render();

        gameEngine.drawText(font, ("Points: " + world.points + "lives: " + world.lives), 24, 20, Color.GREEN, 15);

        if (state == State.Pause)
        {
            gameEngine.drawBitmap(resume, 160 - resume.getWidth()/2, 240 - resume.getHeight()/2);
        }

        if (state == State.GameOver)
        {
            gameEngine.drawBitmap(gameOver, 160 - gameOver.getWidth()/2, 240 - gameOver.getHeight()/2);
        }

    }

    @Override
    public void pause()
    {
        if (state == State.Running) state = State.Pause;
        gameEngine.music.pause();
    }

    @Override
    public void resume()
    {
        gameEngine.music.play();
    }

    @Override
    public void dispose()
    {

    }
}
