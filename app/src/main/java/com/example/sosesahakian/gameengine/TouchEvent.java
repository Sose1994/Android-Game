package com.example.sosesahakian.gameengine;

/**
 * Created by sosesahakian on 02/10/2017.
 */

public class TouchEvent
{
    public enum TouchEventType
    {
        Down,
        Up,
        Dragged
    }

    public TouchEventType type; //the type of event
    public int x;               //the x-coordinate of the event
    public int y;               //the y-coordinate of the event
    public int pointer;         //the pointer id(from android system)
}