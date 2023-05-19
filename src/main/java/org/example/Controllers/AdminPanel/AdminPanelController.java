package org.example.Controllers.AdminPanel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.example.Controllers.AbstractController;
import org.example.Controllers.AddAdmin.AddAdminController;
import org.example.Controllers.AddCarController.AddCarController;
import org.example.Controllers.AppWindow.AppWindowController;

public class AdminPanelController extends AbstractController {

    final public String path = "/fxml/AdminPanel.fxml";

    @FXML
    void onAddAdminButton(ActionEvent event)
    {

        AddAdminController controller = new AddAdminController();
        controller.show(event, controller.path);
    }

    @FXML
    void onReturnButton(ActionEvent event)
    {
        AppWindowController controller = new AppWindowController();
        controller.show(event, controller.path);
    }

    @FXML
    void onaddCarButton(ActionEvent event)
    {
        AddCarController controller = new AddCarController();
        controller.show(event, controller.path);
    }

}
