package Tests;

import Model.gamefield.GameField;
import org.junit.Assert;
import org.junit.Test;

public class TestAbstractCell {

    //Поместить робота в ячейку успешно
    @Test
    public void successPut()
    {
        GameField _field = new GameField(3,3);

        boolean resultPut = _field.getCell(0, 0).putRobot(_field.robot());

        boolean expectedPut = true;

        Assert.assertEquals(expectedPut, resultPut);
    }

    //Поместить робота в ячейку не успешно
    @Test
    public void failPut()
    {
        GameField _field = new GameField(3,3);

        _field.getCell(0, 0).putRobot(_field.robot());
        boolean resultPut = _field.getCell(0, 0).putRobot(_field.robot());

        boolean expectedPut = false;

        Assert.assertEquals(expectedPut, resultPut);
    }
}
