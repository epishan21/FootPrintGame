package Model.gamefield.cells;

import java.util.HashMap;
import java.util.Map;

import Model.gamefield.CellPosition;
import Model.gamefield.Direction;
import Model.units.Robot;

public abstract class AbstractCell {

    // ----------------------- Свойства --------------------------
    private CellPosition _pos;

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

}
