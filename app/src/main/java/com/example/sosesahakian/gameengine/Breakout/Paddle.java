package com.example.sosesahakian.gameengine.Breakout;

/**
 * Created by sosesahakian on 17/10/2017.
 */

public class Paddle
{
    public static final float WIDTH = 56;
    public static final float HEIGHT = 11;
    public float x = 160 - WIDTH/2;
    public float y = World.MAX_Y - 20;
}
