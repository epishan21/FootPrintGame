package Model.gamefield;

import java.util.HashMap;
import java.util.Map;

import Model.units.Robot;

public abstract class AbstractCell {

    // ----------------------- Свойства --------------------------
    private CellPosition _pos;

    private Robot _robot;

    public CellPosition position() { return _pos; }

    //----------------------- Порождение --------------------------
    protected AbstractCell(CellPosition position) { _pos = position; }

    private final Map<Direction, AbstractCell> _neighbors = new HashMap<>();

    // ----------------------- Взаимодействие с соседями --------------------------
    public AbstractCell neighbor(Direction direct) {

        if(_neighbors.containsKey(direct)) {
            return _neighbors.get(direct);
        }

        return null;
    }

    public void setNeighbor(Direction direct, AbstractCell neighbor) {
        if(neighbor != this && !isNeighbor(neighbor)) {
            _neighbors.put(direct, neighbor);
            neighbor.setNeighbor(direct.opposite(), this);
        }
    }

    public boolean isNeighbor(AbstractCell other) {
        return _neighbors.containsValue(other);
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
