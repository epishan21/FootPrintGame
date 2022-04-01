package Model.gamefield;

//  Позиция ячейки
public class CellPosition {

    // ----------------------- Свойства --------------------------
    private final int _row;
    private final int _column;

    public int row() { return _row; }

    public int column() { return _column; }

    private void validate(int row, int col) {
        if(row < 0 || col < 0) {
            throw new IllegalArgumentException();
        }
    }

    // ----------------------- Порождение --------------------------
    public CellPosition(int row, int col) {
        validate(row, col);
        _row = row;
        _column = col;
    }

    // ------------------ Сравнение позиций ----------------
    @Override
    public boolean equals(Object other){

        if(other instanceof CellPosition) {
            // Типы совместимы, можно провести преобразование
            CellPosition otherPosition = (CellPosition)other;
            // Возвращаем результат сравнения углов
            return row() == otherPosition.row() && column() == otherPosition.column();
        }

        return false;
    }

    // --------------------------------------------------
    @Override
    public int hashCode() {
        // Одинаковые объекты должны возвращать одинаковые значения
        return row() * 1000 + column();
    }



}
