package org.example.Controllers.Login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.Controllers.AbstractController;
import org.example.Controllers.AppWindow.AppWindowController;
import org.example.Controllers.Register.RegisterController;
import org.example.Database.Database;

public class LoginController extends AbstractController {

    final public String path = "/fxml/LoginView.fxml";

    @FXML
    private TextField loginTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private Label warningLabel;

    @FXML
    void onButtonLogin(ActionEvent event)
    {
        Database database = new Database();
        if(database.login(loginTextField.getText(),passwordTextField.getText()))
        {
            AppWindowController controller = new AppWindowController();
            controller.show(event, controller.path);
        }
        else
        {
            warningLabel.setText("Błędny login lub hasło");
        }
    }

    @FXML
    void onButtonRegister(ActionEvent event)
    {
       AbstractController controller = new RegisterController();
       controller.show(event,"/fxml/RegisterView.fxml");

    }

}
