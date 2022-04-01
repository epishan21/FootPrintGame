package Model.events;

import java.util.EventObject;

// Событие, связанное с деятельностью робота
public class RobotActionEvent extends EventObject {
    public RobotActionEvent(Object source) {
        super(source);
    }
}
