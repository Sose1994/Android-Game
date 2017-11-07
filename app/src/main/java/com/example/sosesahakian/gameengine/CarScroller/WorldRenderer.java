package com.example.sosesahakian.gameengine.CarScroller;

import android.graphics.Bitmap;

import com.example.sosesahakian.gameengine.GameEngine;

/**
 * Created by sosesahakian on 05/11/2017.
 */

public class WorldRenderer
{
    GameEngine gameEngine;
    World world;

    Bitmap carImage;

    public WorldRenderer(GameEngine gameEngine, World world)
    {
        this.gameEngine = gameEngine;
        this.world = world;

        carImage = gameEngine.loadBitmap("carscrollerassets/xbluecar2.png");
    }

    public void render()
    {
        gameEngine.drawBitmap(carImage, world.car.x, world.car.y);
    }
}
