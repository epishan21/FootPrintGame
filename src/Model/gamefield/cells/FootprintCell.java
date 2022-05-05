package Model.gamefield.cells;

import Model.gamefield.CellPosition;
import Model.units.Robot;

import java.awt.*;

public class FootprintCell extends ObliviousCell {
    public FootprintCell(CellPosition position) {
        super(position);
    }

    protected Color backgroundColor = new Color(136, 128, 170);

    public Color getFootprint() { return backgroundColor; }

    public void setFootprint(Color color) { this.backgroundColor = color; }

}
