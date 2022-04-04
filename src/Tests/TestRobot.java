package Tests;

import Model.gamefield.*;
import Model.units.Key;
import org.junit.Assert;
import org.junit.Test;

public class TestRobot {

    //Робот шагает в свободную клетку
    @Test
    public void robotStepIntoFreeCell()
    {
        GameField _field = new GameField(3,3);
        _field.getCell(0, 0).putRobot(_field.robot());

        _field.robot().move(Direction.south());

        CellPosition expectedPos = new CellPosition( 1, 0 );
        CellPosition resultPos = _field.getCell(1, 0).getRobot().getPos();

        Assert.assertEquals(expectedPos, resultPos);
    }

    //Робот хочет шагнуть в клетку со следом
    @Test
    public void robotStepIntoFootprintCell()
    {
        GameField _field = new GameField(3,3);
        _field.getCell(0, 0).putRobot(_field.robot());
        ((Cell)_field.getCell(1,0)).setFootprint(_field.robot().getColor());

        _field.robot().move(Direction.south());

        CellPosition expectedPos = new CellPosition( 0, 0 );
        CellPosition resultPos = _field.getCell(0, 0).getRobot().getPos();

        Assert.assertEquals(expectedPos, resultPos);
    }

    //Робот шагает в клетку с ключем
    @Test
    public void robotStepIntoCellWithKey()
    {
        GameField _field = new GameField(3,3);
        _field.getCell(0, 0).putRobot(_field.robot());
        ((Cell)_field.getCell(1,0)).putKey(new Key());

        _field.robot().move(Direction.south());

        CellPosition expectedPos = new CellPosition( 1, 0 );
        CellPosition resultPos = _field.getCell(1, 0).getRobot().getPos();
        int countKeysOnField = 0;

        Assert.assertEquals(expectedPos, resultPos);
        Assert.assertEquals(countKeysOnField, Key.keys.size());
    }

    //Робот шагает в клетку с Целевым шестиугольнком
    @Test
    public void robotStepIntoTargetHexagonCell()
    {
        GameField _field = new GameField(3,3);
        _field.getCell(0, 0).putRobot(_field.robot());
        _field.setTargetHexagon(1,0);

        _field.robot().move(Direction.south());

        CellPosition expectedPos = new CellPosition( 1, 0 );
        CellPosition resultPos = _field.getCell(1, 0).getRobot().getPos();

        Assert.assertEquals(expectedPos, resultPos);
    }

    //Робот хочет шагнуть за пределы поля
    @Test
    public void robotStepOutsideField()
    {
        GameField _field = new GameField(1,1);
        _field.getCell(0, 0).putRobot(_field.robot());

        _field.robot().move(Direction.south());

        CellPosition expectedPos = new CellPosition( 0, 0 );
        CellPosition resultPos = _field.getCell(0, 0).getRobot().getPos();

        Assert.assertEquals(expectedPos, resultPos);
    }
}