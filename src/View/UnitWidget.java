package View;

import Model.gamefield.cells.AbstractCell;
import Model.gamefield.cells.PassableCell;
import Model.gamefield.cells.TargetHexagon;
import Model.gamefield.cells.UnitCell;
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
        if(cell instanceof UnitCell) {
            robot = ((UnitCell) cell).getRobot();

            if (!cell.getClass().equals(TargetHexagon.class))
                key = ((PassableCell) cell).getKey();
            if (robot != null)
                return getImage("robot");
            else if (key != null) {
                return getImage("key");
            }
        }
        return null;
    }

    private Image getImage(String name){
        String fileName = "/img/" + name + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(fileName));
        return icon.getImage();
    }
}
