package Model.Event;
import java.awt.event.ActionEvent;

// todo : https://stackoverflow.com/questions/44551760/extending-actionevent-a-custom-event-without-a-custom-listener
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
