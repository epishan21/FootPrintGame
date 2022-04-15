package Model.units;

import Model.events.RobotActionEvent;
import Model.events.RobotActionListener;
import Model.gamefield.*;
import Model.gamefield.cells.*;

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
          return to.getClass().equals(TargetHexagon.class) || to.getClass().equals(PassableCell.class) || to instanceof UnitCell && ((FootprintCell)to).getFootprint() != this.footPrint;
    }

    public void move(Direction direct) {

        AbstractCell pos = _cellOwner;

        AbstractCell newPos = pos.neighbor(direct);
        if (newPos == null) {
            return;
        }

        if (canMoveTo(newPos)) {
            if (!((UnitCell)newPos).isEmpty() && ((PassableCell)newPos).getKey() != null) {
                //подобрать ключ
                this.putKey(newPos);
            }
            //шагнуть
            ((UnitCell)pos).extractRobot();
            ((UnitCell)newPos).putRobot(this);
            if(!(pos.getClass().equals(TargetHexagon.class)) && !(pos.getClass().equals(PassableCell.class)))
                colorCell(pos); // установить цвет ячейки равным цвету робота
        }

        // Генерируем событие
        fireRobotAction();
    }

    // ---------------------- Закрашивание ячейки -----------------------------
    public void colorCell(AbstractCell pos)
    {
        ((FootprintCell)pos).setFootprint(this.footPrint);
    }

    // ---------------------- Взаимодействие с ключем -----------------------------
    public void putKey(AbstractCell pos)
    {
        Key.keys.remove(((PassableCell)pos).getKey());
        ((PassableCell)pos).extractKey();
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
