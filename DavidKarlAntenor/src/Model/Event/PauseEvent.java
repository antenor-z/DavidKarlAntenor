package Model.Event;

import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class PauseEvent extends ActionEvent {
    public PauseEvent(Object source, int id, String command) {
        super(source, id, command);
    }
}
