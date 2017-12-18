package com.example.sosesahakian.gameengine.Maze;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;

import com.example.sosesahakian.gameengine.GameEngine;
import com.example.sosesahakian.gameengine.Music;
import com.example.sosesahakian.gameengine.Screen;
import com.example.sosesahakian.gameengine.Sound;

/**
 * Created by sosesahakian on 08/11/2017.
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
    //Music music = null;
    Sound gameOverSound = null;
    Typeface font;
    State state = State.Running;
    float gameOverScreenCount = 5;
    Sound collectSound= null;
    Bitmap background = null;

    public GameScreen(GameEngine gameEngine)
    {
        super(gameEngine);
        world = new World(gameEngine, new CollisionListener()
        {
            @Override
            public void collisionKey() {
                collectSound.play(1);
            }
        });
        worldRenderer = new WorldRenderer(gameEngine, world);

        //background = gameEngine.loadBitmap("");
        //gameEngine.music = gameEngine.loadMusic("breakoutassets/music.ogg");
        resume = gameEngine.loadBitmap("breakoutassets/resume.png");
        gameOver = gameEngine.loadBitmap("breakoutassets/gameover.png");
        gameOverSound = gameEngine.loadSound("breakoutassets/gameover.wav");
        font = gameEngine.loadFont("breakoutassets/font.ttf");
        background = gameEngine.loadBitmap("mazeassets/background.jpg");

    }

    @Override
    public void update(float deltaTime)
    {

        gameEngine.clearFrameBuffer(Color.WHITE);

        if (state != State.GameOver)
        {
            world.update(deltaTime);
            worldRenderer.render();
            gameEngine.drawText(font, Integer.toString((int)world.countdown), 10, 30, Color.RED,20);
        }

        if (state == State.GameOver)
        {
            if (world.isWon)
            {
                gameEngine.drawText(font, "YOU WON!", 40, 240, Color.BLACK, 38);
            }
            else
            {
                gameEngine.drawText(font, "YOU LOST!", 40, 240, Color.BLACK, 38);

            }

            gameOverScreenCount -= deltaTime;

            if (gameOverScreenCount < 0)
            {
                gameEngine.setScreen(new MainMenuScreen(gameEngine));
            }

        }

        if (world.gameOver)
        {
            state = State.GameOver;
        }

        if (state == State.Pause && gameEngine.getTouchEvents().size() > 0)
        {
            state = State.Running;
        }
    }

    @Override
    public void pause()
    {
        if (state == State.Running) state = State.Pause;
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
