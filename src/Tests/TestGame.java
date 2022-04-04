package Tests;

import Model.game.Game;
import Model.game.State;
import Model.gamefield.Builder;
import Model.gamefield.Cell;
import Model.gamefield.Direction;
import Model.gamefield.GameField;
import Model.units.Key;
import org.junit.Assert;
import org.junit.Test;

public class TestGame {

    //Игра выиграна
    @Test
    public void StateWin()
    {
        Builder level = new Builder() {
            @Override
            protected void seedRobot() {
                _field.getCell(0, 0).putRobot(_field.robot());
            }

            @Override
            protected void createField() {
                setField(new GameField(3, 3));
            }

            @Override
            protected void seedExit() {
                _field.setTargetHexagon(2,0);
            }

            @Override
            protected void seedKey() {
                ((Cell)_field.getCell(1,0)).putKey(new Key());
            }
        };

        Game _game = new Game(level);
        _game.start();
        GameField _field = _game.getField();

        _field.robot().move(Direction.south());
        _field.robot().move(Direction.south());

        State resultGame = _game.getState();

        State expectedGame = State.WIN;

        Assert.assertEquals(expectedGame, resultGame);
    }

    // Игра проиграна
    @Test
    public void StateLose()
    {
        Builder level = new Builder() {
            @Override
            protected void seedRobot() {
                _field.getCell(0, 0).putRobot(_field.robot());
            }

            @Override
            protected void createField() {
                setField(new GameField(4, 4));
            }

            @Override
            protected void seedExit() {
                _field.setTargetHexagon(3,0);
            }

            @Override
            protected void seedKey() {
            }
        };

        Game _game = new Game(level);
        _game.start();
        GameField _field = _game.getField();

        ((Cell)_field.getCell(2,0)).setFootprint(_field.robot().getColor());
        ((Cell)_field.getCell(3,1)).setFootprint(_field.robot().getColor());

        _field.robot().move(Direction.south());

        State resultGame = _game.getState();

        State expectedGame = State.LOSE;

        Assert.assertEquals(expectedGame, resultGame);
    }

    //Игра в процессе
    @Test
    public void StateProcessing()
    {
        Builder level = new Builder() {
            @Override
            protected void seedRobot() {
                _field.getCell(0, 0).putRobot(_field.robot());
            }

            @Override
            protected void createField() {
                setField(new GameField(3, 3));
            }

            @Override
            protected void seedExit() {
                _field.setTargetHexagon(2,0);
            }

            @Override
            protected void seedKey() {
                ((Cell)_field.getCell(1,0)).putKey(new Key());
            }
        };

        Game _game = new Game(level);
        _game.start();
        GameField _field = _game.getField();

        _field.robot().move(Direction.south());

        State resultGame = _game.getState();

        State expectedGame = State.PROCESSING;

        Assert.assertEquals(expectedGame, resultGame);
    }
}
