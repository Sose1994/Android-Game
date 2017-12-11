package com.example.sosesahakian.gameengine.Maze;

import android.graphics.Bitmap;
import android.graphics.Color;

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
    Bitmap background = null;
    Music music = null;
    Sound gameOverSound = null;

    State state = State.Running;

    public GameScreen(GameEngine gameEngine)
    {
        super(gameEngine);

        world = new World(gameEngine, new CollisionListener()
        {
            @Override
            public void collisionFood() {

            }

            @Override
            public void collisionWall() {

            }

            @Override
            public void collisionEdge() {

            }
            //Sounds for when you take a treasure or key

        });
        worldRenderer = new WorldRenderer(gameEngine, world);

        //background = gameEngine.loadBitmap("");
        music = gameEngine.loadMusic("breakoutassets/music.ogg");
        resume = gameEngine.loadBitmap("breakoutassets/resume.png");
        gameOver = gameEngine.loadBitmap("breakoutassets/gameover.png");
        gameOverSound = gameEngine.loadSound("breakoutassets/gameover.wav");

        music.setLooping(true);
        music.play();
    }

    @Override
    public void update(float deltaTime)
    {
        gameEngine.clearFrameBuffer(Color.WHITE);
        world.update(deltaTime);
        worldRenderer.render();
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
        gameEngine.music.pause();
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
