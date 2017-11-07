package com.example.sosesahakian.gameengine.Breakout;

import android.graphics.Bitmap;

import com.example.sosesahakian.gameengine.GameEngine;
import com.example.sosesahakian.gameengine.Music;
import com.example.sosesahakian.gameengine.Screen;
import com.example.sosesahakian.gameengine.Sound;

/**
 * Created by sosesahakian on 06/10/2017.
 */

public class MainMenuScreen extends Screen
{
    Bitmap mainMenu = null;
    Bitmap insertCoin = null;
    float passedTime = 0;
    long startTime = System.nanoTime();

    Music music = null;
    Sound sound = null;

    public MainMenuScreen(GameEngine gameEngine)
    {
        super(gameEngine);
        mainMenu = gameEngine.loadBitmap("breakoutassets/mainmenu.png");
        insertCoin = gameEngine.loadBitmap("breakoutassets/insertcoin.png");
        sound = gameEngine.loadSound("breakoutassets/explosion.ogg");
        music = gameEngine.loadMusic("breakoutassets/music.ogg");

        music.setLooping(true);
        music.play();

    }

    @Override
    public void update(float deltaTime)
    {
        if (gameEngine.isTouchDown(0))
        {
            sound.play(1);
            gameEngine.setScreen(new GameScreen(gameEngine));
            return;
        }

        gameEngine.drawBitmap(mainMenu, 0, 0);

        passedTime = passedTime + deltaTime;
        if ( (passedTime - (int)passedTime) > 0.5f )
        {
            gameEngine.drawBitmap(insertCoin, 160 - (insertCoin.getWidth() / 2), 350);
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
