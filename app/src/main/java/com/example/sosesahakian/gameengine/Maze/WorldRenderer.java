package com.example.sosesahakian.gameengine.Maze;

import android.graphics.Bitmap;

import com.example.sosesahakian.gameengine.GameEngine;

/**
 * Created by sosesahakian on 08/11/2017.
 */

public class WorldRenderer
{
    GameEngine gameEngine;
    World world;
    Bitmap keyImage;
    Bitmap wallImage;
    Bitmap bobImage;
    Bitmap treasureImage;
    Bitmap monsterImage;
    Bitmap resizedBob;
    Bitmap resizedKey;



    public WorldRenderer(GameEngine gameEngine, World world)
    {
        this.gameEngine = gameEngine;
        this.world = world;
        this.keyImage = gameEngine.loadBitmap("mazeassets/bluekey.png");
        this.wallImage = gameEngine.loadBitmap("mazeassets/wall.png");
        this.bobImage = gameEngine.loadBitmap("mazeassets/bob.png");
        this.treasureImage = gameEngine.loadBitmap("mazeassets/treasure.png");
        this.monsterImage = gameEngine.loadBitmap("carscrollerassets/xyellowmonster.png");
        //resizedBob = Bitmap.createScaledBitmap(bobImage, (int)world.bob.WIDTH, (int)world.bob.HEIGHT, true);
        //resizedKey = Bitmap.createScaledBitmap(keyImage, 20, 20, true);
    }

    public void render()
    {
        gameEngine.drawBitmap(bobImage, world.bob.x, world.bob.y);
        //gameEngine.drawBitmap(resizedKey, world.key.x, world.key.y);
        //gameEngine.drawBitmap(monsterImage, );

        //gameEngine.drawBitmap(wallImage, );
    }
}
