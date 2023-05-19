package org.example.Controllers.AppWindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.example.Controllers.AbstractController;
import org.example.Controllers.AccountSettings.SettingsController;
import org.example.Controllers.AddAdmin.AddAdminController;
import org.example.Controllers.AdminPanel.AdminPanelController;
import org.example.Controllers.CarList.carListController;
import org.example.Controllers.Login.LoginController;
import org.example.Controllers.SerachCar.SearchCarContoller;
import org.example.User.User;

import java.nio.FloatBuffer;
import java.util.Arrays;
import java.util.List;

public class AppWindowController extends AbstractController {

    @FXML
    private Button accountsettingsButton;

    @FXML
    private Button addCarButton;

    @FXML
    private ImageView avatar;

    @FXML
    private TextField defaultSearch;

    @FXML
    private Button defaultSearchButton;

    @FXML
    private Label loginLabel;

    @FXML
    private Button logoutButton;

    @FXML
    private Button searchCarsButton;

    @FXML
    private Button showCarsButton;
    final public String path = "/fxml/AppWindow.fxml";

    @FXML
    void onAddCarButton(ActionEvent event)
    {
        if(User.getInstance().getAdmin() == 1)
        {
            AbstractController controller = new AdminPanelController();
            controller.show(event, ((AdminPanelController) controller).path);
        }
    }

    @FXML
    void onDefaultSearch(ActionEvent event)
    {
        AbstractController controller = new carListController();
        List<String> stringList = Arrays.asList(defaultSearch.getText().split(" "));
        ((carListController) controller).searchDefault(stringList);
        ((carListController) controller).setMode(1);
        ((carListController) controller).setStringListBufor(stringList);
        controller.show(event, ((carListController) controller).path);
    }

    @FXML
    void onSearch(ActionEvent event)
    {
        AbstractController controller = new SearchCarContoller();
        controller.show(event, ((SearchCarContoller) controller).path);
    }

    @FXML
    void onShowCarsButton(ActionEvent event)
    {
        AbstractController controller = new carListController();
        controller.show(event, ((carListController) controller).path);
    }

    @FXML
    void onaccountsettingsButtonClick(ActionEvent event)
    {
        AbstractController controller = new SettingsController();
        controller.show(event,((SettingsController) controller).path);
    }

    @FXML
    void onlogoutButtonClick(ActionEvent event)
    {
        AbstractController controller = new LoginController();
        controller.show(event, ((LoginController) controller).path);
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
        AbstractController controller = new carListController();
        ((carListController) controller).setMode(2);
        controller.show(event, ((carListController) controller).path);
    }
}
