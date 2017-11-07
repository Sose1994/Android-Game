package com.example.sosesahakian.gameengine.CarScroller;

import android.graphics.Rect;

import com.example.sosesahakian.gameengine.Breakout.Block;
import com.example.sosesahakian.gameengine.GameEngine;

/**
 * Created by sosesahakian on 05/11/2017.
 */

public class World
{
    public static final float MIN_X = 0;
    public static final float MAX_X = 479;
    public static final float MIN_Y = 28;
    public static final float MAX_Y = 219 - 28;

    Car car = new Car();
    GameEngine gameEngine;
    CollisionListener collisionListener;

    boolean gameOver = false;
    int points = 0;
    int lives = 3;

    public World(GameEngine gameEngine, CollisionListener collisionListener)
    {
        this.gameEngine = gameEngine;
        this.collisionListener = collisionListener;
    }

    public void update(float deltaTime, float accelY)
    {
        //Move the car based on the phone accelerometer in y-axis
        car.y = (int)(car.y + accelY * deltaTime * 50);

        //Check if car touches top side road
        if (car.y < MIN_Y) car.y = (int)MIN_Y;

        //Check if car touches bottom side of the road
        if (car.y > MAX_Y) car.y = (int) MAX_Y - Car.HEIGHT;

        //Car moves with the touch ONLY for testing purposes
        if (gameEngine.isTouchDown(0))
        {
            if (gameEngine.getTouchX(0) > 100)
            {
                car.y = gameEngine.getTouchY(0) - Car.HEIGHT;
            }
        }


        //collideBallPaddle();
        //collideBallBlocks(deltaTime);
    }


    private boolean collideRects(float x, float y, float width, float height, float x2, float y2, float width2, float height2)
    {
        Rect rect1 = new Rect((int) x, (int) y, (int)x+(int)width, (int)y+(int)height);
        Rect rect2 = new Rect((int) x2, (int)y2, (int)x2+(int)width2, (int)y2+(int)height2);

        return rect1.intersect(rect2);

        /*
        //Teacher's implementation
        if (x < x2+width2 && x + width > x2 && y + y + height > y2 && y < y2 + height2)
        {
            return true;
        }

        return false;
        */
    }
}
