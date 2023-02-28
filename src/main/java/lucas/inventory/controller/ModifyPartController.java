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
import lucas.inventory.model.Part;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** This class controls elements, buttons, and text in the modifyPart-view.fxml file*/
public class ModifyPartController implements Initializable {

    //Fields
    @FXML
    private Label mp_changable_lbl;
    @FXML
    private TextField mp_id_textF;
    @FXML
    private TextField mp_name_textF;
    @FXML
    private TextField mp_inv_textF;
    @FXML
    private TextField mp_price_textF;
    @FXML
    private TextField mp_max_textF;
    @FXML
    private TextField mp_min_textF;
    @FXML
    private TextField mp_change_textF;
    @FXML
    private Button mp_cancelBtn;
    @FXML
    private RadioButton mp_inHouse_rbtn;
    @FXML
    private RadioButton mp_outsource_rbtn;


    // Initializer to make sure code is being run through the console
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("... modifyPart-view has been initialized ...");
        mp_id_textF.setDisable(true);
        mp_id_textF.setEditable(false);

    }




    /** This method exits pane when cancel button is clicked.
     * This is the method that gets called when the cancel button is clicked on in the modify part pane.
     * @param actionEvent when cancel button is clicked
     * @throws IOException in case of input/output error*/
    @FXML
    public void mp_onCancelClick(ActionEvent actionEvent) throws IOException
    {
        MainApplication.returnToMain(actionEvent);
    }

    /** Radio button to control which text is shown.
     * When In-House is selected, display "Machine ID".
     * @param actionEvent when button is clicked*/
    public void mp_onInHouseSelect(ActionEvent actionEvent)
    {
        mp_changable_lbl.setText("Machine ID");
    }

    /** Radio button to control which text is shown.
     * When Outsource is selected, display "Company Name".
     * @param actionEvent when button is clicked*/
    public void mp_onOutsourceSelect(ActionEvent actionEvent)
    {
        mp_changable_lbl.setText("Company Name");

    }

    public void receivePart(Part selectedPart)
    {
        mp_id_textF.setText(String.valueOf(selectedPart.getId()));
        mp_name_textF.setText(selectedPart.getName());
        mp_inv_textF.setText(String.valueOf(selectedPart.getStock()));
        mp_price_textF.setText(String.valueOf(selectedPart.getPrice()));
        mp_max_textF.setText(String.valueOf(selectedPart.getMax()));
        mp_min_textF.setText(String.valueOf(selectedPart.getMin()));
        if (selectedPart instanceof InHouse)
        {
            mp_inHouse_rbtn.fire();
            mp_inHouse_rbtn.setSelected(true);
            mp_change_textF.setText(String.valueOf(((InHouse)selectedPart).getMachineId()));
        } else if (selectedPart instanceof OutSourced) {
            mp_outsource_rbtn.fire();
            mp_outsource_rbtn.setSelected(true);
            mp_change_textF.setText(((OutSourced)selectedPart).getCompanyName());
        }

    }


}
