package lucas.inventory.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lucas.inventory.MainApplication;
import lucas.inventory.model.InvalidValuesException;
import lucas.inventory.model.Inventory;
import lucas.inventory.model.Part;
import lucas.inventory.model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
    @FXML
    private TableView<Part> apr_partsTable;
    @FXML
    private TableColumn<Part, Integer> apr_p_partId_col;
    @FXML
    private TableColumn<Part, String> apr_p_partName_col;
    @FXML
    private TableColumn<Part, Integer> apr_p_invLevel_col;
    @FXML
    private TableColumn<Part, Integer> apr_p_price_col;
    @FXML
    private TextField apr_partsSearch_textF;
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

    private final ObservableList<Part> assocPartsList = FXCollections.observableArrayList();

    /**
     * Initializes program.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("... addProduct-view has been initialized ...");
        pr_id_textF.setEditable(false);
        pr_id_textF.setDisable(true);

        apr_partsTable.setItems(Inventory.getAllParts());

        apr_p_partId_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        apr_p_partName_col.setCellValueFactory(new PropertyValueFactory<>("name"));
        apr_p_invLevel_col.setCellValueFactory(new PropertyValueFactory<>("stock"));
        apr_p_price_col.setCellValueFactory(new PropertyValueFactory<>("price"));

        associatedPartsTable.setItems(assocPartsList);
        asp_pId_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        asp_pName_col.setCellValueFactory(new PropertyValueFactory<>("name"));
        asp_pInv_col.setCellValueFactory(new PropertyValueFactory<>("stock"));
        asp_pPrice_col.setCellValueFactory(new PropertyValueFactory<>("price"));

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
     * Save data input when save button is clicked.
     * @param actionEvent when save button is clicked.
     * @throws IOException in case of invalid data input
     */
    public void pr_onSaveClick(ActionEvent actionEvent) throws IOException
    {
        try
        {
        String productName = pr_name_textF.getText();
        double productPrice = Double.parseDouble(pr_price_textF.getText());
        int productStock = Integer.parseInt(pr_inv_textF.getText());
        int productMin = Integer.parseInt(pr_min_textF.getText());
        int productMax = Integer.parseInt(pr_max_textF.getText());

        if (productMin >= productMax)
            throw new InvalidValuesException(productStock, productMin, productMax);
        if (productStock > productMax || productStock < productMin)
            throw new InvalidValuesException(productStock, productMin, productMax);
        else {
            Product newProduct = new Product(MainApplication.generateProductsID(), productName, productPrice, productStock, productMin, productMax);
            for (Part part : assocPartsList)
                newProduct.addAssociatedPart(part);
            Inventory.addProduct(newProduct);
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

    /**
     * Searches through table each time key is released.
     * Searching by name or id
     * @param keyEvent when key is released
     */
    public void apr_onKeyRelease_SearchParts(KeyEvent keyEvent)
    {
        String searchedPart = apr_partsSearch_textF.getText();
        ObservableList<Part> filteredParts = Inventory.lookupPart(searchedPart);
        Alert warning = new Alert(Alert.AlertType.WARNING);
        warning.setTitle("Not found");
        warning.setContentText("No items have not been found");

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

        if (filteredParts.isEmpty())
        {
            warning.showAndWait();
        }
        apr_partsTable.setItems(filteredParts);
    }

    /**
     * Adds part from parts table to associatedparts table.
     * @param actionEvent when add button is clicked.
     */
    public void apr_onAddClick(ActionEvent actionEvent)
    {
        Part selectedPart;
        selectedPart = apr_partsTable.getSelectionModel().getSelectedItem();

        if (selectedPart != null)
            assocPartsList.add(selectedPart);

    }

    /**
     * Removes part from associated parts table.
     * @param actionEvent when remove button is clicked.
     */
    public void apr_onRemoveClick(ActionEvent actionEvent)
    {
        Alert warning = new Alert(Alert.AlertType.WARNING);
        warning.setTitle("Not found");
        warning.setContentText("Nothing was selected and nothing was deleted.");
        Part selectedPart = associatedPartsTable.getSelectionModel().getSelectedItem();


        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you would like to remove this part from the table?");
        Optional<ButtonType> choice = confirm.showAndWait();

        if (selectedPart == null) {
            warning.showAndWait();
        }
        else if (choice.isPresent() && choice.get() == ButtonType.OK )
        {
            assocPartsList.remove(selectedPart);
        }

    }


}
