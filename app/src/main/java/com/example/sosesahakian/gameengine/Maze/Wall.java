package com.example.sosesahakian.gameengine.Maze;

import android.graphics.Bitmap;

/**
 * Created by sosesahakian on 08/11/2017.
 */

public class Wall
{
    public int HEIGHT;
    public int WIDTH;
    public int x;
    public int y;
    public Bitmap bitmap;

    public Wall(int height, int width, int x, int y)
    {
        this.HEIGHT = height;
        this.WIDTH = width;
        this.x = x;
        this.y = y;
    }


}
