package Model.units;

import Model.gamefield.cells.AbstractCell;
import java.util.ArrayList;

public class Key {

    // ----------------------- Свойства --------------------------
    public static ArrayList<Key> keys = new ArrayList<Key>();

    AbstractCell _cellOwner;

    public void setOwner(AbstractCell cell) {
        _cellOwner = cell;
    }

    public AbstractCell getOwner() { return _cellOwner ;}

    public void removeOwner() {
        _cellOwner = null;
    }
}
