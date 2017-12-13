package com.example.sosesahakian.gameengine.Maze;

import android.graphics.Bitmap;

import com.example.sosesahakian.gameengine.GameEngine;
import com.example.sosesahakian.gameengine.Screen;

/**
 * Created by sosesahakian on 08/11/2017.
 */

public class MainMenuScreen extends Screen
{
    Bitmap menu = null;
    Bitmap start = null;
    Bitmap resizedstart;


    public MainMenuScreen(GameEngine gameEngine)
    {
        super(gameEngine);

        menu = gameEngine.loadBitmap("mazeassets/grey.png");
        start = gameEngine.loadBitmap("mazeassets/startgame.jpg");
        resizedstart = Bitmap.createScaledBitmap(start, 200, 60, true);
    }

    @Override
    public void update(float deltaTime)
    {
        if (gameEngine.isTouchDown(0))
        {
            gameEngine.setScreen(new GameScreen(gameEngine));
            return;
        }

        gameEngine.drawBitmap(menu, 0, 0);


        gameEngine.drawBitmap(resizedstart, 60, 200);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
