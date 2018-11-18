package viewer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import jdk.nashorn.api.tree.SwitchTree;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/viewer/viewer.fxml"));
        primaryStage.setTitle("Mandelbrot");
        primaryStage.setScene(new Scene(root, 1200, 900));
        primaryStage.show();
    }


    public static void main(String[] args )  {


        launch(args);
    }
}
