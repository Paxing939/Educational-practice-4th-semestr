package log;


import javafx.scene.control.TextArea;

import java.util.Observable;
import java.util.Observer;

public class ObserverTextArea extends TextArea implements Observer {
    public ObserverTextArea() {
        super();
    }

    @Override
    public void update(Observable o, Object arg) {
        this.setText(this.getText() + arg.toString() + "\n");
    }
}
