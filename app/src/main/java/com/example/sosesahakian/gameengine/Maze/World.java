package com.example.sosesahakian.gameengine.Maze;

import android.graphics.Bitmap;

import com.example.sosesahakian.gameengine.GameEngine;
import com.example.sosesahakian.gameengine.TouchEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sosesahakian on 08/11/2017.
 */

public class World
{
    public static final float MIN_X = 0;
    public static final float MAX_X = 319;
    public static final float MIN_Y = 0;
    public static final float MAX_Y = 479;
    Bob bob = new Bob();
    Key key = new Key();
    Wall wall;
    CollisionListener listener;
    GameEngine gameEngine;
    boolean gameOver;
    List<Wall> walls = new ArrayList<>();
    TouchEvent touchEvent;
    List<TouchEvent> touchEventList;

    public World(GameEngine gameEngine, CollisionListener listener)
    {
        this.gameEngine = gameEngine;
        this.listener = listener;
    }

    public void update(float deltatime)
    {
        touchEventList = gameEngine.getTouchEvents();
        if (touchEventList.size() > 0)
        {
            touchEvent = touchEventList.get(0);

            if (touchEvent.y < 50)       bob.direction = Bob.Direction.Up;
            else if (touchEvent.y > 420) bob.direction = Bob.Direction.Down;
            else if (touchEvent.x < 160) bob.direction = Bob.Direction.Left;
            else if (touchEvent.x > 160) bob.direction = Bob.Direction.Right;
        }

        switch (bob.direction)
        {
            case Up:
                bob.y -=bob.speed * deltatime;
                break;
            case Down:
                bob.y +=bob.speed * deltatime;
                break;
            case Left:
                bob.x -= bob.speed * deltatime;
                break;
            case Right:
                bob.x +=bob.speed * deltatime;
        }

        if (bob.x + bob.WIDTH > MAX_X)
        {
            bob.x = (int)(MAX_X - bob.WIDTH) + 20;
        }
        if (bob.x < MIN_X)
        {
            bob.x = (int)(MIN_X);
        }
        if (bob.y + bob.HEIGHT > MAX_Y)
        {
            bob.y = (int)(MAX_Y - bob.HEIGHT) - 260;
        }
        if (bob.y < MIN_Y)
        {
            bob.y = (int)(MIN_Y);
        }
    }

    private void generateWall()
    {

    }



}
