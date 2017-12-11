package com.example.sosesahakian.gameengine.Maze;

/**
 * Created by sosesahakian on 08/11/2017.
 */

public class Bob
{
    public static final float WIDTH = 20;
    public static final float HEIGHT = 20;
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
