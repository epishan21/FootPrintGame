package Model.gamefield.cells;

import Model.gamefield.CellPosition;
import Model.units.Key;


// Ячейка поля
public class PassableCell extends UnitCell {

    // ----------------------- Порождение ----------------------------------------
    public PassableCell(CellPosition position) { super(position); }

    // ----------------------- Свойства --------------------------
    Key _key;

    // ------------------------------- Владение ключем ----------------------------
    public Key getKey() { return _key; }

    public boolean putKey(Key key) {

        boolean ok = false;

        Key.keys.add(key);

        if(_key == null) {
            ok = true;
            key.setOwner(this);
            _key = key;
        }
        return ok;
    }

    public Key extractKey(){

        if( !isEmpty() ) {
            _key.removeOwner();
        }

        Key removedKey = _key;
        _key = null;
        return removedKey;
    }

    public boolean isEmpty() {
        return _key == null;
    }
}
