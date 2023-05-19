package org.example.Controllers.SerachCar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.example.Controllers.AbstractController;
import org.example.Controllers.AppWindow.AppWindowController;
import org.example.Controllers.CarList.carListController;

public class SearchCarContoller extends AbstractController{

    final public String path = "/fxml/SearchCarView.fxml";
    @FXML
    private TextField colorTextField;

    @FXML
    private TextField markTextField;

    @FXML
    private TextField modelTextField;

    @FXML
    void onReturn(ActionEvent event)
    {
        AppWindowController controller = new AppWindowController();
        controller.show(event, controller.path);
    }

    @FXML
    void onSearch(ActionEvent event)
    {
        carListController controller = new carListController();
        controller.setColorSearch(colorTextField.getText());
        controller.setModelSearch(modelTextField.getText());
        controller.setMarkSearch(markTextField.getText());
        controller.show(event, controller.path);
    }

}
