package org.example.Controllers.CarList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.example.Car.Car;
import org.example.Controllers.AbstractController;
import org.example.Controllers.AppWindow.AppWindowController;
import org.example.Controllers.Info.InfoController;
import org.example.Database.Database;
import org.example.User.User;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.example.App.carList;

public class carListController extends AbstractController
{
    int mode = 0;
    String modelSearch;
    String markSearch;
    String colorSearch;
    List<String> stringListBufor = new ArrayList<>();
    final public String path = "/fxml/carList.fxml";

    @FXML
    private ImageView image1;

    @FXML
    private ImageView image2;

    @FXML
    private ImageView image3;

    @FXML
    private Button info1;

    @FXML
    private Button info2;

    @FXML
    private Button info3;

    @FXML
    private Text mark1;

    @FXML
    private Text markLabel1;

    @FXML
    private Text markLabel2;

    @FXML
    private Text markLabel3;
    @FXML
    private Text modelLabel1;

    @FXML
    private Text modelLabel2;

    @FXML
    private Text modelLabel3;

    @FXML
    private Text mark2;

    @FXML
    private Text mark3;

    @FXML
    private Text model1;

    public void setStringListBufor(List<String> stringListBufor) {
        this.stringListBufor = stringListBufor;
    }

    @FXML
    private Text model2;

    @FXML
    private Text model3;


    @FXML
    private Button rent1;

    @FXML
    private Button rent2;

    @FXML
    private Button rent3;

    List<Car> matchCars = new ArrayList<>();
    int page = 0;
    int getMaxPage()
    {
        int bufor = matchCars.size()/3;
        int rest = matchCars.size()%3;
        if(rest > 0)
        {
            bufor++;
        }
        return bufor-1;
    }
    void setDefault()
    {
        model1.setText("");
        model2.setText("");
        model3.setText("");
        mark1.setText("");
        mark2.setText("");
        mark3.setText("");
        image1.setImage(null);
        image2.setImage(null);
        image3.setImage(null);
        info1.setVisible(false);
        info2.setVisible(false);
        info3.setVisible(false);
        rent1.setVisible(false);
        rent2.setVisible(false);
        rent3.setVisible(false);
        mark1.setVisible(false);
        model1.setVisible(false);
        markLabel1.setVisible(false);
        markLabel2.setVisible(false);
        markLabel3.setVisible(false);
        modelLabel1.setVisible(false);
        modelLabel2.setVisible(false);
        modelLabel3.setVisible(false);
    }
    void serachByID()
    {
        for(Car car : carList)
        {
            if( car.getBuyer_id() == User.getInstance().getId())
            {
                matchCars.add(car);
            }
        }
    }
    void setMatchCars()
    {
        for(Car car : carList)
        {
            if((car.getModel().equalsIgnoreCase(modelSearch) || modelSearch == null || modelSearch.isEmpty()) && (car.getMark().equalsIgnoreCase(markSearch) || markSearch == null || markSearch.isEmpty()) && (car.getColor().equalsIgnoreCase(colorSearch) || colorSearch == null || colorSearch.isEmpty()) && car.getBuyer_id() == -1)
            {

                matchCars.add(car);
            }
        }
    }
    public void searchDefault(List<String> stringList)
    {

        int matchNumber = 0;
        for(Car car : carList)
        {
            if(car.getBuyer_id() == -1) {
                for (String string : stringList) {
                    if (car.getMark().equalsIgnoreCase(string) || car.getColor().equalsIgnoreCase(string) || car.getModel().equalsIgnoreCase(string)) {
                        matchNumber++;
                    }
                }
                if (matchNumber >= stringList.size()) {
                    matchCars.add(car);
                }
                matchNumber = 0;
            }
        }

    }
    void setPage()
    {
        setDefault();
        if(matchCars.size() == 0)
        {
            return;
        }
        int current = 1;
        for(int i = page*3; i < i+3 && i < matchCars.size(); ++i)
        {
            if(current == 1)
            {
                mark1.setText(matchCars.get(i).getMark());
                model1.setText(matchCars.get(i).getModel());
                image1.setImage(matchCars.get(i).getImage());
                mark1.setVisible(true);
                model1.setVisible(true);
                info1.setVisible(true);
                if(mode != 2)
                    rent1.setVisible(true);
                modelLabel1.setVisible(true);
                markLabel1.setVisible(true);
                current++;
            }
            else if(current == 2)
            {
                mark2.setText(matchCars.get(i).getMark());
                model2.setText(matchCars.get(i).getModel());
                image2.setImage(matchCars.get(i).getImage());
                mark2.setVisible(true);
                model2.setVisible(true);
                info2.setVisible(true);
                if(mode != 2)
                    rent2.setVisible(true);
                modelLabel2.setVisible(true);
                markLabel2.setVisible(true);
                current++;
            }
            else if(current == 3)
            {
                mark3.setText(matchCars.get(i).getMark());
                model3.setText(matchCars.get(i).getModel());
                image3.setImage(matchCars.get(i).getImage());
                mark3.setVisible(true);
                model3.setVisible(true);
                info3.setVisible(true);
                if(mode != 2)
                    rent3.setVisible(true);
                modelLabel3.setVisible(true);
                markLabel3.setVisible(true);
                break;
            }
        }

    }

    @FXML
    void onBefore()
    {
        if(page > 0)
        {
            page --;
            setPage();
        }
    }
    public void setModelSearch(String modelSearch) {
        this.modelSearch = modelSearch;
    }

    public void setMarkSearch(String markSearch) {
        this.markSearch = markSearch;
    }

    public void setColorSearch(String colorSearch) {
        this.colorSearch = colorSearch;
    }

    @FXML
    void onInfo1(ActionEvent event)
    { //TODO lepszego if'a trzeba zrobic
        InfoController controller = new InfoController();

        if(3*page < matchCars.size()) {
            if(colorSearch != null)
                controller.setColorSearch(colorSearch);
            if(markSearch != null)
                controller.setMarkSearch(markSearch);
            if(modelSearch != null)
                controller.setModelSearch(modelSearch);
            if(stringListBufor != null)
                controller.setStringList(stringListBufor);

            controller.setMode(mode);
            ImageView imageView = new ImageView();
            imageView.setImage(matchCars.get(3*page).getImage());

            controller.setModelText(new Text(matchCars.get(3*page).getModel()));
            controller.setColorText(new Text(matchCars.get(3*page).getColor()));
            controller.setMarkText(new Text(matchCars.get(3*page).getMark()));
            controller.setCostText(new Text(String.valueOf(matchCars.get(3*page).getCost())));
            controller.setRentText(new Text(matchCars.get(3*page).getData_wyp()));
            controller.setImage(imageView);
            controller.setId(matchCars.get(3*page).getId());



        }
        controller.show(event, controller.path);
    }

    @FXML
    void onInfo2()
    {

    }

    @FXML
    void onInfo3()
    {

    }

    @FXML
    void onNext() {
        if(page < getMaxPage())
        {
            page ++;
            setPage();
        }

    }
    String getDate()
    {
        Instant instant = Instant.now();
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.of("Europe/Warsaw"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return dateTime.format(formatter);
    }

    @FXML
    void onRent1(ActionEvent event) {
        if(3*page < matchCars.size()) {
            Database database = new Database();
            database.rentCarUpdate(matchCars.get(3 * page), getDate());
            matchCars.get(3 * page).setBuyer_id(User.getInstance().getId());
            updateList(matchCars.get(3 * page));
            AppWindowController controller = new AppWindowController();
            controller.show(event, controller.path);
        }


    }
    void updateList(Car car)
    {

        for (Car value : carList) {
            if (value.getId() == car.getId()) {
                value.setBuyer_id(car.getBuyer_id());
                value.setData_wyp(getDate());
            }
        }
    }

    @FXML
    void onRent2(ActionEvent event) {
        if(3*page+1 < matchCars.size())
        {
            Database database = new Database();
            database.rentCarUpdate(matchCars.get(3*page+1), getDate());
            matchCars.get(3 * page + 1).setBuyer_id(User.getInstance().getId());
            updateList(matchCars.get(3 * page));
            AppWindowController controller = new AppWindowController();
            controller.show(event, controller.path);
        }

    }

    @FXML
    void onRent3(ActionEvent event) {
        if(3*page+2 < matchCars.size())
        {
            Database database = new Database();
            database.rentCarUpdate(matchCars.get(3*page+2), getDate());
            matchCars.get(3 * page + 2).setBuyer_id(User.getInstance().getId());
            updateList(matchCars.get(3 * page));
            AppWindowController controller = new AppWindowController();
            controller.show(event, controller.path);
        }
    }

    @FXML
    void onReturn(ActionEvent event)
    {
        AppWindowController controller = new AppWindowController();
        controller.show(event, controller.path);
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    @Override
    public void init(FXMLLoader loader)
    {
        carListController controller = loader.getController();
        controller.mode = mode;
        if(mode == 0) {
            controller.colorSearch = colorSearch;
            controller.modelSearch = modelSearch;
            controller.markSearch = markSearch;
            controller.setMatchCars();

        }
        if(mode == 1)
        {
            controller.stringListBufor = stringListBufor;
            if(controller.matchCars != null)
               controller.matchCars = matchCars;
        }
        if(mode == 2)
        {
            controller.serachByID();
            controller.rent1.setVisible(false);
            controller.rent2.setVisible(false);
            controller.rent3.setVisible(false);
        }
        controller.setPage();
    }
}
