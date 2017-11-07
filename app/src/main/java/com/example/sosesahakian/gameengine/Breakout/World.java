package com.example.sosesahakian.gameengine.Breakout;

import com.example.sosesahakian.gameengine.GameEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sosesahakian on 10/10/2017.
 */

public class World
{
    public static final float MIN_X = 0;
    public static final float MAX_X = 319;
    public static final float MIN_Y = 36;
    public static final float MAX_Y = 479;
    Ball ball = new Ball();
    Paddle paddle = new Paddle();
    List<Block> blocks = new ArrayList<>();
    GameEngine gameEngine;
    CollisionListener listener;
    boolean gameOver = false;
    int points = 0;
    int lives = 3;

    public World(GameEngine gameEngine, CollisionListener listener)
    {
        generateBlocks();
        this.gameEngine = gameEngine;
        this.listener = listener;
    }

    public void generateBlocks()
    {
        blocks.clear();
        for (int y = 50, type = 0; y < 50 + 8*Block.HEIGHT; y=y+(int)Block.HEIGHT, type++)//For each row
        {
            for (int x = 20; x < MAX_X - Block.WIDTH/2; x = x + (int)Block.WIDTH) // For each block
            {
                blocks.add(new Block(x, y, type));
            }
        }
    }



    public void update(float deltaTime, float accelX)
    {
        ball.x = ball.x + (int)(ball.vx * deltaTime);
        ball.y = ball.y + (int)(ball.vy * deltaTime);

        if (ball.x < MIN_X)
        {
            ball.vx = -ball.vx;
            ball.x = (int)MIN_X;
            listener.collisionWall();
        }

        if (ball.x > MAX_X - ball.WIDTH)
        {
            ball.vx = -ball.vx;
            ball.x = (int)(MAX_X - ball.WIDTH);
            listener.collisionWall();
        }

        if (ball.y < MIN_Y)
        {
            ball.vy = -ball.vy;
            ball.y = (int)MIN_Y;
            listener.collisionWall();
        }

        /*if (ball.y > MAX_Y - ball.HEIGHT)
        {
            ball.vy = -ball.vy;
            ball.y = (int)(MAX_X - ball.HEIGHT);
        }*/

        if (ball.y + Ball.HEIGHT > MAX_Y)
        {
            lives = lives - 1;

            if (lives == 0)
            {
                gameOver = true;
                return;
            }
            else
            {
                ball.y = (int)MAX_Y/2;
                if (ball.vy > 0) ball.vy = -ball.vy;
            }
        }

        paddle.x = paddle.x + accelX * deltaTime * 50;
        if (paddle.x < MIN_X) paddle.x = MIN_X;
        if (paddle.x + paddle.WIDTH > MAX_X) paddle.x =  MAX_X - Paddle.WIDTH;

        if (gameEngine.isTouchDown(0))
        {
            if (gameEngine.getTouchY(0) > 450)
            {
                paddle.x = gameEngine.getTouchX(0);
            }
        }

        collideBallPaddle();
        collideBallBlocks(deltaTime);

        //if all blocks are removed, regenerate or better: start a new level

        if (blocks.size() == 0)
        {
            generateBlocks();
        }
    }


    private void collideBallBlocks(float deltaTime)
    {

        Block block = null;
        for (int i = 0; i < blocks.size(); i++)
        {
            block = blocks.get(i);
            if (collideRectangles(ball.x, ball.y, Ball.WIDTH, Ball.HEIGHT, block.x, block.y, Block.WIDTH, Block.HEIGHT))
            {
                blocks.remove(i);
                listener.collisionBlock();
                i = i - 1;
                float oldvx = ball.vx;
                float oldvy = ball.vy;
                reflectBall(ball, block);
                ball.x = (int)(ball.x - oldvx * deltaTime * 1.01f);
                ball.y = (int)(ball.y - oldvy * deltaTime * 1.01f);
                points = points + (10 - block.type);
            }
        }
    }

    private void reflectBall(Ball ball, Block block)
    {
        if (collideRectangles(ball.x, ball.y, Ball.WIDTH, Ball.HEIGHT, block.x, block.y, 1, 1))
        {
            if (ball.vx > 0) ball.vx = - ball.vx;
            if (ball.vy > 0) ball.vy = - ball.vy;
            return;
        }

        if (collideRectangles(ball.x, ball.y, Ball.WIDTH, Ball.HEIGHT, block.x, block.y, 1, 1))
        {
            if (ball.vx < 0) ball.vx = -ball.vx;
            if (ball.vy > 0) ball.vy = -ball.vy;
            return;
        }

        if (collideRectangles(ball.x, ball.y, Ball.WIDTH, Ball.HEIGHT, block.x, block.y+ Block.HEIGHT, 1, 1))
        {
            if (ball.vx > 0) ball.vx = -ball.vx;
            if (ball.vy < 0) ball.vy = -ball.vy;
            return;
        }

        if (collideRectangles(ball.x, ball.y, Ball.WIDTH, Ball.HEIGHT, block.x + Block.WIDTH, block.y+ Block.HEIGHT, 1, 1))
        {
            if (ball.vx < 0) ball.vx = -ball.vx;
            if (ball.vy < 0) ball.vy = -ball.vy;
            return;
        }

        if (collideRectangles(ball.x, ball.y, Ball.WIDTH, Ball.HEIGHT, block.x, block.y, Block.WIDTH, 1))
        {
            ball.vy = -ball.vy;
            return;
        }

        if (collideRectangles(ball.x, ball.y, Ball.WIDTH, Ball.HEIGHT, block.x, block.y+Block.HEIGHT, Block.WIDTH, 1))
        {
            ball.vy = -ball.vy;
            return;
        }

        if (collideRectangles(ball.x, ball.y, Ball.WIDTH, Ball.HEIGHT, block.x, block.y, 1, Block.HEIGHT))
        {
            ball.vx = -ball.vx;
            return;
        }

        if (collideRectangles(ball.x, ball.y, Ball.WIDTH, Ball.HEIGHT, block.x + Block.WIDTH, block.y, 1, Block.HEIGHT))
        {
            ball.vx = -ball.vx;
            return;
        }
    }

    private boolean collideRectangles(float x1, float y1, float width1, float height1,
                                      float x2, float y2, float width2, float height2)
    {
        if ((x1 < x2 + width2) && (x1 + width1 > x2) && (y1 + height1 > y2) && (y1 < y2 + height2))
        {
            return true;
        }
        return false;
    }

    private void collideBallPaddle()
    {
        if (ball.y + Ball.HEIGHT >= paddle.y &&
                ball.x < paddle.x + paddle.WIDTH &&
                ball.x + Ball.WIDTH > paddle.x)
        {
            ball.y = (int)(paddle.y - Ball.HEIGHT - 2);
            ball.vy = -ball.vy;

            listener.collisionPaddle();
        }
    }
}
