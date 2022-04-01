package Model.builders;

import Model.gamefield.Cell;
import Model.gamefield.GameField;
import Model.gamefield.Builder;
import Model.units.Key;

public class LevelOne extends Builder {

    @Override
    protected void seedRobot() {
        _field.getCell(0, 0).putRobot(_field.robot());
    }

    @Override
    protected void createField() {
        setField(new GameField(10, 10));
    }

    @Override
    protected void seedExit() {
        _field.setTargetHexagon(0,1);
        _field.setTargetHexagon(3,4); //для проверки, он не создастся
    }

    @Override
    protected void seedKey() {
        ((Cell)_field.getCell(2,2)).putKey(new Key());
        ((Cell)_field.getCell(3,3)).putKey(new Key());
        ((Cell)_field.getCell(5,7)).putKey(new Key());
        ((Cell)_field.getCell(9,9)).putKey(new Key());
        ((Cell)_field.getCell(7,7)).putKey(new Key());
        ((Cell)_field.getCell(2,5)).putKey(new Key());
        ((Cell)_field.getCell(7,5)).putKey(new Key());
    }


}
