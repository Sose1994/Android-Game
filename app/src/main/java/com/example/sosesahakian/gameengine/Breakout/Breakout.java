package com.example.sosesahakian.gameengine.Breakout;

import com.example.sosesahakian.gameengine.GameEngine;
import com.example.sosesahakian.gameengine.Screen;

/**
 * Created by sosesahakian on 06/10/2017.
 */

//This is the "main" game which extends GameEngine, meaning it get's all the gameengine stuff
//We override the createStartScreen and load some music and
//Then we return a new MainMenuScreen with a gameengine and we want the Breakout gameengine so that's why "this"

public class Breakout extends GameEngine
{
    @Override
    public Screen createStartScreen()
    {
        //music = this.loadMusic("breakout_assets/music.ogg");
        return new MainMenuScreen(this);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        music.pause();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        //music.play();
    }
}