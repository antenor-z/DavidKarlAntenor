package view;

import Model.Event.ChangeViewEvent;
import Model.Event.ViewType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ViewManager implements ActionListener {
    private final List<MyFrame> frames = new ArrayList<MyFrame>();

    public ViewManager() {
        MyFrame menu = new Menu(this);
        MyFrame game = new GameView(this);

        this.frames.add(menu);
        this.frames.add(game);
        menu.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e instanceof ChangeViewEvent) {
            try {
                changeView((ChangeViewEvent) e);
            } catch (ViewException ex) {
                showToast(ex.getMessage());
            }
        } else {
            showToast("Unknown Event");
        }
    }

    private void showToast(String msg) {
        Toast t = new Toast(msg, 150, 400);
        t.ShowToast();
    }

    private void changeView(ChangeViewEvent event) throws ViewException {
        ViewType viewType = event.getViewType();
        MyFrame target = findViewByType(viewType);

        if (target != null) {
            showToast("Hello world.");
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
