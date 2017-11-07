package com.example.sosesahakian.gameengine.CarScroller;

import android.graphics.Bitmap;

import com.example.sosesahakian.gameengine.GameEngine;
import com.example.sosesahakian.gameengine.Screen;

/**
 * Created by sosesahakian on 05/11/2017.
 */

public class MainMenuScreen extends Screen
{

    Bitmap background = null;
    Bitmap startGame = null;
    float passedTime = 0;
    long startTime = System.nanoTime();

    public MainMenuScreen(GameEngine gameEngine)
    {
        super(gameEngine);
        background = gameEngine.loadBitmap("carscrollerassets/xcarbackground.png");
        startGame = gameEngine.loadBitmap("carscrollerassets/xstartgame.png");
    }

    @Override
    public void update(float deltaTime)
    {
        if (gameEngine.isTouchDown(0))
        {
            gameEngine.setScreen(new GameScreen(gameEngine));
            return;
        }

        gameEngine.drawBitmap(background, 0, 0);

        passedTime += deltaTime;
        if (passedTime - (int) passedTime > 0.5f)
        {
            gameEngine.drawBitmap(startGame, 240 - (startGame.getWidth() / 2), 150);
        }
    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void dispose()
    {

    }
}
