package View;

import Model.game.Game;
import Model.game.State;
import Model.gamefield.Builder;
import Model.builders.LevelOne;
import Model.gamefield.Direction;
import Model.gamefield.GameField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Окно игры
public class GameFrame extends JFrame {

    Game _game;
    GameField _field;

    public GameFrame() {

        Builder builder = new LevelOne();
        _game = new Game(builder);
        _game.start();
        _field = _game.getField();
        GameFieldView mainBox = new GameFieldView( _field );

        setContentPane(mainBox);
        setSize(mainBox.getSize());
        setResizable(false);
        setFocusable(true);
        setTitle("FootPrint Game");
        ImageIcon img = new ImageIcon(getClass().getResource("/img/robot.png"));
        setIconImage(img.getImage());
        addKeyListener( new KeyController() );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private class KeyController implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode();

            if (code == KeyEvent.VK_Q) { // Вверх влево
                _field.robot().move(Direction.northWest());
            } else if (code == KeyEvent.VK_W) { // Вверх
                _field.robot().move(Direction.north());
            } else if (code == KeyEvent.VK_E) { // Вверх вправо
                _field.robot().move(Direction.northEast());
            } else if (code == KeyEvent.VK_A) { // Вниз влево
                _field.robot().move(Direction.southWest());
            } else if (code == KeyEvent.VK_S) { // Вниз
                _field.robot().move(Direction.south());
            } else if (code == KeyEvent.VK_D) { // Вниз вправо
                _field.robot().move(Direction.southEast());
            }

            repaint();

            if (_game.getState() != State.PROCESSING){
                JOptionPane.showMessageDialog(null, _game.isPlayerWin() ? "Победа" : "Поражение");
                System.exit(0);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

    }
}
