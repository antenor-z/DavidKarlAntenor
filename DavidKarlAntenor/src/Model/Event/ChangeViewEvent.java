package Model.Event;
import java.awt.event.ActionEvent;

public class ChangeViewEvent extends ActionEvent {
    private ViewType _type;
    private Boolean _isExclusive;

    public ChangeViewEvent(Object source, int id, String command, ViewType _type) {
        super(source, id, command);
        this._type = _type;
	this._isExclusive = true;
    }

    public ViewType getViewType() {
        return _type;
    }

    public void set_isExclusive(Boolean isExclusive) {
        this._isExclusive = isExclusive;
    }

    public Boolean get_isExclusive() {
        return _isExclusive;
    }
}
