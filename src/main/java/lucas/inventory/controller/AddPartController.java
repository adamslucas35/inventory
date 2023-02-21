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

/** This class controls elements, buttons, and text in the addPart-view.fxml file*/
public class AddPartController implements Initializable {
// Initializer to make sure code is being run through the console
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("... addPart-view has been initialized ...");
    }

    //Fields
    @FXML
    private Label ap_changableText;
    @FXML
    private Button ap_cancelBtn;
    @FXML
    private RadioButton ap_inHouse_rbtn;
    @FXML
    private RadioButton ap_outsource_rbtn;




    /** This method exits pane when cancel button is clicked.
     * This is the method that gets called when the cancel button is clicked on in the add part pane.
     * @param actionEvent when cancel button is clicked
     * @throws IOException incase of input/output error*/
    @FXML
    public void ap_onCancelClick(ActionEvent actionEvent) throws IOException
    {

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 927, 366);
        stage.setScene(scene);
        stage.show();
    }

    /** Radio button to control which text is shown.
     * When Outsource is selected, display "Machine ID".
     * @param actionEvent when button is clicked*/
    public void ap_onInHouseSelect(ActionEvent actionEvent)
    {
        ap_changableText.setText("Machine ID");
    }

    /** Radio button to control which text is shown.
     * When Outsource is selected, display "Company Name".
     * @param actionEvent when button is clicked*/
    public void ap_onOutsourceSelect(ActionEvent actionEvent)
    {
        ap_changableText.setText("Company Name");

    }
}
