package src;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.geometry.*;
import javafx.application.Platform;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;



public class JavaFXMain extends Application {
    public static GridPane pane = new GridPane();

    public static void update(int row, String message)
    {
        //Runnable task = new Runnable() {
        //        @Override
        //        public void run() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                TextField current = new TextField();
                pane.add(current, 1, row);
                current = new TextField(message);
                pane.add(current, 1, row);
            }
        });
        //}
        //};
    }

    @Override
    public void start(Stage primaryStage) {
        //GridPane pane = new GridPane();
        pane.setAlignment(Pos.TOP_LEFT);
        pane.setHgap(2);
        pane.setVgap(15);
        pane.setPadding(new Insets(10));
        ColumnConstraints column1 = new ColumnConstraints(100);
        ColumnConstraints column2 = new ColumnConstraints(50, 150, 300);
        column2.setHgrow(Priority.ALWAYS);
        pane.getColumnConstraints().addAll(column1, column2);

        pane.add(new Label("USER1:"), 0, 0);
        pane.add(new Label("USER2:"), 0, 1);
        pane.add(new Label("USER3:"), 0, 2);
        pane.add(new Label("USER4:"), 0, 3);

        pane.add(new Label("DISK1:"), 0, 4);
        pane.add(new Label("DISK2:"), 0, 5);

        pane.add(new Label("PRINTER1:"), 0, 6);
        pane.add(new Label("PRINTER2:"), 0, 7);
        pane.add(new Label("PRINTER3:"), 0, 8);

        Scene scene = new Scene(pane, 380, 450);

        primaryStage.setTitle("141OS");
        primaryStage.setScene(scene);
        primaryStage.show();
    }



}
