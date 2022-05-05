package Model.gamefield.cells;

import Model.events.RobotActionEvent;
import Model.events.RobotActionListener;
import Model.gamefield.CellPosition;
import Model.gamefield.GameField;
import Model.units.Robot;

import java.awt.*;
import java.util.Iterator;

public class ObliviousCell extends PassableCell {

    protected Color obliviousColor = new Color(200, 128, 128);
    protected Color footPrint = obliviousColor;

    public ObliviousCell(CellPosition position) {
        super(position);
    }

    public Color getFootprint() { return this.obliviousColor; }

    public void setFootprint(Color color) { this.obliviousColor = color; }

    int _originalStepCount;
    public int _stepCount;

    public ObliviousCell(CellPosition position, int stepCount, GameField field) {
        super(position);
        _originalStepCount = stepCount;
        _stepCount = _originalStepCount;
        // "Следим" за роботом
         field.robot().addRobotActionListener(new ObliviousCell.RobotObserver());
    }

    public boolean isCellPassable(Robot robot)
    {
        return this.getFootprint() != robot.getColor();
    }

    private class RobotObserver implements RobotActionListener {

        @Override
        public void robotMakedMove(RobotActionEvent e, Iterator<RobotActionListener> iter) {
            if (footPrint != obliviousColor) {
                if (_stepCount == 1) {
                    setFootprint(footPrint);
                    _stepCount = _originalStepCount;
                }else
                    _stepCount--;
            }
        }
    }
}
