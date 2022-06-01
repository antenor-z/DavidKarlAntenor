package Controller;
import View.GameFrameManager;

import java.awt.HeadlessException;

import javax.swing.*;

import Model.GameException;

public class Main {
    public static void main(String []args) {
        try {
	    SwingUtilities.invokeLater(new Runnable() {
                public void run(){  
						try {
							GameFrameManager viewManager = new GameFrameManager();
						} catch (GameException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                }
            });    
	} catch (Exception e) {
  	     System.out.println("Fail to execute Main.");
        }
    }
}
