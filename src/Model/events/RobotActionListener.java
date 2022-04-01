package Model.events;

import java.util.EventListener;
import java.util.Iterator;

public interface RobotActionListener extends EventListener {
    void robotMakedMove(RobotActionEvent e, Iterator<RobotActionListener> iter);
}
