package com.example.sosesahakian.gameengine.Maze;

import android.graphics.Bitmap;
import android.graphics.Rect;

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

    CollisionListener listener;
    GameEngine gameEngine;
    boolean gameOver;
    List<Wall> walls = new ArrayList<>();
    TouchEvent touchEvent;
    List<TouchEvent> touchEventList;
    List<Key> keys = new ArrayList<>();
    List<Door> doors = new ArrayList<>();

    public World(GameEngine gameEngine, CollisionListener listener)
    {
        this.gameEngine = gameEngine;
        this.listener = listener;

        walls.add(new Wall(20, 20, 120, 160));
        walls.add(new Wall(20, 200, 50, 240));
        walls.add(new Wall(20, 200, 210, 60));
        walls.add(new Wall(20, 200, 120, 360));
        walls.add(new Wall(20, 20, 120, 160));
        walls.add(new Wall(240, 20, 140, 0));
        walls.add(new Wall(80, 20, 200, 420));
        walls.add(new Wall(80, 20, 50, 160));
        walls.add(new Wall(20, 80, 0, 80));

        doors.add(new Door(20, 50, Key.Color.Blue, 200, 380));
        doors.add(new Door(50, 20, Key.Color.Green, 70, 160));
        doors.add(new Door(50, 20, Key.Color.Red, 160, 60));

        keys.add(new Key(260, 20, Key.Color.Blue));
        keys.add(new Key(100, 200, Key.Color.Red));
        keys.add(new Key(200, 200, Key.Color.Green));

    }

    public void update(float deltatime)
    {
        touchEventList = gameEngine.getTouchEvents();
        if (gameEngine.isTouchDown(0))
        {
            if (gameEngine.getTouchY(0) < 50)       bob.direction = Bob.Direction.Up;
            else if (gameEngine.getTouchY(0) > 420) bob.direction = Bob.Direction.Down;
            else if (gameEngine.getTouchX(0) < 160) bob.direction = Bob.Direction.Left;
            else if (gameEngine.getTouchX(0) > 160) bob.direction = Bob.Direction.Right;
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

        collideBobWall(deltatime);
        collideBobKey();

        if (bob.x + bob.WIDTH > MAX_X)
        {
            bob.x = (int)(MAX_X - bob.WIDTH);
        }
        if (bob.x < MIN_X)
        {
            bob.x = (int)(MIN_X);
        }
        if (bob.y + bob.HEIGHT > MAX_Y)
        {
            bob.y = (int)(MAX_Y - bob.HEIGHT);
        }
        if (bob.y < MIN_Y)
        {
            bob.y = (int)(MIN_Y);
        }
    }

    private boolean collideRects(float x, float y, float width, float height, float x2, float y2, float width2, float height2)
    {
        Rect rect1 = new Rect((int) x, (int)y, (int)x+(int)width, (int)y+(int)height);
        Rect rect2 = new Rect((int) x2, (int)y2, (int)x2+(int)width2, (int)y2+(int)height2);

        return rect1.intersect(rect2);
    }

    public void collideBobWall(float deltatime)
    {
        for (Wall w: walls)
        {
            if (collideRects(bob.x, bob.y, bob.WIDTH, bob.HEIGHT, w.x, w.y, w.WIDTH, w.HEIGHT))
            {
                switch (bob.direction)
                {
                    case Up:
                        bob.y +=bob.speed * deltatime + 1;
                        break;
                    case Down:
                        bob.y -=bob.speed * deltatime - 1;
                        break;
                    case Left:
                        bob.x += bob.speed * deltatime + 1;
                        break;
                    case Right:
                        bob.x -=bob.speed * deltatime - 1;
                }
                return;
            }
        }

        for (Door d: doors)
        {
            if (collideRects(bob.x, bob.y, bob.WIDTH, bob.HEIGHT, d.x, d.y, d.WIDTH, d.HEIGHT))
            {
                switch (bob.direction)
                {
                    case Up:
                        bob.y +=bob.speed * deltatime + 1;
                        break;
                    case Down:
                        bob.y -=bob.speed * deltatime - 1;
                        break;
                    case Left:
                        bob.x += bob.speed * deltatime + 1;
                        break;
                    case Right:
                        bob.x -=bob.speed * deltatime - 1;
                }
                return;
            }

        }
    }

    public void collideBobKey()
    {
        for (int i = 0; i < keys.size(); i++)
        {
            Key k = keys.get(i);
            if (collideRects(bob.x, bob.y, bob.WIDTH, bob.HEIGHT, k.x, k.y, k.WIDTH, k.HEIGHT))
            {
                keys.remove(i);

                for (int j = 0; j < doors.size(); j++ )
                {
                    Door d = doors.get(j);
                    if (d.color == k.color)
                    {
                        doors.remove(j);
                    }
                }
            }
        }

    }
}
