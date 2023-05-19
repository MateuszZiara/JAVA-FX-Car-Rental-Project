package org.example.Controllers.AdminPanel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.example.Controllers.AbstractController;
import org.example.Controllers.AddAdmin.AddAdminController;
import org.example.Controllers.AddCarController.AddCarController;
import org.example.Controllers.AppWindow.AppWindowController;

public class AdminPanelController extends AbstractController {

    final public String path = "/fxml/AdminPanel.fxml";
    @FXML
    private Button addAdminButton;

    @FXML
    private Button addCarButton;

    @FXML
    private Button returnButton;

    @FXML
    void onAddAdminButton(ActionEvent event)
    {

        AbstractController controller = new AddAdminController();
        controller.show(event, ((AddAdminController) controller).path);
    }

    @FXML
    void onReturnButton(ActionEvent event)
    {
        AbstractController controller = new AppWindowController();
        controller.show(event,((AppWindowController) controller).path);
    }

    @FXML
    void onaddCarButton(ActionEvent event)
    {
        AbstractController controller = new AddCarController();
        controller.show(event, ((AddCarController) controller).path);
    }

}
