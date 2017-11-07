package com.example.sosesahakian.gameengine;

/**
 * Created by sosesahakian on 02/10/2017.
 */

public class TouchEventPool extends Pool<TouchEvent>
{
    @Override
    protected TouchEvent newItem()
    {
        return new TouchEvent();
    }
}