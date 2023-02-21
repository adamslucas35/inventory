package lucas.inventory.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import lucas.inventory.MainApplication;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** This class controls elements, buttons, and text in the modifyPart-view.fxml file*/
public class ModifyPartController implements Initializable {
    // Initializer to make sure code is being run through the console
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("... modifyPart-view has been initialized ...");
    }

    //Fields
    @FXML
    private Label mp_changableText;
    @FXML
    private Button mp_cancelBtn;
    @FXML
    private RadioButton mp_inHouse_rbtn;
    @FXML
    private RadioButton mp_outsource_rbtn;


    /** This method exits pane when cancel button is clicked.
     * This is the method that gets called when the cancel button is clicked on in the modify part pane.
     * @param actionEvent when cancel button is clicked
     * @throws IOException incase of input/output error*/
    @FXML
    public void mp_onCancelClick(ActionEvent actionEvent) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 927, 366);
        stage.setScene(scene);
        stage.show();
    }

    /** Radio button to control which text is shown.
     * When In-House is selected, display "Machine ID".
     * @param actionEvent when button is clicked*/
    public void mp_onInHouseSelect(ActionEvent actionEvent)
    {
        mp_changableText.setText("Machine ID");
    }

    /** Radio button to control which text is shown.
     * When Outsource is selected, display "Company Name".
     * @param actionEvent when button is clicked*/
    public void mp_onOutsourceSelect(ActionEvent actionEvent)
    {
        mp_changableText.setText("Company Name");

    }
}
