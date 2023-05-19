package org.example.Controllers.AppWindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.Controllers.AbstractController;
import org.example.Controllers.AccountSettings.SettingsController;
import org.example.Controllers.AdminPanel.AdminPanelController;
import org.example.Controllers.CarList.carListController;
import org.example.Controllers.Login.LoginController;
import org.example.Controllers.SerachCar.SearchCarContoller;
import org.example.User.User;

import java.util.Arrays;
import java.util.List;

public class AppWindowController extends AbstractController {

    @FXML
    private Button addCarButton;

    @FXML
    private TextField defaultSearch;

    @FXML
    private Label loginLabel;

    final public String path = "/fxml/AppWindow.fxml";

    @FXML
    void onAddCarButton(ActionEvent event)
    {
        if(User.getInstance().getAdmin() == 1)
        {
            AdminPanelController controller = new AdminPanelController();
            controller.show(event, controller.path);
        }
    }

    @FXML
    void onDefaultSearch(ActionEvent event)
    {
        carListController controller = new carListController();
        List<String> stringList = Arrays.asList(defaultSearch.getText().split(" "));
        controller.searchDefault(stringList);
        controller.setMode(1);
        controller.setStringListBufor(stringList);
        controller.show(event, controller.path);
    }

    @FXML
    void onSearch(ActionEvent event)
    {
        SearchCarContoller controller = new SearchCarContoller();
        controller.show(event, controller.path);
    }

    @FXML
    void onShowCarsButton(ActionEvent event)
    {
        carListController controller = new carListController();
        controller.show(event, controller.path);
    }

    @FXML
    void onaccountsettingsButtonClick(ActionEvent event)
    {
        SettingsController controller = new SettingsController();
        controller.show(event, controller.path);
    }

    @FXML
    void onlogoutButtonClick(ActionEvent event)
    {
        LoginController controller = new LoginController();
        controller.show(event, controller.path);
    }
    @Override
    public void init(FXMLLoader loader)
    {
        AppWindowController controller = loader.getController();
        controller.loginLabel.setText(User.getInstance().getLogin());
        User user = User.getInstance();
        if(user.getAdmin() == 1)
        {
            controller.addCarButton.setVisible(true);
        }
    }

    @FXML
    void onMyCars(ActionEvent event)
    {
        carListController controller = new carListController();
        controller.setMode(2);
        controller.show(event, controller.path);
    }
}
