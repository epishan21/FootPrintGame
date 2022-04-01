package Model.gamefield;

import Model.units.Key;

import java.awt.*;


// Ячейка поля
public class Cell extends AbstractCell {

    // ----------------------- Порождение ----------------------------------------
    public Cell(CellPosition position) {
        super(position);
    }

    // ----------------------- Свойства --------------------------
    Key _key;

    private Color footPrint = new Color(136, 128, 170);

    public Color getFootprint() { return footPrint; }

    public void setFootprint(Color color){
        this.footPrint = color;
    }

    // ------------------------------- Владение ключем ----------------------------
    public Key getKey() {
        return _key;
    }

    public boolean putKey(Key key) {

        boolean ok = false;

        return ok;
    }

    public Key extractKey(){

        Key removedKey = _key;

        return removedKey;
    }

    public boolean isEmpty() {
        return _key == null;
    }
}
