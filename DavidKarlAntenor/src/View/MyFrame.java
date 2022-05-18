package View;

import Model.Event.ViewType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame {
    private ViewType type;
    protected ActionListener parent;

    public GameFrame(ActionListener parent, ViewType type) throws HeadlessException {
        this.type = type;
        this.parent = parent;
    }

    public ViewType getViewType() {
        return type;
    }
}
