package com.example.sosesahakian.gameengine.Breakout;

/**
 * Created by sosesahakian on 10/10/2017.
 */

//So here we have the "description" of the ball
//So we have the height and width of the ball
//Then we have the position of the ball (x, y)
//And then we have velocity, which is the speed of the ball in a direction

public class Ball
{
    public static final float WIDTH  = 15;
    public static final float HEIGHT = 15;
    public int x = 160;
    public int y = 240;
    public int vx = 150;
    public int vy = -150;
}
