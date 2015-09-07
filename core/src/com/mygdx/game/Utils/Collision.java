package com.mygdx.game.Utils;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Administrator on 2015/9/7.
 */
public class Collision {
    public static boolean isColliding(Rectangle one, Rectangle other) {
        return one.overlaps(other);
    }
}
