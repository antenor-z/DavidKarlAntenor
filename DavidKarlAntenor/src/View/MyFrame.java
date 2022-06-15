package View;

import Model.Event.ViewType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class MyFrame extends JFrame {
    private ViewType type;
    protected ActionListener controller;

    public MyFrame(ActionListener controller, ViewType type) throws HeadlessException {
        this.type = type;
        this.controller = controller;
    }

    public ViewType getViewType() {
        return type;
    }
}