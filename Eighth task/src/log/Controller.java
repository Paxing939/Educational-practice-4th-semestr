package log;

import javafx.fxml.FXML;

import java.util.Observable;

public class Controller extends Observable {

    @FXML
    ObserverLabel label = new ObserverLabel();

    @FXML
    ObserverTextArea textArea = new ObserverTextArea();

    @Override
    public void notifyObservers(Object o) {
        this.setChanged();
        super.notifyObservers(o);
    }

    public void initialize() {
        this.addObserver(textArea);
        this.addObserver(label);
    }
}
