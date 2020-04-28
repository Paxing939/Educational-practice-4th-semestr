package log;

import javafx.scene.control.Label;

import java.util.Observable;
import java.util.Observer;

public class ObserverLabel extends Label implements Observer {

    public ObserverLabel() {
        super();
    }

    @Override
    public void update(Observable o, Object arg) {
        this.setText(arg.toString());
    }
}
