package Model.gamefield.cells;

import Model.gamefield.CellPosition;
import Model.units.Robot;

public class UnitCell extends AbstractCell{

    private Robot _robot;

    protected UnitCell(CellPosition position) {
        super(position);
    }

    // ----------------------- Взаимодействие с роботом --------------------------
    public Robot getRobot() {
        return _robot;
    }

    public boolean putRobot(Robot robot) {
        boolean ok = false;

        if(_robot == null) {
            ok = true;
            robot.setOwner(this);
            _robot = robot;
        }
        return ok;
    }

    public Robot extractRobot(){

        if( !isEmpty() ) {
            _robot.removeOwner();
        }

        Robot removedRobot = _robot;
        _robot = null;
        return removedRobot;
    }

    public boolean isEmpty() {
        return _robot == null;
    }
}
