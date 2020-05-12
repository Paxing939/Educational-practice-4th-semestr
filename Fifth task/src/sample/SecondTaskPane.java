package sample;

import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;

public class SecondTaskPane extends FlowPane {

    // исходный текст для второго задания
    private final static String START_STRING = "dfkjbkjxdfhbkxdjkfbjkxdfn2020-12-20sjv<JSBVh,bSV" +
            "sjbvdhjBDSvhjbSDvhjbSD1212-01-06<JvhjDvhjhdsvhjSJdvbhjBSDvhj,s6543-10-12sdkjvbS<HDhj,bSj,hvshdvhjSDJvs";

    public SecondTaskPane(double width) {
        setOrientation(Orientation.VERTICAL);

        // две TextArea для входного текста и для вывода дат
        TextArea textArea = new TextArea(), finalArea = new TextArea();

        // задаем разные характеристики для окон ввода и вывода
        textArea.setMaxWidth(width);
        textArea.setWrapText(true);
        textArea.setPrefRowCount(6);
        textArea.setText(START_STRING);
        finalArea.setPrefRowCount(6);
        finalArea.setWrapText(true);
        finalArea.setMaxWidth(width);

        // кнопка поиска дат в тексте
        Button findDates = new Button("find dates!");

        // обработчик сообщений кнопки
        findDates.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            String text = textArea.getText(); // получаем текст из поля
            finalArea.clear();
            var foundDates = FoundText.findDateInText(text);
            for (var date : foundDates) {
                finalArea.appendText(date + "\n");
            }
        });
        getChildren().add(textArea);
        getChildren().add(findDates);
        getChildren().add(finalArea);
    }

}
