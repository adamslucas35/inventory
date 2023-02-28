package lucas.inventory.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lucas.inventory.MainApplication;
import lucas.inventory.model.Inventory;
import lucas.inventory.model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** This class controls elements, buttons, and text in the addProduct-view.fxml file*/
public class AddProductController implements Initializable {
    @FXML
    private TextField pr_id_textF;
    @FXML
    private TextField pr_name_textF;
    @FXML
    private TextField pr_inv_textF;
    @FXML
    private TextField pr_price_textF;
    @FXML
    private TextField pr_max_textF;
    @FXML
    private TextField pr_min_textF;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("... addProduct-view has been initialized ...");
        pr_id_textF.setEditable(false);
        pr_id_textF.setDisable(true);
    }

    /** This method exits pane when cancel button is clicked.
     * This is the method that gets called when the cancel button is clicked on in the add product pane.
     * @param actionEvent when cancel button is clicked
     * @throws IOException in case of input/output error*/
    public void pr_onCancelClick(ActionEvent actionEvent) throws IOException
    {
        MainApplication.returnToMain(actionEvent);
    }

    /**
     * When save button clicked, create new product based on data entered.
     * */
    public void pr_onSaveClick(ActionEvent actionEvent) throws IOException
    {
        String productName = pr_name_textF.getText();
        double productPrice = Double.parseDouble(pr_price_textF.getText());
        int productStock = Integer.parseInt(pr_inv_textF.getText());
        int productMin = Integer.parseInt(pr_min_textF.getText());
        int productMax = Integer.parseInt(pr_max_textF.getText());

        Inventory.addProduct(new Product(MainApplication.generateProductsID(), productName, productPrice, productStock, productMin, productMax));


        MainApplication.returnToMain(actionEvent);

    }





}
