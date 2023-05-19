package org.example.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;


import java.io.IOException;

public abstract class AbstractController
{
    public String path;
   public void show(ActionEvent event, String path) {

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent parent;
       try {
           FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
           parent = loader.load();
           init(loader);
       } catch (IOException e) {
           throw new RuntimeException(e);
       }

        Scene scene = new Scene(parent);
        stage.setScene(scene);
    }

        public void init(FXMLLoader loader)
        {

        }
        public void showMouseEvent(MouseEvent event, String path)
        {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent parent;
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
                parent = loader.load();
                init(loader);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Scene scene = new Scene(parent);
            stage.setScene(scene);
        }


    }

