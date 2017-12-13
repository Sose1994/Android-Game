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
    Bitmap blueKey;
    Bitmap redKey;
    Bitmap greenKey;
    Bitmap wallImage;
    Bitmap bobImage;
    Bitmap treasureImage;
    Bitmap monsterImage;
    Bitmap redDoor;
    Bitmap blueDoor;
    Bitmap greenDoor;

    Bitmap resizedBob;
    Bitmap resizedTreasure;


    public WorldRenderer(GameEngine gameEngine, World world)
    {
        this.gameEngine = gameEngine;
        this.world = world;

        this.blueKey = gameEngine.loadBitmap("mazeassets/bluekey.png");
        this.redKey = gameEngine.loadBitmap("mazeassets/redkey.png");
        this.greenKey = gameEngine.loadBitmap("mazeassets/greenkey.png");

        this.wallImage = gameEngine.loadBitmap("mazeassets/wall.png");
        this.bobImage = gameEngine.loadBitmap("mazeassets/bob.png");
        this.treasureImage = gameEngine.loadBitmap("mazeassets/treasure.png");
        this.monsterImage = gameEngine.loadBitmap("carscrollerassets/xyellowmonster.png");
        this.blueDoor = gameEngine.loadBitmap("mazeassets/blueDoor.png");
        this.greenDoor = gameEngine.loadBitmap("mazeassets/GreenDoor.png");
        this.redDoor = gameEngine.loadBitmap("mazeassets/redDoor.png");

        resizedBob = Bitmap.createScaledBitmap(bobImage, (int)world.bob.WIDTH, (int)world.bob.HEIGHT, true);

        resizedTreasure = Bitmap.createScaledBitmap(treasureImage, 40, 40, true);

        for (Wall w : world.walls)
        {
            w.bitmap = Bitmap.createScaledBitmap(wallImage, w.WIDTH, w.HEIGHT, true);
        }

        for (Door d : world.doors)
        {
            switch (d.color)
            {
                case Blue: d.bitmap = Bitmap.createScaledBitmap(blueDoor, d.WIDTH, d.HEIGHT, true);
                    break;
                case Red: d.bitmap = Bitmap.createScaledBitmap(redDoor, d.WIDTH, d.HEIGHT, true);
                    break;
                case Green: d.bitmap = Bitmap.createScaledBitmap(greenDoor, d.WIDTH, d.HEIGHT, true);
                    break;
            }


        }

        for (Key k : world.keys)
        {
            switch (k.color)
            {
                case Blue: k.bitmap = Bitmap.createScaledBitmap(blueKey, (int)k.WIDTH, (int)k.HEIGHT, true);
                    break;
                case Red: k.bitmap = Bitmap.createScaledBitmap(redKey, (int)k.WIDTH, (int)k.HEIGHT, true);
                    break;
                case Green: k.bitmap = Bitmap.createScaledBitmap(greenKey, (int)k.WIDTH, (int)k.HEIGHT, true);
                    break;
            }


        }
    }

    public void render()
    {
        gameEngine.drawBitmap(resizedBob, world.bob.x, world.bob.y);
        gameEngine.drawBitmap(resizedTreasure, 240, 420);

        for ( Wall w : world.walls)
        {
            gameEngine.drawBitmap(w.bitmap, w.x, w.y);
        }

        for ( Door d : world.doors)
        {
            gameEngine.drawBitmap(d.bitmap, d.x, d.y);
        }

        for ( Key k : world.keys)
        {
            gameEngine.drawBitmap(k.bitmap, k.x, k.y);
        }

    }
}
