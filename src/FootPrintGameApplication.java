import View.GameFrame;

import javax.swing.*;

public class FootPrintGameApplication {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GameFrame mainW = new GameFrame();
                mainW.setVisible(true);
            }
        });
    }
}