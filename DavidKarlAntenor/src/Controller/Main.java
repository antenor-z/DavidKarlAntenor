package Controller;
import View.GameFrameManager;
import javax.swing.*;

public class Main {
    public static void main(String []args) {
        try {
	    SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    GameFrameManager viewManager = new GameFrameManager();
                }
            });    
	} catch (Exception e) {
  	     System.out.println("Fail to execute Main.");
        }
    }
}
