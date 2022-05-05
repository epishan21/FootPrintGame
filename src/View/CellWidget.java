package View;

import Model.gamefield.cells.AbstractCell;


import javax.swing.*;
import java.awt.*;

public class CellWidget extends JPanel {

    private final AbstractCell cell;
    UnitWidget unitWidget = new UnitWidget();
    CellsPaint cellsPaint = new CellsPaint();

    public static final int CELL_SIZE = 65;
    public static final double CELL_WIDTH_MOD = 0.866025;

    private static final Color RECT_COLOR = Color.black;

    private static final int[] xHexagonPoints = new int[]{ 33, 61, 61, 33 ,4, 4, 33 };
    private static final int[] yHexagonPoints = new int[]{ 65, 49, 16, 0, 16, 49, 65 };


    public CellWidget(AbstractCell cell) {
        this.cell = cell;

        setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));

        setBackground(new Color(0, 0, 0, 0));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D gr2d = (Graphics2D) g;

        drawHexagon(gr2d, cellsPaint.getCellColor(cell));

        unitWidget.drawUnit(g,cell);
    }

    private void drawHexagon(Graphics2D gr2d, Color cellColor){
        gr2d.setPaint(cellColor);
        gr2d.fillPolygon(yHexagonPoints, xHexagonPoints, xHexagonPoints.length);

        gr2d.setStroke(new BasicStroke(2));
        gr2d.setPaint(RECT_COLOR);
        gr2d.drawPolygon(yHexagonPoints, xHexagonPoints, xHexagonPoints.length);
    }
}
