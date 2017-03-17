package com.anwesome.games.hamburgbutton;

/**
 * Created by anweshmishra on 18/03/17.
 */
public interface IconAnimation {
    void execute(int dir);
    boolean isDone();
    void setEdgeConditionOnComplete();
}
