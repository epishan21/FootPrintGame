package Model.game;

import Model.events.RobotActionEvent;
import Model.events.RobotActionListener;
import Model.gamefield.GameField;
import Model.gamefield.Builder;


import java.util.Iterator;

public class Game {

    private State state = State.PROCESSING;

    public State getState() {
        return state;
    }

    private Builder builder;
    private GameField _field;
    WaysChecker waysChecker = new WaysChecker();

    public Game(Builder builder) {

    }

    public GameField getField() { return _field; }

    public void start(){

    }

    private void createLevel() {
        builder.run();
    }

    private class RobotObserver implements RobotActionListener {

        @Override
        public void robotMakedMove(RobotActionEvent e, Iterator<RobotActionListener> iter) {

        }
    }

    public boolean isPlayerWin(){
        return state == State.WIN;
    }
}
