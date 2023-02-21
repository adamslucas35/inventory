package lucas.inventory.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lucas.inventory.MainApplication;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** This class controls elements, buttons, and text in the main-view.fxml file*/
public class MainController implements Initializable
{
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        System.out.println("... main-view has been initialized ...");
    }
    /**Method to go to add part pane.
     * Changes stage to view and add parts.
     * @param actionEvent when button is clicked
     * @throws IOException incase of input/output error*/
    @FXML
    public void onAddPartClick(ActionEvent actionEvent) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("addPart-view.fxml"));
        Stage stage = (Stage)((Button)(actionEvent.getSource())).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 537, 546);
        stage.setScene(scene);
        stage.show();
    }

    /**Method to go to modify part pane.
     * Changes stage to view and modify parts.
     * @param actionEvent when button is clicked
     * @throws IOException incase of input/output error*/
    public void onModifyPartClick(ActionEvent actionEvent) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("modifyPart-view.fxml"));
        Stage stage = (Stage)((Button)(actionEvent.getSource())).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 537, 546);
        stage.setScene(scene);
        stage.show();
    }

    /**Method to go to add product pane.
     * Changes stage to view and add products.
     * @param actionEvent when button is clicked
     * @throws IOException incase of input/output error*/
    public void onAddProductClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("addProduct-view.fxml"));
        Stage stage = (Stage)((Button)(actionEvent.getSource())).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 928, 548);
        stage.setScene(scene);
        stage.show();
    }
    /**Method to go to modify product pane.
     * Changes stage to view and modify products.
     * @param actionEvent when button is clicked
     * @throws IOException incase of input/output error*/
    public void onModifyProductClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("modifyProduct-view.fxml"));
        Stage stage = (Stage)((Button)(actionEvent.getSource())).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 928, 548);
        stage.setScene(scene);
        stage.show();
    }


    /**Method to exit application.
     * When button is clicked, the application will close.
     * @param actionEvent execute when clicked*/
    public void onExitClick(ActionEvent actionEvent)
    {
        final Button source = (Button) actionEvent.getSource();
        final Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
}