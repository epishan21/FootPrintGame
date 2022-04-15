package Model.gamefield.cells;

import Model.gamefield.CellPosition;

import java.awt.*;

public class FootprintCell extends PassableCell {
    public FootprintCell(CellPosition position) {
        super(position);
    }

    protected Color backgroundColor = new Color(136, 128, 170);
    protected Color footPrint = backgroundColor;

    public Color getFootprint() { return footPrint; }

    public void setFootprint(Color color) { this.footPrint = color; }

}
