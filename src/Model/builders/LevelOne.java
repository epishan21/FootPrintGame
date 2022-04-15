package Model.builders;

import Model.gamefield.Builder;
import Model.gamefield.CellPosition;
import Model.gamefield.GameField;
import Model.gamefield.cells.*;
import Model.units.Key;

public class LevelOne extends Builder {

    @Override
    protected void seedRobot() {
        ((UnitCell) _field.getCell(0, 0)).putRobot(_field.robot());
    }

    @Override
    protected void createField() {
        setField(new GameField(10, 10));
    }

    @Override
    protected void seedAnyCell() {
        _field.setAnyCell(new BlockedCell(new CellPosition(1, 1)));
        _field.setAnyCell(new BlockedCell(new CellPosition(0, 3)));
        _field.setAnyCell(new BlockedCell(new CellPosition(5, 6)));
        _field.setAnyCell(new PassableCell(new CellPosition(5, 5)));
        _field.setAnyCell(new PassableCell(new CellPosition(4, 5)));
        _field.setAnyCell(new PassableCell(new CellPosition(0, 2)));
        _field.setAnyCell(new TargetHexagon(new CellPosition(0, 1)));
        _field.setAnyCell(new TargetHexagon(new CellPosition(0, 1)));
        _field.setAnyCell(new ObliviousCell(new CellPosition(2, 2), 5, _field));

    }

    @Override
    protected void seedKey() {
        //((PassableCell) _field.getCell(2, 2)).putKey(_field.getCell(2, 2) instanceof PassableCell ? new Key() : null );
        ((PassableCell) _field.getCell(5, 5)).putKey(new Key());
        ((PassableCell) _field.getCell(3, 3)).putKey(new Key());
        ((PassableCell) _field.getCell(5, 1)).putKey(new Key());
        ((PassableCell) _field.getCell(2, 5)).putKey(new Key());
        ((PassableCell) _field.getCell(7, 3)).putKey(new Key());
    }
}