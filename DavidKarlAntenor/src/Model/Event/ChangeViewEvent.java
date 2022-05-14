package Model.Event;
import java.awt.event.ActionEvent;

public class ChangeViewEvent extends ActionEvent {
    private ViewType _type;

    public ChangeViewEvent(Object source, int id, String command, ViewType _type) {
        super(source, id, command);
        this._type = _type;
    }

    public ViewType getViewType() {
        return _type;
    }
}
