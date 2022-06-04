import View.GameController;

import javax.swing.*;

public class Main {
    public static void main(String []args) {
        try {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    GameController viewManager = new GameController();
                }
            });
        } catch (Exception e) {
            System.out.println("Fail to execute Main.");
        }
    }
}
