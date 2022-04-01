package Model.game;

import Model.events.RobotActionEvent;
import Model.events.RobotActionListener;
import Model.gamefield.GameField;
import Model.gamefield.Builder;
import Model.gamefield.TargetHexagon;
import Model.units.Key;

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
        super();
        this.builder = builder;
        if (this.builder == null) {
            throw new IllegalArgumentException("Game can't work without Builder!");
        }
    }

    public GameField getField() { return _field; }

    public void start(){
        createLevel();
        _field = builder.getField();

        // "Следим" за роботом
        _field.robot().addRobotActionListener(new RobotObserver());
    }

    private void createLevel() {
        builder.run();
    }

    private class RobotObserver implements RobotActionListener {

        @Override
        public void robotMakedMove(RobotActionEvent e, Iterator<RobotActionListener> iter) {
            if(! waysChecker.CheckWay(_field.robot().getPos(), _field.getCells()))
                state = State.LOSE;
            if(_field.getCell(_field.robot().getPos()).getClass().equals(TargetHexagon.class) && Key.keys.isEmpty()) // Если робот наступил на целевой шестиугольник и все ключи собраны с поля
                state = State.WIN;
            isPlayerWin();
        }
    }

    public boolean isPlayerWin(){
        return state == State.WIN;
    }
}
