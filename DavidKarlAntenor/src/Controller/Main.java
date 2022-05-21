package Controller;
import View.GameFrameManager;

import java.awt.HeadlessException;

import javax.swing.*;

import Model.Player.PlayerException;

public class Main {
    public static void main(String []args) {
        try {
	    SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    try {
						GameFrameManager viewManager = new GameFrameManager();
					} catch (HeadlessException e) {
						e.printStackTrace();
					} catch (PlayerException e) {
						e.printStackTrace();
					}
                }
            });    
	} catch (Exception e) {
  	     System.out.println("Fail to execute Main.");
        }
    }
}
