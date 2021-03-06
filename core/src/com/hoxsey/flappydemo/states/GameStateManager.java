package com.hoxsey.flappydemo.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by Hoxsey on 8/19/2016.
 */
public class GameStateManager {
    private Stack<State> states;
    public SpriteBatch batch;

    public GameStateManager(SpriteBatch sp)   {
        states = new Stack<State>();
        batch = sp;
    }
    public void push(State state)   {
        states.push(state);
    }
    public void pop()   {
        states.pop().dispose();
    }
    public void set(State state)    {
        states.pop().dispose();
        states.push(state);
    }

    public void update(float dt)    {
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb)    {
        states.peek().render(sb);
    }
}
