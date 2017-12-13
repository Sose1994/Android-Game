package com.example.sosesahakian.gameengine.Maze;

import android.graphics.Bitmap;

/**
 * Created by sosesahakian on 08/11/2017.
 */

public class Door
{
    public int WIDTH = 60;
    public int HEIGHT = 20;
    public Key.Color color;
    public int x, y;
    public Bitmap bitmap;

    public Door(int WIDTH, int HEIGHT, Key.Color color, int x, int y)
    {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.color = color;
        this.x = x;
        this.y = y;
    }
}
