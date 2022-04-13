package View;

import Model.gamefield.AbstractCell;
import Model.gamefield.Cell;
import Model.gamefield.TargetHexagon;

import java.awt.*;

public class CellsPaint {

    private static final Color BACKGROUND_COLOR_CELL = new Color(136, 128, 194);
    private static final Color BACKGROUND_COLOR_TARGETHEXAGON = Color.green;

    public Color getCellColor(AbstractCell cell){
        Color color;
        if(cell instanceof Cell)
            color = ((Cell)cell).getFootprint();
        else if(cell instanceof TargetHexagon)
            color = BACKGROUND_COLOR_TARGETHEXAGON;
        else
            color = BACKGROUND_COLOR_CELL;

        return color;
    }
}
