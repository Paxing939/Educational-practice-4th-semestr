package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.regex.Pattern;

public class FirstTaskPane extends FlowPane {
    // разные паттерны для регулярных выражений
    private final static Pattern N = Pattern.compile("([0-9]*)([1-9])([0-9]*)");
    private final static Pattern Z = Pattern.compile("[-+]?([0-9]*)([0-9])([0-9]*)");
    private final static Pattern R = Pattern.compile("[-+]?(([0-9]*[.]?[0-9]+)|([0-9]+[.]?[0-9]*))([eE][-+]?[0-9]+)?");
    private final static Pattern TIME = Pattern.compile("(([0-1][0-9])|([2][0-3])):([0-5][0-9])");
    private final static Pattern EMAIL = Pattern.compile("^(.+)@(.+)$", Pattern.CASE_INSENSITIVE);

    public FirstTaskPane(double v, double v1, ComboBox<String> comboBox) {
        super(v, v1, comboBox);

        // обработчик сообщений выпадающего списка
        comboBox.setOnAction(event -> {
            if (comboBox.getValue() == "Date") {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Correct type of data!");
                alert.setHeaderText("Please, write date in such format, as YYYY-MM-DD");
                alert.showAndWait();
            }
        });

        TextField textField = new TextField();
        textField.setPrefHeight(50);

        // кружок для правильности данных
        Circle circle = new Circle(40, 100, 5);
        circle.setFill(Color.RED);

        // обработчик сообщений смены цвета кружка
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            switch (comboBox.getValue()) {
                case "E-Mail": {
                    if (EMAIL.matcher(newValue).matches()) {
                        circle.setFill(Color.GREEN);
                    } else {
                        circle.setFill(Color.RED);
                    }
                    break;
                }
                case "Time": {
                    if (TIME.matcher(newValue).matches()) {
                        circle.setFill(Color.GREEN);
                    } else {
                        circle.setFill(Color.RED);
                    }
                    break;
                }
                case "Natural number": {
                    if (N.matcher(newValue).matches()) {
                        circle.setFill(Color.GREEN);
                    } else {
                        circle.setFill(Color.RED);
                    }
                    break;
                }
                case "Integer": {
                    if (Z.matcher(newValue).matches()) {
                        circle.setFill(Color.GREEN);
                    } else {
                        circle.setFill(Color.RED);
                    }
                    break;
                }
                case "Real number": {
                    if (R.matcher(newValue).matches()) {
                        circle.setFill(Color.GREEN);
                    } else {
                        circle.setFill(Color.RED);
                    }
                    break;
                }
                case "Date": {
                    if (ValidDate.isValidDate(newValue)) {
                        circle.setFill(Color.GREEN);
                    } else {
                        circle.setFill(Color.RED);
                    }
                    break;
                }
            }
        });
        getChildren().add(textField);
        getChildren().add(circle);
    }
}
