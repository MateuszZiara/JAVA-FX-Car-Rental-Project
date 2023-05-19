package org.example.Controllers.Info;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.example.Car.Car;
import org.example.Controllers.AbstractController;
import org.example.Controllers.CarList.carListController;
import org.example.Database.Database;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.example.App.carList;

public class InfoController extends AbstractController
{
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    final public String path = "/fxml/infoView.fxml";
    List<String> stringList = new ArrayList<>();
    String colorSearch;
    String modelSearch;
    String markSearch;
    int mode = 0;

    @FXML
    private Label rentDateLabel;

    public Text getColorText() {
        return colorText;
    }

    public Text getCostText() {
        return costText;
    }

    public ImageView getImage() {
        return image;
    }

    public Text getMarkText() {
        return markText;
    }

    public Text getModelText() {
        return modelText;
    }
    @FXML
    private Button endRent;

    public Button getOnReturn() {
        return onReturn;
    }

    public Text getRentText() {
        return rentText;
    }

    public void setColorText(Text colorText) {
        this.colorText = colorText;
    }

    public void setCostText(Text costText) {
        this.costText = costText;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public void setMarkText(Text markText) {
        this.markText = markText;
    }

    public void setModelText(Text modelText) {
        this.modelText = modelText;
    }

    public void setOnReturn(Button onReturn) {
        this.onReturn = onReturn;
    }

    public void setRentText(Text rentText) {
        this.rentText = rentText;
    }

    @FXML
    private Text colorText;

    @FXML
    private Text costText;

    @FXML
    private ImageView image;

    @FXML
    private Text markText;

    @FXML
    private Text modelText;

    @FXML
    private Button onReturn;

    @FXML
    private Text rentText;
    private String text;
    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    public void setColorSearch(String colorSearch) {
        this.colorSearch = colorSearch;
    }

    public void setModelSearch(String modelSearch) {
        this.modelSearch = modelSearch;
    }

    public void setMarkSearch(String markSearch) {
        this.markSearch = markSearch;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    @FXML
    void onReturn(ActionEvent event)
    {
        AbstractController controller = new carListController();
        ((carListController) controller).setMode(mode);
        if(mode == 0)
        {
            ((carListController) controller).setColorSearch(colorSearch);
            ((carListController) controller).setModelSearch(modelSearch);
            ((carListController) controller).setMarkSearch(markSearch);
        }
        if(mode == 1)
        {

            ((carListController) controller).searchDefault(stringList);
            ((carListController) controller).setMode(1);
        }
        controller.show(event, ((carListController) controller).path);
    }
    @Override
    public void init(FXMLLoader loader)
    {
        InfoController controller = loader.getController();
        controller.mode = mode;
        controller.id = id;
        if (mode == 0) // Car search from carSearchView
        {
            controller.colorSearch = colorSearch;
            controller.markSearch = markSearch;
            controller.modelSearch = modelSearch;
        }
        else if (mode == 1) // From default search on nav bar
        {

            controller.stringList = stringList;
        }
        else if(mode == 2)
        {
            controller.rentText.setText(rentText.getText());
            controller.rentText.setVisible(true);
            controller.endRent.setVisible(true);
            controller.rentDateLabel.setVisible(true);

        }

         controller.colorText.setText(colorText.getText());
         controller.markText.setText(markText.getText());
         controller.modelText.setText(modelText.getText());
         controller.image.setImage(image.getImage());
         controller.costText.setText(costText.getText());

    }

    @FXML
    void onEndRent(ActionEvent event)
    {
        Database database = new Database();
        database.rentCarEnd(id);
        AbstractController controller = new carListController();
        ((carListController) controller).setMode(2);
        for(int i = 0; i< carList.size(); ++i)
        {
            if(carList.get(i).getId() == id)
            {
                carList.get(i).setBuyer_id(-1);
            }
        }
        controller.show(event, ((carListController) controller).path);

    }
}
