package lucas.inventory.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lucas.inventory.MainApplication;
import lucas.inventory.model.InHouse;
import lucas.inventory.model.Inventory;
import lucas.inventory.model.OutSourced;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** This class controls elements, buttons, and text in the addPart-view.fxml file*/
public class AddPartController implements Initializable {

    /** Initializer to make sure code is being run through the console.
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        System.out.println("... addPart-view has been initialized ...");
    }
    @FXML
    private TextField ap_ihID_textF;
    @FXML
    private TextField ap_ihName_textF;
    @FXML
    private TextField ap_ihInv_testF;
    @FXML
    private TextField ap_ihPrice_textF;
    @FXML
    private TextField ap_ihMax_textF;
    @FXML
    private TextField ap_ihMin_textF;
    @FXML
    private Button ap_ih_save_btn;
    @FXML
    private TextField ap_change_textF;
    @FXML
    private Label ap_changable_lbl;
    @FXML
    private Button ap_cancelBtn;
    @FXML
    private RadioButton ap_inHouse_rbtn;
    @FXML
    private RadioButton ap_outsource_rbtn;




    /** This method exits pane when cancel button is clicked.
     * This is the method that gets called when the cancel button is clicked on in the add part pane.
     * @param actionEvent when cancel button is clicked
     * @throws IOException in case of input/output error*/
    @FXML
    public void ap_onCancelClick(ActionEvent actionEvent) throws IOException
    {
        MainApplication.returnToMain(actionEvent);
    }

    /** Radio button to control which text is shown.
     * When Outsource is selected, display "Machine ID".
     * @param actionEvent when button is clicked*/
    public void ap_onInHouseSelect(ActionEvent actionEvent)
    {
        ap_changable_lbl.setText("Machine ID");
    }

    /** Radio button to control which text is shown.
     * When Outsource is selected, display "Company Name".
     * @param actionEvent when button is clicked*/
    public void ap_onOutsourceSelect(ActionEvent actionEvent)
    {
        ap_changable_lbl.setText("Company Name");

    }

    /**
     * Create product based on input in text fields.
     * @param actionEvent runs code when save button is clicked*/
    public void ap_onSaveClick(ActionEvent actionEvent) throws IOException {
        int partId = Integer.parseInt(ap_ihID_textF.getText());
        String partName = ap_ihName_textF.getText();
        double partPrice = Double.parseDouble(ap_ihPrice_textF.getText());
        int partStock = Integer.parseInt(ap_ihInv_testF.getText());
        int partMin = Integer.parseInt(ap_ihMin_textF.getText());
        int partMax = Integer.parseInt(ap_ihMax_textF.getText());

        if (ap_inHouse_rbtn.isSelected())
        {
            int partMachineId = Integer.parseInt(ap_change_textF.getText());
            Inventory.addPart(new InHouse(partId, partName, partPrice, partStock, partMin, partMax, partMachineId));
            
        } else if (ap_outsource_rbtn.isSelected()) {
            String partCompanyName = ap_change_textF.getText();
            Inventory.addPart(new OutSourced(partId, partName, partPrice, partStock, partMin, partMax, partCompanyName));

        }



        MainApplication.returnToMain(actionEvent);
    }
}
