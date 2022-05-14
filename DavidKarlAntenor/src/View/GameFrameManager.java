package View;

import Model.Event.ChangeViewEvent;
import Model.Event.ViewType;
import View.Exception.ViewException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GameFrameManager implements ActionListener {
    private final List<GameFrame> frames = new ArrayList<GameFrame>();
    private final GameFrame currentFrame = null;

    public GameFrameManager() {
        GameFrame menu = new Menu(this);
        GameFrame game = new GameView(this);

        this.frames.add(menu);
        this.frames.add(game);
        this.setFrameVisible(menu);
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

    private void setFrameVisible(GameFrame frame) {
        frames.forEach(i -> {
            if (i != frame) {
                i.setVisible(false);
            }
        });
        frame.setVisible(true);
    }

    private void showToast(String msg) {
        Toast t = new Toast(msg, 150, 400);
        t.ShowToast();
    }

    private void changeView(ChangeViewEvent event) throws ViewException {
        ViewType viewType = event.getViewType();
        GameFrame target = findViewByType(viewType);

        if (target != null) {
            this.setFrameVisible(target);
        } else {
            throw new ViewException("Cannot find view of type: " + viewType);
        }
    }

    private GameFrame findViewByType(ViewType typeToFind) {
        return frames.stream()
                .filter((frame) -> typeToFind.equals(frame.getViewType()))
                .findFirst()
                .orElse(null);
    }
}
