package com.example.sosesahakian.gameengine.CarScroller;

/**
 * Created by sosesahakian on 04/11/2017.
 */

public class Car
{
    public static final int WIDTH = 54;
    public static final int HEIGHT = 34;
    public int x = 0;
    public int y = 0;

    public Car()
    {
        x = 20;
        y = 160 - (HEIGHT / 2);
    }
}
