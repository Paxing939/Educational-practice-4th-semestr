package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    // массив строк для выпадающего списка
    private final static String[] ELEMENTS = {"Natural number", "Date", "E-Mail", "Time", "Real number", "Integer"};

    @Override
    public void start(Stage primaryStage) {
        // название окна
        primaryStage.setTitle("Forth task");

        // высота и ширина
        final double width = 325, height = 300;

        // первое задание
        // вкладки для разделения двух заданий
        TabPane tabPane = new TabPane();
        Tab firstTask = new Tab("First task"), secondTask = new Tab("Second task");
        tabPane.getTabs().add(firstTask);
        tabPane.getTabs().add(secondTask);
        VBox vBox = new VBox(tabPane);


        // выпадающий список
        ComboBox<String> comboBox = new ComboBox(FXCollections.observableArrayList(ELEMENTS));
        comboBox.setPrefHeight(50);
        comboBox.setValue("Natural number");

        // панель для первого задания
        FlowPane firstPane = new FirstTaskPane(10, 10, comboBox);
        firstTask.setContent(firstPane);

        // второе задание
        // панель для второго задания
        FlowPane secondPane = new SecondTaskPane(width);
        secondTask.setContent(secondPane);

        primaryStage.setScene(new Scene(vBox, width, height));
        primaryStage.setResizable(false); // окно не изменет размер
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
