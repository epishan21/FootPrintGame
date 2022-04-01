package Model.units;

import Model.events.RobotActionEvent;
import Model.events.RobotActionListener;
import Model.gamefield.*;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Robot {

    // ----------------------- Свойства --------------------------
   AbstractCell _cellOwner;

    private Color footPrint = Color.orange;

    public Color getColor() { return footPrint; }

    public CellPosition getPos()
    {
        return _cellOwner.position();
    }

    // --------------------------- Перемещение ------------------------------------
    private boolean canMoveTo(AbstractCell to) {
        return true;
    }

    public void move(Direction direct) {
    }

    // ---------------------- Закрашивание ячейки -----------------------------
    public void colorCell(AbstractCell pos) {
    }

    // ---------------------- Взаимодействие с ключем -----------------------------
    public void putKey(AbstractCell pos) {
    }

    // ---------------------- Взаимодействие с владельцем -----------------------------
    public void setOwner(AbstractCell cell) {
        _cellOwner = cell;
    }

    public void removeOwner() {
        _cellOwner = null;
    }

    // ---------------------- Порождает события -----------------------------
    private final List<RobotActionListener> _listeners = new ArrayList<>();


    // присоединяет слушателя
    public void addRobotActionListener(RobotActionListener l) {
        _listeners.add(l);
    }

    // отсоединяет слушателя
    public void removeRobotActionListener(RobotActionListener l) {
        _listeners.remove(l);
    }

    // оповещает слушателей о событии
    protected void fireRobotAction() {
        Iterator<RobotActionListener> iter = _listeners.iterator();

        while (iter.hasNext()) {
            iter.next().robotMakedMove(new RobotActionEvent(this), iter);
        }
    }
}
