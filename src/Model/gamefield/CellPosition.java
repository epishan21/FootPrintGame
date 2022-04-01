package Model.gamefield;

//  Позиция ячейки
public class CellPosition {

    // ----------------------- Свойства --------------------------
    private final int _row = 0;
    private final int _column = 0;

    public int row() { return _row; }

    public int column() { return _column; }

    private void validate(int row, int col) {
        if(row < 0 || col < 0) {
            throw new IllegalArgumentException();
        }
    }

    // ----------------------- Порождение --------------------------
    public CellPosition(int row, int col) {

    }

    // ------------------ Сравнение позиций ----------------
    @Override
    public boolean equals(Object other){
        return false;
    }

    // --------------------------------------------------
    @Override
    public int hashCode() {
        return 0;
    }



}
