package Tests;

import Model.game.WaysChecker;
import Model.gamefield.*;
import Model.gamefield.cells.*;
import org.junit.Assert;
import org.junit.Test;

public class TestWaysChecker {

    //Имеется путь от робота до целевого шестиугольника
    @Test
    public void WayFromRobotToTargethexagonExists()
    {
        WaysChecker waysChecker = new WaysChecker();

        GameField _field = new GameField(3,3);
        _field.setAnyCell(new TargetHexagon(new CellPosition(2,0)));
        _field.linkCells();

        ((UnitCell)_field.getCell(0, 0)).putRobot(_field.robot());

        boolean expectedWay = true;
        boolean resultWay = waysChecker.checkWay(_field.robot().getPos(), _field.getPosTargetHexagon(), _field._cells);

        Assert.assertEquals(expectedWay, resultWay);
    }

    //Робот стоит на целевом шестиугольнике
    @Test
    public void RobotLocatedOnTargethexagon()
    {
        WaysChecker waysChecker = new WaysChecker();

        GameField _field = new GameField(3,3);
        _field.setAnyCell(new TargetHexagon(new CellPosition(1,0)));
        _field.linkCells();

        ((UnitCell)_field.getCell(1, 0)).putRobot(_field.robot());

        boolean expectedWay = true;
        boolean resultWay = waysChecker.checkWay(_field.robot().getPos(), _field.getPosTargetHexagon(), _field._cells);

        Assert.assertEquals(expectedWay, resultWay);
    }

    //Не имеется путь от робота до целевого шестиугольника(робот огражден следами)
    @Test
    public void WayFromRobotToTargethexagonNotExists()
    {
        WaysChecker waysChecker = new WaysChecker();

        GameField _field = new GameField(3,3);
        _field.setAnyCell(new TargetHexagon(new CellPosition(2,0)));
        _field.linkCells();

        ((UnitCell)_field.getCell(0, 0)).putRobot(_field.robot());
        ((FootprintCell)_field.getCell(1,0)).setFootprint(_field.robot().getColor());
        ((FootprintCell)_field.getCell(1,1)).setFootprint(_field.robot().getColor());
        ((FootprintCell)_field.getCell(0,1)).setFootprint(_field.robot().getColor());

        boolean expectedWay = false;
        boolean resultWay = waysChecker.checkWay(_field.robot().getPos(), _field.getPosTargetHexagon(), _field._cells);

        Assert.assertEquals(expectedWay, resultWay);
    }

    //Не имеется путь от робота до целевого шестиугольника(робот огражден совсем непроходимыми шестиугольниками)
    @Test
    public void WayFromRobotToTargethexagonNotExistsByBlockedCells()
    {
        WaysChecker waysChecker = new WaysChecker();
        GameField _field = new GameField(3,3);

        ((UnitCell)_field.getCell(0, 0)).putRobot(_field.robot());
        _field.setAnyCell(new TargetHexagon(new CellPosition(2,0)));

        _field.setAnyCell(new BlockedCell(new CellPosition(1,0)));
        _field.setAnyCell(new BlockedCell(new CellPosition(1,1)));
        _field.setAnyCell(new BlockedCell(new CellPosition(0,1)));

        _field.linkCells();

        boolean expectedWay = false;
        boolean resultWay = waysChecker.checkWay(_field.robot().getPos(), _field.getPosTargetHexagon(), _field._cells);

        Assert.assertEquals(expectedWay, resultWay);
    }

    //Имеется путь от робота до целевого шестиугольника(робот огражден всегда проходимыми шестиугольниками)
    @Test
    public void WayFromRobotToTargethexagonExistsByAlwaysPassableCell()
    {
        WaysChecker waysChecker = new WaysChecker();
        GameField _field = new GameField(3,3);

        ((UnitCell)_field.getCell(0, 0)).putRobot(_field.robot());
        _field.setAnyCell(new TargetHexagon(new CellPosition(2,0)));

        _field.setAnyCell(new AlwaysPassableCell(new CellPosition(1,0)));
        _field.setAnyCell(new AlwaysPassableCell(new CellPosition(1,1)));
        _field.setAnyCell(new AlwaysPassableCell(new CellPosition(0,1)));

        _field.linkCells();

        boolean expectedWay = true;
        boolean resultWay = waysChecker.checkWay(_field.robot().getPos(), _field.getPosTargetHexagon(), _field._cells);

        Assert.assertEquals(expectedWay, resultWay);
    }

    //Имеется путь от робота до целевого шестиугольника(робот огражден забывающими шестиугольниками)
    @Test
    public void WayFromRobotToTargethexagonExistsByObliviousCell()
    {
        WaysChecker waysChecker = new WaysChecker();
        GameField _field = new GameField(3,3);

        ((UnitCell)_field.getCell(0, 0)).putRobot(_field.robot());
        _field.setAnyCell(new TargetHexagon(new CellPosition(2,0)));

        _field.setAnyCell(new ObliviousCell(new CellPosition(1, 0), 3, _field));
        _field.setAnyCell(new ObliviousCell(new CellPosition(1, 1), 3, _field));
        _field.setAnyCell(new ObliviousCell(new CellPosition(0, 1), 3, _field));

        _field.linkCells();

        boolean expectedWay = true;
        boolean resultWay = waysChecker.checkWay(_field.robot().getPos(), _field.getPosTargetHexagon(), _field._cells);

        Assert.assertEquals(expectedWay, resultWay);
    }
    //Выход огражднен забывающими шестиугольниками
}
