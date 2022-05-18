package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MyPanel extends JPanel {
    protected ActionListener _controller;
    protected CardLayout _cl;
    protected JPanel _panelCont;

    public MyPanel(CardLayout cl, JPanel panelCont, ActionListener controller) {
        _controller = controller;
        _cl = cl;
        _panelCont = panelCont;
    }
}
