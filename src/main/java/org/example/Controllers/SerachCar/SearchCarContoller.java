package org.example.Controllers.SerachCar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    private Button returnButton;

    @FXML
    private Button searchButton;

    @FXML
    void onReturn(ActionEvent event)
    {
        AbstractController controller = new AppWindowController();
        controller.show(event, ((AppWindowController) controller).path);
    }

    @FXML
    void onSearch(ActionEvent event)
    {
        AbstractController controller = new carListController();
        ((carListController) controller).setColorSearch(colorTextField.getText());
        ((carListController) controller).setModelSearch(modelTextField.getText());
        ((carListController) controller).setMarkSearch(markTextField.getText());
        controller.show(event, ((carListController) controller).path);
    }

}
