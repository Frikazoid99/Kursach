package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader;
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("test.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        stage.setTitle("Hotel Manager");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}