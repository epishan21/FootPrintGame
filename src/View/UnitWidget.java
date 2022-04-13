package View;

import Model.gamefield.AbstractCell;
import Model.gamefield.Cell;
import Model.gamefield.TargetHexagon;
import Model.units.Key;
import Model.units.Robot;

import javax.swing.*;
import java.awt.*;

public class UnitWidget {

    Key key = null;
    Robot robot;
    CellWidget cellWidget;

    public void drawUnit(Graphics g ,AbstractCell cell)
    {
        g.drawImage(getUnit(cell), 13, 13, 40, 40, cellWidget);
    }

    private Image getUnit(AbstractCell cell){
        robot = cell.getRobot();

        if(!cell.getClass().equals(TargetHexagon.class))
            key = ((Cell)cell).getKey();
        if (robot != null)
            return getImage("robot");
        else if (key != null) {
            return getImage("key");
        }
        return null;
    }

    private Image getImage(String name){
        String fileName = "/img/" + name + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(fileName));
        return icon.getImage();
    }
}
