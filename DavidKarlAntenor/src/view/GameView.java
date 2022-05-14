package view;

import Model.Event.ViewType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameView extends MyFrame {
    final private int windowSizeX = 1280;
    final private int windowSizeY = 720;

    public GameView(ActionListener parent) throws HeadlessException {
        super(parent, ViewType.START_MENU);

        setSize(windowSizeX, windowSizeY);
        setLayout(null);

        setResizable(false);
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - getWidth()/2, (Toolkit.getDefaultToolkit().getScreenSize().height)/2 - getHeight()/2);
    }
}
