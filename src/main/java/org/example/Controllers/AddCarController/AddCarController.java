package org.example.Controllers.AddCarController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import org.example.Car.Car;
import org.example.Controllers.AbstractController;
import org.example.Controllers.AdminPanel.AdminPanelController;
import org.example.Database.Database;
import org.example.User.User;

import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;

import static org.example.App.carList;

public class AddCarController extends AbstractController {
    final public String path = "/fxml/AddCarView.fxml";

    @FXML
    private TextField costTextField;

    @FXML
    private TextField colorTextField;

    @FXML
    private Button imageChooseButton;

    @FXML
    private TextField markTextField;

    @FXML
    private TextField modelTextField;

    @FXML
    private Label warningLabel;

    private Blob imageView;

    @FXML
    void onReturn(ActionEvent event)
    {
        AdminPanelController controller = new AdminPanelController();
        controller.show(event, controller.path);
    }
    Image convertBlobToImage()
    {
        Image image = null;
        try {
            InputStream binaryStream = imageView.getBinaryStream();
            if (binaryStream != null) {
                byte[] imageBytes = binaryStream.readAllBytes();
                image = new Image(new ByteArrayInputStream(imageBytes));
                binaryStream.close();
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        return image;
    }
    @FXML
    void onSubmitButton()
    {
        if(modelTextField.getText().isEmpty())
        {
            warningLabel.setText("Model can't be empty");
        }
        else if (markTextField.getText().isEmpty())
        {
            warningLabel.setText("Mark can't be empty");
        }
        else if (colorTextField.getText().isEmpty())
        {
            warningLabel.setText("Color can't be empty");
        }
        else if(imageView == null)
        {
            warningLabel.setText("Image can't be empty");

        }
        else if(costTextField == null)
        {
            warningLabel.setText("Cost can't be empty");
        }
        try {
            Float.parseFloat(costTextField.getText());
        } catch (NumberFormatException e) {
            warningLabel.setText("Cost hast to be a number");
            return;
        }

            Database database = new Database();
           int id = database.addCar(modelTextField.getText(),markTextField.getText(),colorTextField.getText(),imageView,User.getInstance().getId(), Integer.parseInt(costTextField.getText()),"0");
            Image image = convertBlobToImage();
            if(image == null)
            {
                System.out.println("Bad format");
                warningLabel.setText("Something went wrong with your image conversion");
                return;
            }
            Car car = new Car(colorTextField.getText(),modelTextField.getText(),markTextField.getText(),Float.parseFloat(costTextField.getText()),"0",User.getInstance().getId(),-1,image, id);
            carList.add(car);


    }

    private void pickImage() {


        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");

        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.gif");
        fileChooser.getExtensionFilters().add(filter);


        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            try (InputStream is = new FileInputStream(file);
                 ByteArrayOutputStream os = new ByteArrayOutputStream()) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) != -1) {
                    os.write(buffer, 0, length);
                }
                byte[] bytes = os.toByteArray();
                imageView = new javax.sql.rowset.serial.SerialBlob(bytes);
            } catch (IOException | SQLException ex) {
                ex.printStackTrace();
            }
        }

    }
    private void initializeButton()
    {
        imageChooseButton.setOnAction(e -> pickImage());
        imageView = null;
    }
    @Override
    public void init(FXMLLoader loader)
    {
        AddCarController controller = loader.getController();
        controller.initializeButton();

    }
}
