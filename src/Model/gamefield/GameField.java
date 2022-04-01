package Model.gamefield;

import java.util.HashMap;
import java.util.Iterator;

import Model.units.Robot;

// Шестиугольное поле, состоящее из ячеек
public class GameField implements Iterable<AbstractCell> {

    // ---------------------- Размеры -----------------------------
    private final int _width;
    private final int _height;

    public int width() {
        return _width;
    }

    public int height() {
        return _height;
    }

    private int countTargetHexagon = 0;

    // --------------------------- Ячейки ----------------------
    public final HashMap<CellPosition, AbstractCell> _cells = new HashMap<>();

    public AbstractCell getCell(CellPosition pos) {
        return _cells.get( pos );
    }

    public AbstractCell getCell(int row, int col) {
        return getCell(new CellPosition(row, col));
    }

    public HashMap<CellPosition, AbstractCell> getCells()
    {
        return _cells;
    }

    @Override
    public Iterator<AbstractCell> iterator() {
        return new GameFieldIterator( this );
    }

    // ---------------------------- Порождение ---------------------
    public GameField(int height, int width) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException();
        }

        _width = width;
        _height = height;
        buildField();
        linkCells();
    }

    private void buildField() {

        // Создаем ячейки
        for (int row = 0; row < height(); row++) {
            for (int col = 0; col < width(); col++) {
                CellPosition pos = new CellPosition(row, col);
                _cells.put(pos, new Cell(pos));
            }
        }

    }
    private void linkCells() {
        // Связываем ячейки
        for (int row = 0; row < height(); row++) {
            for (int col = 0; col < width(); col++) {

                AbstractCell cell = getCell(row, col);

                //Для четных
                if((col%2)==0) {
                    //(Если снизу есть то ее можно связать)
                    if (height() > 1 && row < height() - 1) {
                        cell.setNeighbor(Direction.south(), getCell(row + 1, col));
                    }
                    //(Если сверху есть то ее можно связать)
                    if (row > 0) {
                        cell.setNeighbor(Direction.north(), getCell(row - 1, col));
                    }
                    //(Если сверху и справа есть то можно связать)
                    if (/*row > 0 &&*/ width() > 1 && col < width() - 1) {
                        cell.setNeighbor(Direction.northEast(), getCell(row, col + 1));
                    }
                    //(Если снизу и справа есть то ее можно связать)
                    if (height() > 1 && row < height() - 1 && width() > 1 && col < width() - 1) {
                        cell.setNeighbor(Direction.southEast(), getCell(row + 1, col + 1));
                    }  //(Если сверху и слева есть то ее можно связать)
                    if (row > 0 && col > 0) {
                        cell.setNeighbor(Direction.northWest(), getCell(row, col - 1));
                    }  //(Если снизу и слева есть то ее можно связать)
                    if (height() > 1 && row < height() - 1 && col > 0) {
                        cell.setNeighbor(Direction.southWest(), getCell(row + 1, col - 1));
                    }
                }
                //Для нечетных
                else
                {
                    //(Если снизу есть то ее можно связать)
                    if (height() > 1 && row < height() - 1) {
                        cell.setNeighbor(Direction.south(), getCell(row + 1, col));
                    }
                    //(Если сверху есть то ее можно связать)
                    if (row > 0) {
                        cell.setNeighbor(Direction.north(), getCell(row - 1, col));
                    }
                    //(Если сверху и справа есть то можно связать)
                    if (row > 0 && width() > 1 && col <  width() - 1) {
                        cell.setNeighbor(Direction.northEast(), getCell(row - 1, col + 1));
                    }
                    //(Если снизу и справа есть то ее можно связать)
                    if (height() > 1 && row < height() - 1 && width() > 1 && col < width() - 1) {
                        cell.setNeighbor(Direction.southEast(), getCell(row, col + 1));
                    }  //(Если сверху и слева есть то ее можно связать)
                    if (row > 0 && col > 0) {
                        cell.setNeighbor(Direction.northWest(), getCell(row - 1, col - 1));
                    }  //(Если снизу и слева есть то ее можно связать)
                    if (height() > 1 && row < height() - 1 && col > 0) {
                        cell.setNeighbor(Direction.southWest(), getCell(row, col - 1));
                    }
                }
            }
        }
    }

    public void setTargetHexagon(int row, int col){
        CellPosition pos = new CellPosition(row, col);

        // Если уже есть Целевой шестиугольник на карте, то второй не создавать
        if(countTargetHexagon == 1)
        {
            return;
        }
        //удалить эту ячейку из массива перед добавлением
        _cells.remove(pos);
        // Добавить ячейку
        TargetHexagon hexagon = new TargetHexagon(pos);

        _cells.put(pos, hexagon);
        // Связываем ячейки
        linkCells();
        countTargetHexagon++;
    }

    // ---------------------- Робот ---------------------------
    private final Robot _robot = new Robot();

    public Robot robot() {
        return _robot;
    }


    // --------------- Итератор по ячейкам ----------------
    private  class GameFieldIterator implements Iterator<AbstractCell> {

        private AbstractCell _cell = null;
        private final GameField _field;

        public GameFieldIterator(GameField field) {
            _field = field;
        }

        @Override
        public boolean hasNext() {

            return nextCell( _cell ) != null;
        }

        @Override
        public AbstractCell next() {
            _cell = nextCell(_cell);
            return _cell;
        }

        private AbstractCell nextCell(AbstractCell cell) {
            AbstractCell next_cell = null;

            if(cell == null) {
                next_cell = _field.getCell(0, 0);
            } else {
                next_cell = cell.neighbor(Direction.south());
                if( next_cell == null && cell.position().column() < _field.width() - 1 ) {
                    next_cell = _field.getCell( 0 , cell.position().column() + 1 );
                }
            }

            return next_cell;
        }
    }
}

//                    //Если это не ячейка не в последней строке то можно ставить снизу(Если снизу есть то ее можно связать)
//                    if (height() > 1 && row < height() - 1) {
//                        cell.setNeighbor(Direction.south(), cell(row + 1, col));
//                    }
//                    //Если это первая строка то вверху нельзя поставить(Если сверху есть то ее можно связать)
//                    if (row > 0) {
//                        cell.setNeighbor(Direction.north(), cell(row - 1, col));
//                    }
//                    //Если это не ячейка не в последней колонке то можно ставить слева(Если справа есть то можно связать)
//                    if (width() > 1 && col < width() - 1) {
//                        cell.setNeighbor(Direction.east(), cell(row, col + 1));
//                    }
//                    //Если это первая колонка то слева нельзя поставить(Если слева есть то ее можно связать)
//                    if (col > 0) {
//                        cell.setNeighbor(Direction.west(), cell(row, col - 1));
//                    }