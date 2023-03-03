package lucas.inventory.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lucas.inventory.MainApplication;
import lucas.inventory.model.InvalidValuesException;
import lucas.inventory.model.Inventory;
import lucas.inventory.model.Part;
import lucas.inventory.model.Product;

import java.io.IOException;
import java.net.URL;
import java.security.cert.CertificateNotYetValidException;
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
    private TextField mpr_partsSearch_textF;

    @FXML
    private TableView<Part> mpr_partsTable;
    @FXML
    private TableColumn<Part, Integer> mpr_p_partId_col;
    @FXML
    private TableColumn<Part, String> mpr_p_partName_col;
    @FXML
    private TableColumn<Part, Integer> mpr_p_invLevel_col;
    @FXML
    private TableColumn<Part, Integer> mpr_p_price_col;

    @FXML
    private TableView<Part> associatedPartsTable;
    @FXML
    private TableColumn<Part, Integer> asp_pId_col;
    @FXML
    private TableColumn<Part, String> asp_pName_col;
    @FXML
    private TableColumn<Part, Integer> asp_pInv_col;
    @FXML
    private TableColumn<Part, Integer> asp_pPrice_col;
    @FXML
    private ObservableList<Part> assocPartsList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        System.out.println("...  modifyProduct-view has been initialized ...");
        mpr_Id_textF.setEditable(false);
        mpr_Id_textF.setDisable(true);

        mpr_partsTable.setItems(Inventory.getAllParts());



        mpr_p_partId_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        mpr_p_partName_col.setCellValueFactory(new PropertyValueFactory<>("name"));
        mpr_p_invLevel_col.setCellValueFactory(new PropertyValueFactory<>("stock"));
        mpr_p_price_col.setCellValueFactory(new PropertyValueFactory<>("price"));


        asp_pId_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        asp_pName_col.setCellValueFactory(new PropertyValueFactory<>("name"));
        asp_pInv_col.setCellValueFactory(new PropertyValueFactory<>("stock"));
        asp_pPrice_col.setCellValueFactory(new PropertyValueFactory<>("price"));
        



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
        try
        {
        int id = Integer.parseInt(mpr_Id_textF.getText());
        String name = mpr_Name_textF.getText();
        int productStock = Integer.parseInt(mpr_Inv_textF.getText());
        double price = Double.parseDouble(mpr_Price_textF.getText());
        int productMax = Integer.parseInt(mpr_Max_textF.getText());
        int productMin = Integer.parseInt(mpr_Min_textF.getText());

        Product newProduct = new Product(id, name, price, productStock, productMin, productMax);
        for (Part part : assocPartsList)
        {
            newProduct.addAssociatedPart(part);
        }
        Inventory.updateProduct(id, newProduct);
        if (productMin >= productMax)
            throw new InvalidValuesException(productStock, productMin, productMax);
        if (productStock > productMax || productStock < productMin)
            throw new InvalidValuesException(productStock, productMin, productMax);

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


    public void receiveProduct(Product selectedProduct)
    {
        mpr_Id_textF.setText(String.valueOf(selectedProduct.getId()));
        mpr_Name_textF.setText(selectedProduct.getName());
        mpr_Inv_textF.setText(String.valueOf(selectedProduct.getStock()));
        mpr_Price_textF.setText(String.valueOf(selectedProduct.getPrice()));
        mpr_Max_textF.setText(String.valueOf(selectedProduct.getMax()));
        mpr_Min_textF.setText(String.valueOf(selectedProduct.getMin()));

        assocPartsList = selectedProduct.getAllAssociatedParts();

        associatedPartsTable.setItems(assocPartsList);
    }

    public void mpr_onKeyRelease_SearchParts(KeyEvent keyEvent)
    {
        String searchedPart = mpr_partsSearch_textF.getText();
        ObservableList<Part> filteredParts = Inventory.lookupPart(searchedPart);

        if (filteredParts.isEmpty()) {
            try {
                int id_searchedPart = Integer.parseInt(searchedPart);
                Part part = Inventory.lookupPart(id_searchedPart);
                if (part != null)
                    filteredParts.add(part);
            }
            catch (NumberFormatException e)
            {/*ignore*/}
        }

        mpr_partsTable.setItems(filteredParts);
    }
    public void mpr_onAddClick(ActionEvent actionEvent)
    {

        Part newProduct;
        newProduct = mpr_partsTable.getSelectionModel().getSelectedItem();

        if (newProduct != null)
            assocPartsList.add(newProduct);

    }

    public void mpr_onRemoveClick(ActionEvent actionEvent)
    {
        Part selectedPart = associatedPartsTable.getSelectionModel().getSelectedItem();
        assocPartsList.remove(selectedPart);
    }
}
