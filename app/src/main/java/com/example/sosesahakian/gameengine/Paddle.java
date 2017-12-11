package com.example.sosesahakian.gameengine;

import com.example.sosesahakian.gameengine.Breakout.World;

/**
 * Created by sosesahakian on 12/11/2017.
 */

public class Paddle
{
    public static final float WIDTH = 56;
    public static final float HEIGHT = 11;
    public float x = 169 - WIDTH/2;
    public float y = World.MAX_Y - 40 - HEIGHT/2;
}