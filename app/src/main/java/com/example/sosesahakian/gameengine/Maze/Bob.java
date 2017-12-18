package com.example.sosesahakian.gameengine.Maze;

/**
 * Created by sosesahakian on 08/11/2017.
 */

public class Bob
{
    public final float WIDTH = 40;
    public final float HEIGHT = 40;
    public int x = 1;
    public int y = 1;
    public int speed = 150;
    public Direction direction = Direction.Right;



    enum Direction
    {
        Up,
        Down,
        Left,
        Right
    }
}
