package com.example.sosesahakian.gameengine.CarScroller;

import com.example.sosesahakian.gameengine.Breakout.MainMenuScreen;
import com.example.sosesahakian.gameengine.GameEngine;
import com.example.sosesahakian.gameengine.Screen;

/**
 * Created by sosesahakian on 04/11/2017.
 */

public class Carscroller extends GameEngine
{
    @Override
    public Screen createStartScreen()
    {
        //music = this.loadMusic("music.ogg");
        return new MainMenuScreen(this);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        //music.pause();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        //music.play();
    }
}
