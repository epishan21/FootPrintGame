package View;

import Model.gamefield.cells.*;

import java.awt.*;

public class CellsPaint {

    private static final Color BACKGROUND_COLOR_CELL = new Color(136, 128, 194);
    private static final Color BACKGROUND_COLOR_TARGETHEXAGON = Color.green;
    private static final Color BACKGROUND_COLOR_BLOCKEDCELL = Color.gray;
    private static final Color BACKGROUND_COLOR_ALWAYSPASSABLECELL = Color.white;

    public Color getCellColor(AbstractCell cell){
        Color color;
        if(cell.getClass().equals(FootprintCell.class))
            color = ((FootprintCell)cell).getFootprint();
        else if(cell.getClass().equals(ObliviousCell.class))
            color = ((ObliviousCell)cell).getFootprint();
        else if(cell instanceof TargetHexagon)
            color = BACKGROUND_COLOR_TARGETHEXAGON;
        else if(cell instanceof BlockedCell)
            color = BACKGROUND_COLOR_BLOCKEDCELL;
        else if(cell.getClass().equals(AlwaysPassableCell.class))
            color = BACKGROUND_COLOR_ALWAYSPASSABLECELL;
        else
            color = BACKGROUND_COLOR_CELL;

        return color;
    }
}
