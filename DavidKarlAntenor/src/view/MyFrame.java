package view;

import Model.Event.ViewType;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    private ViewType type;

    public MyFrame(ViewType type) throws HeadlessException {
        this.type = type;
    }

    public ViewType getViewType() {
        return type;
    }
}
