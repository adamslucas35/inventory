package lucas.inventory.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lucas.inventory.MainApplication;
import lucas.inventory.model.InHouse;
import lucas.inventory.model.InvalidValuesException;
import lucas.inventory.model.Inventory;
import lucas.inventory.model.OutSourced;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** This class controls elements, buttons, and text in the addPart-view.fxml file*/
public class AddPartController implements Initializable
{
    @FXML
    private TextField ap_Id_textF;
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
    private RadioButton ap_inHouse_rbtn;
    @FXML
    private RadioButton ap_outsource_rbtn;


    /** Initializes program.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        System.out.println("... addPart-view has been initialized ...");
        ap_Id_textF.setEditable(false);
        ap_Id_textF.setDisable(true);

    }



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
     * Saves all data in input fields to new part when clicked.
     * @param actionEvent when save button is clicked.
     * @throws IOException in case of input error
     */
    public void ap_onSaveClick(ActionEvent actionEvent) throws IOException
    {
        try {
            String partName = ap_ihName_textF.getText();
            double partPrice = Double.parseDouble(ap_ihPrice_textF.getText());
            int partStock = Integer.parseInt(ap_ihInv_testF.getText());
            int partMin = Integer.parseInt(ap_ihMin_textF.getText());
            int partMax = Integer.parseInt(ap_ihMax_textF.getText());
            if (partMin >= partMax) {
                throw new InvalidValuesException(partStock, partMin, partMax);
            } else if (partStock < partMin || partStock > partMax) {
                throw new InvalidValuesException(partStock, partMin, partMax);
            } else if (ap_inHouse_rbtn.isSelected()) {
                int partMachineId = Integer.parseInt(ap_change_textF.getText());
                Inventory.addPart(new InHouse(MainApplication.generatePartsID(), partName, partPrice, partStock, partMin, partMax, partMachineId));
            } else if (ap_outsource_rbtn.isSelected()) {
                String partCompanyName = ap_change_textF.getText();
                Inventory.addPart(new OutSourced(MainApplication.generatePartsID(), partName, partPrice, partStock, partMin, partMax, partCompanyName));
            }
            MainApplication.returnToMain(actionEvent);

        }
        catch (NumberFormatException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Data");
            alert.setContentText("Please check that all data has been entered correctly");
            alert.showAndWait();

//            error_lbl.setText("Please check that all data has been entered correctly.");
//            System.out.println("Exception: " + e.getMessage());
        }
        catch (InvalidValuesException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Values");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }



    }
}
