package org.example.Controllers.AccountSettings;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.Controllers.AbstractController;
import org.example.Controllers.AppWindow.AppWindowController;
import org.example.Controllers.Login.LoginController;
import org.example.Database.Database;
import org.example.User.User;

import javax.xml.crypto.Data;

public class SettingsController extends AbstractController {

    public String path = "/fxml/SettingView.fxml/";
    @FXML
    private Button cancelButton;

    @FXML
    private Button confirmButton;

    @FXML
    private TextField firstnameTextField;

    @FXML
    private TextField lastnameTextField;

    @FXML
    private Label loginLabel;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private TextField peselTextField;

    @FXML
    private Label warningLabel;

    @FXML
    void onCancel(ActionEvent event)
    {
        AbstractController controller = new AppWindowController();
        controller.show(event,((AppWindowController) controller).path);
    }

    @FXML
    void onConfirm(ActionEvent event) {
        if (passwordTextField.getText().length() < 5 && !passwordTextField.getText().isEmpty()) {
            warningLabel.setText("Your password have to has at least 5 characters");
            return;
        } else if (peselTextField.getText().length() != 11 && !peselTextField.getText().isEmpty()) {
            warningLabel.setText("Your PESEL have to has 11 characters");
            return;
        } else {
            if (!peselTextField.getText().isEmpty()) {
                try {
                    long num = Long.parseLong(peselTextField.getText());
                } catch (NumberFormatException e) {
                    warningLabel.setText("Your PESEL isn't integer");
                    return;
                }
            }

        }
        Database database = new Database();
        database.changeAccountSettings(loginLabel.getText(),firstnameTextField.getText(),lastnameTextField.getText(),peselTextField.getText(),passwordTextField.getText());
        AbstractController controller = new LoginController();
        controller.show(event,((LoginController) controller).path);

    }
    @Override
    public void init(FXMLLoader loader)
    {
        SettingsController controller = loader.getController();
        controller.loginLabel.setText(User.getInstance().getLogin());
        controller.passwordTextField.setPromptText(User.getInstance().getPassword());
        controller.firstnameTextField.setPromptText(User.getInstance().getFirstName());
        controller.lastnameTextField.setPromptText(User.getInstance().getLastName());
        controller.peselTextField.setPromptText(User.getInstance().getPesel());

    }

}
