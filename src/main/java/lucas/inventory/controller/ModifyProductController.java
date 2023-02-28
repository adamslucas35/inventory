package lucas.inventory.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lucas.inventory.MainApplication;
import lucas.inventory.model.Inventory;
import lucas.inventory.model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/** This class controls elements, buttons, and text in the modifyProduct-view.fxml file*/
public class ModifyProductController implements Initializable
{
    @FXML
    private TextField mpr_Id_textF;
    @FXML
    private TextField mpr_Name_textF;
    @FXML
    private TextField mpr_Inv_textF;
    @FXML
    private TextField mpr_Price_textF;
    @FXML
    private TextField mpr_Max_textF;
    @FXML
    private TextField mpr_Min_textF;
    @FXML
    private TextField mpr_partsSearch;
    @FXML
    private TableView mpr_partsTable_textF;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        System.out.println("...  modifyProduct-view has been initialized ...");
        mpr_Id_textF.setEditable(false);
        mpr_Id_textF.setDisable(true);

    }
    /** This method exits pane when cancel button is clicked.
     * This is the method that gets called when the cancel button is clicked on in the modify product pane.
     * @param actionEvent when cancel button is clicked
     * @throws IOException in case of input/output error*/
    public void mpr_onCancelClick(ActionEvent actionEvent) throws IOException
    {
        MainApplication.returnToMain(actionEvent);
    }

    public void mpr_onSaveBtnClick(ActionEvent actionEvent) throws IOException
    {
        int id = Integer.parseInt(mpr_Id_textF.getText());
        String name = mpr_Name_textF.getText();
        int stock = Integer.parseInt(mpr_Inv_textF.getText());
        double price = Double.parseDouble(mpr_Price_textF.getText());
        int max = Integer.parseInt(mpr_Max_textF.getText());
        int min = Integer.parseInt(mpr_Min_textF.getText());
        Inventory.updateProduct(id, new Product(id, name, price, stock, min, max));



        MainApplication.returnToMain(actionEvent);

    }


    public void receiveProduct(Product selectedProduct)
    {
        mpr_Id_textF.setText(String.valueOf(selectedProduct.getId()));
        mpr_Name_textF.setText(selectedProduct.getName());
        mpr_Inv_textF.setText(String.valueOf(selectedProduct.getStock()));
        mpr_Price_textF.setText(String.valueOf(selectedProduct.getPrice()));
        mpr_Max_textF.setText(String.valueOf(selectedProduct.getMax()));
        mpr_Min_textF.setText(String.valueOf(selectedProduct.getMin()));


    }
}
