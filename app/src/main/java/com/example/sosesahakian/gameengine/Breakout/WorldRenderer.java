package com.example.sosesahakian.gameengine.Breakout;

import android.graphics.Bitmap;

import com.example.sosesahakian.gameengine.GameEngine;

/**
 * Created by sosesahakian on 10/10/2017.
 */

public class WorldRenderer
{
    GameEngine gameEngine;
    World world;
    Bitmap ballImage;
    Bitmap paddleImgage;
    Bitmap blocksImage;

    public WorldRenderer(GameEngine gameEngine, World world)
    {
        this.gameEngine = gameEngine;
        this.world = world;
        this.ballImage = gameEngine.loadBitmap("breakoutassets/ball.png");
        this.paddleImgage = gameEngine.loadBitmap("breakoutassets/paddle.png");
        this.blocksImage = gameEngine.loadBitmap("breakoutassets/blocks.png");
        //this.paddleImgage = gameEngine.loadBitmap("breakoutassets/paddle.png");
    }

    public void render()
    {
        gameEngine.drawBitmap(ballImage, world.ball.x, world.ball.y);
        gameEngine.drawBitmap(paddleImgage, (int)world.paddle.x, (int)world.paddle.y);
        int listSize = world.blocks.size();
        Block block = null;
        for (int i = 0; i < listSize; i++) //draw the blocks in rows and colums
        {
            block= world.blocks.get(i);
            gameEngine.drawBitmap(blocksImage, (int)block.x, (int)block.y,
            0, (int)(block.type * block.HEIGHT), (int)Block.WIDTH, (int)Block.HEIGHT);
        }
    }
}
