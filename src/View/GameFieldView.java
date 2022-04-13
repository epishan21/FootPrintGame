package View;

import Model.game.Game;
import Model.gamefield.*;

import javax.swing.*;
import javax.swing.text.Position;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

// Виджет для отрисовки поля и всего того, что на нем. Так же управляет робот с помощью клавиатуры
public class GameFieldView extends JPanel {

    private final CellPosition margins = new CellPosition(5, 5);

    public GameFieldView(GameField field) {
        setLayout(null);
        setSize(
                (int) (margins.row() * 2 + CellWidget.CELL_SIZE * (field.width() + 0.7) * 0.75),
                (int) (margins.column() * 2 + CellWidget.CELL_SIZE * (field.height() + 1.3) * CellWidget.CELL_WIDTH_MOD)
        );

        for (int x = 0; x < field.height(); x++) {
            for (int y = 0; y < field.width(); y++) {
                boolean withOffset = y % 2 == 0;

                CellWidget cellWidget = new CellWidget(field.getCell(x, y));
                cellWidget.setBounds(
                        (int) (margins.row() + cellWidget.CELL_SIZE * y * 0.75),
                        (int) (margins.column() + cellWidget.CELL_SIZE * cellWidget.CELL_WIDTH_MOD * (x + (withOffset ? 0.5 : 0))),
                        cellWidget.CELL_SIZE, cellWidget.CELL_SIZE
                );
                add(cellWidget);
            }
        }
        setFocusable(true);
    }
}

