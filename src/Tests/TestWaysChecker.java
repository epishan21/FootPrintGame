package Tests;

import Model.game.WaysChecker;
import Model.gamefield.*;
import org.junit.Assert;
import org.junit.Test;

public class TestWaysChecker {

    //Имеется путь от робота до целевого шестиугольника
    @Test
    public void WayFromRobotToTargethexagonExists()
    {
        WaysChecker waysChecker = new WaysChecker();
        GameField _field = new GameField(3,3);

        _field.getCell(0, 0).putRobot(_field.robot());
        _field.setTargetHexagon(2,0);

        boolean expectedWay = true;
        boolean resultWay = waysChecker.CheckWay(_field.robot().getPos(), _field._cells);

        Assert.assertEquals(expectedWay, resultWay);
    }

    //Не имеется путь от робота до целевого шестиугольника
    @Test
    public void WayFromRobotToTargethexagonNotExists()
    {
        WaysChecker waysChecker = new WaysChecker();
        GameField _field = new GameField(3,3);

        _field.getCell(0, 0).putRobot(_field.robot());
        _field.setTargetHexagon(2,0);

        ((Cell)_field.getCell(1,0)).setFootprint(_field.robot().getColor());
        ((Cell)_field.getCell(1,1)).setFootprint(_field.robot().getColor());
        ((Cell)_field.getCell(0,1)).setFootprint(_field.robot().getColor());

        boolean expectedWay = false;
        boolean resultWay = waysChecker.CheckWay(_field.robot().getPos(), _field._cells);

        Assert.assertEquals(expectedWay, resultWay);
    }

    //Робот стоит на целевом шестиугольнике
    @Test
    public void RobotLocatedOnTargethexagon()
    {
        WaysChecker waysChecker = new WaysChecker();
        GameField _field = new GameField(3,3);

        _field.setTargetHexagon(1,0);
        _field.getCell(1, 0).putRobot(_field.robot());

        boolean expectedWay = true;
        boolean resultWay = waysChecker.CheckWay(_field.robot().getPos(), _field._cells);

        Assert.assertEquals(expectedWay, resultWay);
    }
}
