package com.mygdx.game.Utils;

/**
 * Created by Administrator on 2015/9/8.
 */
public class Log {
    public static void show(String string) {
        System.out.println(string);
    }

    public static void show(String string, int... arg) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < arg.length; i++) {
            builder.append(" " + arg);
        }
        System.out.println(string + " " + builder.toString());
    }
    public static void show(String string, float... arg) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < arg.length; i++) {
            builder.append(" " + arg[i]);
        }
        System.out.println(string + " " + builder.toString());
    }
}
