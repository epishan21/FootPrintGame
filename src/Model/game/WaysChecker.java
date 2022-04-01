package Model.game;
import Model.gamefield.*;

import java.util.*;
import java.util.List;

// Проверка на наличие пути от робота до целевого шестиугольника
public class WaysChecker {

    public boolean CheckWay(CellPosition posRobot, HashMap<CellPosition, AbstractCell> _cells) {

        AbstractCell robotCell = _cells.get(posRobot); // Ячейка с роботом

        List<AbstractCell> checkedCells = new ArrayList<>(); // Проверенные ячейки
        List<AbstractCell> toCheckCells = new ArrayList<>(); // Ячейки которые надо проверить
        toCheckCells.add(robotCell);

        // Пока есть что проверять
        while (!toCheckCells.isEmpty()) {
            AbstractCell currentCell = toCheckCells.get(0);

            // Для каждого направления ячейки
            for (Direction direction : Direction.directions) {
                AbstractCell nextCell = currentCell.neighbor(direction);

                // Если сосед является целевым  шестиугольником
                if(nextCell != null && nextCell.getClass().equals(TargetHexagon.class))
                    return true;
                // Иначе если соседняя ячейка имеется и она не является следом цвета игрока и она еще не проверена
                else if (nextCell != null &&  ((Cell)nextCell).getFootprint() != robotCell.getRobot().getColor() && !checkedCells.contains(nextCell)) {
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


