package view;

import Model.Event.ChangeVewEvent;
import Model.Event.ViewType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ViewManager implements ActionListener {
    private final List<MyFrame> frames = new ArrayList<MyFrame>();

    public ViewManager() {
        MyFrame menu = new Menu();

        this.frames.add(menu);
        menu.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e instanceof ChangeVewEvent) {
            try {
                changeView((ChangeVewEvent) e);
            } catch (ViewException ex) {
                showToast(ex.getMessage());
            }
        }
    }

    private void showToast(String msg) {
        Toast t = new Toast(msg, 150, 400);
        t.showtoast();
    }

    private void changeView(ChangeVewEvent event) throws ViewException {
        ViewType viewType = event.getViewType();
        MyFrame target = findViewByType(viewType);

        if (target != null) {

        } else {
            throw new ViewException("Cannot find view of type: " + viewType);
        }
    }

    private MyFrame findViewByType(ViewType typeToFind) {
        return frames.stream()
                .filter((frame) -> typeToFind.equals(frame.getViewType()))
                .findFirst()
                .orElse(null);
    }
}
