package com.example.sosesahakian.gameengine.Maze;


import android.graphics.Bitmap;

/**
 * Created by sosesahakian on 08/11/2017.
 */

public class Key
{
    public static final float width = 25;
    public static final float height = 25;
    public int x;
    public int y;
    public Color color;
    public Bitmap bitmap;

    public Key(int x, int y, Color color)
    {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public enum Color
    {
        Red,
        Blue,
        Green
    }
}
