package Model.Event;
import java.awt.event.ActionEvent;

public class ChangeVewEvent extends ActionEvent {
    private ViewType _type;

    public ChangeVewEvent(Object source, int id, String command, ViewType type) {
        super(source, id, command);
        _type = type;
    }

    public ChangeVewEvent(Object source, int id, String command, int modifiers, ViewType type) {
        super(source, id, command, modifiers);
        _type = type;
    }

    public ChangeVewEvent(Object source, int id, String command, long when, int modifiers, ViewType type) {
        super(source, id, command, when, modifiers);
        _type = type;
    }

    public ViewType getViewType() {
        return _type;
    }
}
