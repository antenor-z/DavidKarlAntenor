package View;

import Model.GameException;
import Model.Event.ChangeViewEvent;
import Model.Event.ViewType;
import View.Exception.ViewException;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GameFrameManager implements ActionListener {
    private final List<MyFrame> frames = new ArrayList<MyFrame>();
    private final MyFrame currentFrame = null;

    public GameFrameManager() throws HeadlessException, GameException {
        MyFrame menu = new Menu(this);
        MyFrame game = new GameFrame(this);

        this.frames.add(menu);
        this.frames.add(game);
        this.setFrameVisible(menu, true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e instanceof ChangeViewEvent) {
            try {
                changeView((ChangeViewEvent) e);
            } catch (ViewException ex) {
                showToast(ex.getMessage());
            }
        }
    }

    private void setFrameVisible(MyFrame frame, Boolean isExclusive) {
        if (isExclusive) {
            frames.forEach(i -> {
                if (i != frame) {
                    i.setVisible(false);
                }
            });
        }
        frame.setVisible(true);
    }

    private void showToast(String msg) {
        Toast t = new Toast(msg, 150, 400);
        t.ShowToast();
    }

    private void changeView(ChangeViewEvent event) throws ViewException {
        ViewType viewType = event.getViewType();
        MyFrame target = findViewByType(viewType);

        if (target != null) {
            this.setFrameVisible(target, event.get_isExclusive());
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
