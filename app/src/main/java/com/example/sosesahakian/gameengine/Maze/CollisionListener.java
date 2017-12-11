package com.example.sosesahakian.gameengine.Maze;

/**
 * Created by sosesahakian on 08/11/2017.
 */

public interface CollisionListener
{
    public void collisionFood();
    public void collisionWall();
    public void collisionEdge();
}
