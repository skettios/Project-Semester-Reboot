package com.skettios.lpreboot.state;

import java.util.Stack;

public class StateEngine
{
    private Stack<State> stateStack = new Stack<State>();

    public void pushState(State state)
    {
        state.scene.initialize();
        stateStack.push(state).onPush();
    }

    public State popState()
    {
        State ret = stateStack.pop();
        ret.clearScene();
        ret.onPop();
        return ret;
    }

    public State peekState()
    {
        return stateStack.peek();
    }

    public void update(float deltaTime)
    {
        for (int i = stateStack.size() - 1; i >= 0; i--)
        {
            if (!stateStack.get(i).isPaused())
                stateStack.get(i).update(deltaTime);
        }
    }
}
