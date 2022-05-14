package view;

import Model.Event.ViewType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {
    private ViewType type;
    protected ActionListener parent;

    public MyFrame(ActionListener parent, ViewType type) throws HeadlessException {
        this.type = type;
        this.parent = parent;
    }

    public ViewType getViewType() {
        return type;
    }
}
