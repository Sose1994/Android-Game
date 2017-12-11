package com.example.sosesahakian.gameengine.Breakout;

/**
 * Created by sosesahakian on 17/10/2017.
 */

//Here we have the description of a block
//We have the height and width of a block
//We have the position of a block (x, y)
//And lastly we have a block type (because there are different colors)
//We have a constructor so we can create more blocks with a certain type and a position

public class Block
{
    public static final float WIDTH = 40;
    public static final float HEIGHT = 18;
    public float x;
    public float y;
    public int type;

    public Block(float x, float y, int type)
    {
        this.x = x;
        this.y = y;
        this.type = type;
    }
}
