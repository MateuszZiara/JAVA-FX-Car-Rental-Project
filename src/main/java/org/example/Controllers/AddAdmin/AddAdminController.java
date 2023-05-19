package org.example.Controllers.AddAdmin;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import org.example.Controllers.AbstractController;
import org.example.Controllers.AdminPanel.AdminPanelController;
import org.example.Database.Database;

public class AddAdminController extends AbstractController {
    final public String path = "/fxml/AddAdminView.fxml";
    @FXML
    private ImageView addAdminButton;


    @FXML
    private TextField loginTextField;

    @FXML
    private ImageView removeAdmin;

    @FXML
    private ImageView returnButton;

    @FXML
    private Label warningLabel;

    @FXML
    void onAddAdminButton(MouseEvent event)
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
    void onRemoveAdmin(MouseEvent event)
    {

    }

    @FXML
    void onReturn(MouseEvent event)
    {

        AbstractController controller = new AdminPanelController();
        controller.showMouseEvent(event, ((AdminPanelController) controller).path);
    }

}
