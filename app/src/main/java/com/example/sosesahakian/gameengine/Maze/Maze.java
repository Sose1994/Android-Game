package com.example.sosesahakian.gameengine.Maze;

import com.example.sosesahakian.gameengine.Breakout.*;
import com.example.sosesahakian.gameengine.GameEngine;
import com.example.sosesahakian.gameengine.Screen;

/**
 * Created by sosesahakian on 08/11/2017.
 */

public class Maze extends GameEngine
{
    @Override
    public Screen createStartScreen()
    {
        music = loadMusic("breakoutassets/music.ogg");
        music.setLooping(true);
        music.play();
        return new MainMenuScreen(this);


    }

    //Then we have an onPause and onResume which called the onpause and onresume in the super,
    // which means the gameengine will pause or resume the game
    public void onPause()
    {
        super.onPause();
        music.pause();
    }

    public void onResume()
    {
        super.onResume();
//        music.play();
    }
}
