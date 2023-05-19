package org.example;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.example.Car.Car;
import org.example.Database.Database;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class App extends Application {
    public static List<Car> carList = new ArrayList<>();
    //Main
    public static void main(String[] args)
    {

        Database database = new Database();
        database.initializeDatabase();
        database.carsToList(carList);
        launch();
    }
    //App
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent loginParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/LoginView.fxml")));
        Scene loginScene = new Scene(loginParent);
        primaryStage.setTitle("Wypozyczalnia samochodów");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }
}