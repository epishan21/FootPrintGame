package Tests;

import Model.game.WaysChecker;
import Model.gamefield.*;
import Model.gamefield.cells.FootprintCell;
import Model.gamefield.cells.TargetHexagon;
import Model.gamefield.cells.UnitCell;
import org.junit.Assert;
import org.junit.Test;

public class TestWaysChecker {

    //Имеется путь от робота до целевого шестиугольника
    @Test
    public void WayFromRobotToTargethexagonExists()
    {
        WaysChecker waysChecker = new WaysChecker();
        GameField _field = new GameField(3,3);
        _field.linkCells();

        ((UnitCell)_field.getCell(0, 0)).putRobot(_field.robot());
        _field.setAnyCell(new TargetHexagon(new CellPosition(2,0)));

        boolean expectedWay = true;
        boolean resultWay = waysChecker.CheckWay(_field.robot().getPos(), _field.getPosTargetHexagon(), _field._cells);

        Assert.assertEquals(expectedWay, resultWay);
    }

    //Не имеется путь от робота до целевого шестиугольника
    @Test
    public void WayFromRobotToTargethexagonNotExists()
    {
        WaysChecker waysChecker = new WaysChecker();
        GameField _field = new GameField(3,3);
        _field.linkCells();

        ((UnitCell)_field.getCell(0, 0)).putRobot(_field.robot());
        _field.setAnyCell(new TargetHexagon(new CellPosition(2,0)));

        ((FootprintCell)_field.getCell(1,0)).setFootprint(_field.robot().getColor());
        ((FootprintCell)_field.getCell(1,1)).setFootprint(_field.robot().getColor());
        ((FootprintCell)_field.getCell(0,1)).setFootprint(_field.robot().getColor());

        boolean expectedWay = false;
        boolean resultWay = waysChecker.CheckWay(_field.robot().getPos(), _field.getPosTargetHexagon(), _field._cells);

        Assert.assertEquals(expectedWay, resultWay);
    }

    //Робот стоит на целевом шестиугольнике
    @Test
    public void RobotLocatedOnTargethexagon()
    {
        WaysChecker waysChecker = new WaysChecker();
        GameField _field = new GameField(3,3);
        _field.linkCells();

        _field.setAnyCell(new TargetHexagon(new CellPosition(1,0)));
        ((UnitCell)_field.getCell(1, 0)).putRobot(_field.robot());

        boolean expectedWay = true;
        boolean resultWay = waysChecker.CheckWay(_field.robot().getPos(), _field.getPosTargetHexagon(), _field._cells);

        Assert.assertEquals(expectedWay, resultWay);
    }
}
