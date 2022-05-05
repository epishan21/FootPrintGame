package Model.game;

import Model.events.RobotActionEvent;
import Model.events.RobotActionListener;
import Model.gamefield.Builder;
import Model.gamefield.GameField;
import Model.gamefield.cells.TargetHexagon;
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
            // Если имеется путь до целевого шестиугольника
            if (waysChecker.checkWay(_field.robot().getPos(), _field.getPosTargetHexagon(), _field.getCells())){
                int countKey = 0;
                // Если имеется путь до каждого ключа на поле
                for(int i = 0; i < Key.keys.size(); i++) {
                    if (waysChecker.checkWay(_field.robot().getPos(), Key.keys.get(i).getOwner().position(), _field.getCells()))
                        countKey++;
                }
                if (Key.keys.size() != countKey)
                    state = State.LOSE;
            }
            else{
                state = State.LOSE;
            }
            // Если робот наступил на целевой шестиугольник и все ключи собраны с поля
            if(_field.getCell(_field.robot().getPos()).getClass().equals(TargetHexagon.class) && Key.keys.isEmpty())
                state = State.WIN;
            isPlayerWin();
        }
    }

    public boolean isPlayerWin(){
        return state == State.WIN;
    }
}
