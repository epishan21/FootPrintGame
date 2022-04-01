package Tests;

import Model.gamefield.GameField;
import Model.gamefield.TargetHexagon;
import org.junit.Assert;
import org.junit.Test;

public class TestGameField {

    //Установить Целевой шестиугольник успешно
    @Test
    public void successSet()
    {
        GameField _field = new GameField(3,3);

        _field.setTargetHexagon(1,1);

        boolean resultPut = _field.getCell(1,1).getClass().equals(TargetHexagon.class);

        boolean expectedPut = true;

        Assert.assertEquals(expectedPut, resultPut);
    }

    //Установить Целевой шестиугольник успешно
    @Test
    public void failSet()
    {
        GameField _field = new GameField(3,3);

        _field.setTargetHexagon(1,1);
        _field.setTargetHexagon(2,2);

        boolean resultPut = _field.getCell(2,2).getClass().equals(TargetHexagon.class);

        boolean expectedPut = false;

        Assert.assertEquals(expectedPut, resultPut);
    }
}
