package com.example.sosesahakian.gameengine.Breakout;

/**
 * Created by sosesahakian on 24/10/2017.
 */

//We have an interface which

public interface CollisionListener
{
    public void collisionWall();
    public void collisionPaddle();
    public void collisionBlock();
    public void gameOver();
}
