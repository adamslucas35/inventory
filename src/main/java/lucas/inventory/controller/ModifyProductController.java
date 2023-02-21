package lucas.inventory.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import lucas.inventory.MainApplication;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/** This class controls elements, buttons, and text in the modifyProduct-view.fxml file*/
public class ModifyProductController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("...  modifyProduct-view has been initialized ...");
    }
    /** This method exits pane when cancel button is clicked.
     * This is the method that gets called when the cancel button is clicked on in the modify product pane.
     * @param actionEvent when cancel button is clicked
     * @throws IOException incase of input/output error*/
    public void mpr_onCancelClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 927, 366);
        stage.setScene(scene);
        stage.show();
    }
}
