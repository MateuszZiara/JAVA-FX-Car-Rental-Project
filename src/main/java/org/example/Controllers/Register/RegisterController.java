package org.example.Controllers.Register;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.Controllers.AbstractController;
import org.example.Controllers.Login.LoginController;
import org.example.Database.Database;

import javax.xml.crypto.Data;

public class RegisterController extends AbstractController {
    @FXML
    private Button CreateAccountButton;

    @FXML
    private Button ReturnButton;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastnameTextField;

    @FXML
    private TextField loginTextField;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private TextField peselTextField;

    @FXML
    private Label warningLabel;

    @FXML
    void CreateAccountButtonOnClick(ActionEvent event)
    {
        if(!checkTextFields())
            return;
        Database database = new Database();
        if(!database.checkUsername(loginTextField.getText())) {
            warningLabel.setText("Nazwa użytkownika zajęta");
            return;
        }
        database.addUser(loginTextField.getText(),passwordTextField.getText(),firstNameTextField.getText(),lastnameTextField.getText(),peselTextField.getText());
        AbstractController controller = new LoginController();
        controller.show(event,"/fxml/LoginView.fxml");

    }

    @FXML
    void ReturnButtonOnClick(ActionEvent event)
    {
        AbstractController controller = new LoginController();
        controller.show(event,"/fxml/LoginView.fxml");
    }

    boolean checkTextFields()
    {
        if(loginTextField.getText().isEmpty() || passwordTextField.getText().isEmpty() || firstNameTextField.getText().isEmpty() || lastnameTextField.getText().isEmpty() || peselTextField.getText().isEmpty())
        {
            warningLabel.setText("Wszsystkie pola muszą być zapełnione");
            return false;
        }
        else if(peselTextField.getText().length() != 11)
        {
            warningLabel.setText("Pesel musi mieć 11 cyfr");
            return false;
        }
        try {
            Long.parseLong(peselTextField.getText());
        } catch (NumberFormatException e) {
            warningLabel.setText("Pesel musi być liczbą");
            return false;
        }

        return true;
    }


}
