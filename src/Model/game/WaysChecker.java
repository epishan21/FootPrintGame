package Model.game;

import Model.gamefield.CellPosition;
import Model.gamefield.Direction;
import Model.gamefield.cells.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// Проверка на наличие пути от робота до целевого шестиугольника
public class WaysChecker {

    public boolean checkWay(CellPosition posUnit, CellPosition posCell, HashMap<CellPosition, AbstractCell> _cells) {

        AbstractCell unitCell = _cells.get(posUnit); // Ячейка с роботом

        List<AbstractCell> checkedCells = new ArrayList<>(); // Проверенные ячейки
        List<AbstractCell> toCheckCells = new ArrayList<>(); // Ячейки которые надо проверить
        toCheckCells.add(unitCell);

        // Пока есть что проверять
        while (!toCheckCells.isEmpty()) {
            AbstractCell currentCell = toCheckCells.get(0);
            // Если Робот стоит на искомой ячейке
            if (currentCell != null && currentCell.position().row() == posCell.row() && currentCell.position().column() == posCell.column()) {
                return true;
            }

            // Для каждого направления ячейки
            for (Direction direction : Direction.directions) {
                AbstractCell nextCell = currentCell.neighbor(direction);

                // Если сосед является искомой ячейкой
                if (nextCell != null && nextCell.position().row() == posCell.row() && nextCell.position().column() == posCell.column()) {
                    return true;
                }
                // Иначе если соседняя ячейка имеется и она не является следом цвета игрока и она еще не проверена
                else if (nextCell instanceof PassableCell && (nextCell.getClass().equals(AlwaysPassableCell.class) ||
                        ((ObliviousCell) nextCell).isCellPassable(((UnitCell) unitCell).getRobot())) &&
                        !checkedCells.contains(nextCell)) {
                    // Добавить в список ячеек которые надо проверить
                    toCheckCells.add(nextCell);
                }
            }

            // Добавить currentCell в список проверенных
            checkedCells.add(currentCell);

            // Удалить currentCell из списка ячеек которые надо проверить
            toCheckCells.remove(0);

        }
        return false;
    }
}


