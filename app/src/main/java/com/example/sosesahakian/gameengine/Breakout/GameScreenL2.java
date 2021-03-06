package com.example.sosesahakian.gameengine.Breakout;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;

import com.example.sosesahakian.gameengine.GameEngine;
import com.example.sosesahakian.gameengine.Screen;
import com.example.sosesahakian.gameengine.Sound;
import com.example.sosesahakian.gameengine.TouchEvent;

import java.util.List;

/**
 * Created by sosesahakian on 12/11/2017.
 */

public class GameScreenL2 extends Screen
{
    enum State
    {
        Paused,
        Running,
        GameOver
    }

    WorldL2 world2;
    WorldRendererL2 worldRenderer;
    GameScreen.State state      = GameScreen.State.Running;

    //Load Level2 background
    Bitmap background = null;
    Bitmap resume     = null;
    Bitmap gameOver   = null;

    Typeface font     = null;

    Sound bounceSound   = null;
    Sound blockSound    = null;
    Sound gameOverSound = null;

    public GameScreenL2(GameEngine gameEngine)
    {
        super(gameEngine);
        world2 = new WorldL2(gameEngine, new CollisionListener()
        {
            @Override
            public void collisionWall() {
                bounceSound.play(1);
            }

            @Override
            public void collisionPaddle() {
                bounceSound.play(1);
            }

            @Override
            public void collisionBlock() {
                blockSound.play(1);
            }

            @Override
            public void gameOver() {
                gameOverSound.play(1);
            }
        } );
        worldRenderer = new WorldRendererL2(gameEngine, world2);

        //Bitmaps
        //Load level2 bitmap
        background = gameEngine.loadBitmap("breakout_assets/background.png");
        resume = gameEngine.loadBitmap("breakout_assets/resume.png");
        gameOver = gameEngine.loadBitmap("breakout_assets/gameover.png");

        //Init font
        font = gameEngine.loadFont("breakout_assets/font.ttf");

        //Sounds
        bounceSound   = gameEngine.loadSound("breakout_assets/bounce.wav");
        blockSound    = gameEngine.loadSound("breakout_assets/blocksplosion.wav");
        gameOverSound = gameEngine.loadSound("breakout_assets/gameover.wav");
    }

    @Override
    public void update(float deltaTime)
    {
        if(world2.gameOver)
        {
            state = GameScreen.State.GameOver;
        }

        if(state == GameScreen.State.Paused && gameEngine.getTouchEvents().size() > 0)
        {
            state = GameScreen.State.Running;
            resume();
        }

        if(state == GameScreen.State.GameOver)
        {
            List<TouchEvent> events = gameEngine.getTouchEvents();
            for(int i = 0; i < events.size(); i++)
            {
                if (events.get(i).type == TouchEvent.TouchEventType.Up)
                {
                    gameEngine.setScreen(new MainMenuScreen(gameEngine));
                    return;
                }
            }
        }

        if(state == GameScreen.State.Running && gameEngine.getTouchY(0) < 38 && gameEngine.getTouchX(0) > 280)
        {
            state = GameScreen.State.Paused;
            pause();
            return;
        }

        //Load new background?
        gameEngine.drawBitmap(background, 0, 0);

        if(state == GameScreen.State.Running)
        {
            world2.update(deltaTime, gameEngine.getAccelerometer()[0]);
        }
        worldRenderer.render();

        gameEngine.drawText(font, ("LIVES " + Integer.toString(world2.lives) + " | POINTS" + Integer.toString(world2.points)), 24, 24, Color.GREEN, 12);

        if(state == GameScreen.State.Paused)
        {
            gameEngine.drawBitmap(resume, 160 - (resume.getWidth() / 2), 240 - (resume.getHeight() / 2));
        }

        if(state == GameScreen.State.GameOver)
        {
            gameEngine.drawBitmap(gameOver, 160 - (gameOver.getWidth() / 2), 240 - (gameOver.getHeight() / 2));
        }
    }

    @Override
    public void pause()
    {
        if(state == GameScreen.State.Running) state = GameScreen.State.Paused;
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