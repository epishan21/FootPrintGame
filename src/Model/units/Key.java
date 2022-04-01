package Model.units;

import Model.gamefield.AbstractCell;
import java.util.ArrayList;

public class Key {

    // ----------------------- Свойства --------------------------
    public static ArrayList<Key> keys = new ArrayList<Key>();

    AbstractCell _cellOwner;

    public void setOwner(AbstractCell cell) {
        _cellOwner = cell;
    }

    public void removeOwner() {
        _cellOwner = null;
    }
}
