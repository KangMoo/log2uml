package com.log2uml;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.InputStream;

public class Log2UmlMain extends Application {
    public static FXMLLoader loader;
    public static final double BASE_WIDTH = 800L;
    public static final double BASE_HEIGHT = 600L;
    public static final double BASE_TAB_SIZE = 30L;
    @Override
    public void start(Stage primaryStage) throws Exception{
        loader = new FXMLLoader(getClass().getResource(
                "main.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Log2Uml");
        Scene scene = new Scene(root, BASE_WIDTH, BASE_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(BASE_WIDTH);
        primaryStage.setMinHeight(BASE_HEIGHT + BASE_TAB_SIZE);
        Controller controller = loader.getController();
        controller.initialize();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
