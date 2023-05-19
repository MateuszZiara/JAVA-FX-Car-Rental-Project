package org.example.Controllers.AddAdmin;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.example.Controllers.AbstractController;
import org.example.Controllers.AdminPanel.AdminPanelController;
import org.example.Database.Database;

public class AddAdminController extends AbstractController {
    final public String path = "/fxml/AddAdminView.fxml";


    @FXML
    private TextField loginTextField;

    @FXML
    private Label warningLabel;

    @FXML
    void onAddAdminButton()
    {

        Database database = new Database();

        if(!database.addAdmin(loginTextField.getText()))
        {
            warningLabel.setText("Brak konta");

        }
        else
        {
            warningLabel.setText("Dodano Admina");
        }

    }

    @FXML
    void onRemoveAdmin()
    {

    }

    @FXML
    void onReturn(MouseEvent event)
    {

        AdminPanelController controller = new AdminPanelController();
        controller.showMouseEvent(event, controller.path);
    }

}
