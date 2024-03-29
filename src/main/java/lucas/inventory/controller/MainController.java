package lucas.inventory.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lucas.inventory.MainApplication;
import lucas.inventory.model.*;

import java.io.IOException;
import java.net.URL;
import java.security.ProtectionDomain;
import java.text.NumberFormat;
import java.util.Optional;
import java.util.ResourceBundle;

/** This class controls elements, buttons, and text in the main-view.fxml file.
 * <b>FUTURE ENHANCEMENT: I feel the ability to modify parts and products through double clicking would enhance
 * the functionality of this program. Multiple times while testing my code I would double click a product to open
 * it up and of course nothing would happen. The buttons are nice to have, but a simple way to open and modify parts
 * and products would be nice.</b>*/
public class MainController implements Initializable
{
    @FXML
    private TableView<Part> parts_table;
    @FXML
    private TableView<Product> products_table;
    @FXML
    private TableColumn<Part, Integer> p_partId_col;
    @FXML
    private TableColumn<Part, String> p_partName_col;
    @FXML
    private TableColumn<Part, Integer> p_invLevel_col;
    @FXML
    private TableColumn<Part, Double> p_price_col;
    @FXML
    private TableColumn<Product, Integer> pr_productId_col;
    @FXML
    private TableColumn<Product, String> pr_productName_col;
    @FXML
    private TableColumn<Product, Integer> pr_invLevel_col;
    @FXML
    private TableColumn<Product, Double> pr_price_col;
    @FXML
    private TextField searchParts_textF;
    @FXML
    private TextField searchProducts_textF;

    /**
     * Initialized program.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        System.out.println("... main-view has been initialized ...");

        parts_table.setItems(Inventory.getAllParts());

        p_partId_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        p_partName_col.setCellValueFactory(new PropertyValueFactory<>("name"));
        p_invLevel_col.setCellValueFactory(new PropertyValueFactory<>("stock"));
        p_price_col.setCellValueFactory(new PropertyValueFactory<>("price"));

        products_table.setItems(Inventory.getAllProducts());
        pr_productId_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        pr_productName_col.setCellValueFactory(new PropertyValueFactory<>("name"));
        pr_invLevel_col.setCellValueFactory(new PropertyValueFactory<>("stock"));
        pr_price_col.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /**Method to go to add part pane.
     * Changes stage to view and add parts.
     * @param actionEvent when button is clicked
     * @throws IOException in case of input/output error*/
    public void onAddPartClick(ActionEvent actionEvent) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("addPart-view.fxml"));
        Stage stage = (Stage)((Button)(actionEvent.getSource())).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 537, 546);
        stage.setScene(scene);
        stage.show();
    }

    /**Method to go to modify part pane.
     * Changes stage to view and modify parts.
     * @param actionEvent when button is clicked
     * @throws IOException in case of input/output error*/
    public void onModifyPartClick(ActionEvent actionEvent) throws IOException
    {
        Part selectedPart = parts_table.getSelectionModel().getSelectedItem();
        if (selectedPart == null)
        {
            Alert warning = new Alert(Alert.AlertType.WARNING);
            warning.setTitle("Not found");
            warning.setContentText("Nothing was selected.");
            warning.showAndWait();
        } else {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("modifyPart-view.fxml"));
            loader.load();
            ModifyPartController mp_controller = loader.getController();
            mp_controller.receivePart(parts_table.getSelectionModel().getSelectedItem());
            Stage stage = (Stage) ((Button) (actionEvent.getSource())).getScene().getWindow();
            Scene scene = new Scene(loader.getRoot(), 537, 546);
            stage.setScene(scene);
            stage.show();
        }


    }

    /**Method to go to add product pane.
     * Changes stage to view and add products.
     * @param actionEvent when button is clicked
     * @throws IOException in case of input/output error*/
    public void onAddProductClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("addProduct-view.fxml"));
        Stage stage = (Stage)((Button)(actionEvent.getSource())).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 928, 548);
        stage.setScene(scene);
        stage.show();
    }
    /**Method to go to modify product pane.
     * Changes stage to view and modify products.
     * @param actionEvent when button is clicked
     * @throws IOException in case of input/output error*/
    public void onModifyProductClick(ActionEvent actionEvent) throws IOException
    {
        Product selcetedProduct = products_table.getSelectionModel().getSelectedItem();
        if (selcetedProduct == null)
        {
            Alert warning = new Alert(Alert.AlertType.WARNING);
            warning.setTitle("Not found");
            warning.setContentText("Nothing was selected.");
            warning.showAndWait();
        } else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApplication.class.getResource("modifyProduct-view.fxml"));
            loader.load();
            ModifyProductController mpr_controller = loader.getController();
            mpr_controller.receiveProduct(products_table.getSelectionModel().getSelectedItem());
            Stage stage = (Stage) ((Button) (actionEvent.getSource())).getScene().getWindow();
            Scene scene = new Scene(loader.getRoot(), 928, 548);
            stage.setScene(scene);
            stage.show();
        }
    }

    /**Method to exit application.
     * When button is clicked, the application will close.
     * @param actionEvent execute when clicked*/
    public void onExitClick(ActionEvent actionEvent)
    {
        final Button source = (Button) actionEvent.getSource();
        final Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    /**
     * Searches for parts in table by name or id.
     * @param keyEvent when key is released
     */
    public void onKeyRelease_SearchParts(KeyEvent keyEvent)
    {
        String searchedPart = searchParts_textF.getText();
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
        parts_table.setItems(filteredParts);
    }
    /**
     * Searches for products in table by name or id.
     * @param keyEvent when key is released
     */
    public void onKeyRelease_SearchProducts(KeyEvent keyEvent)
    {
        String searchedProduct = searchProducts_textF.getText();
        ObservableList<Product> filteredProducts = Inventory.lookupProduct(searchedProduct);
        Alert warning = new Alert(Alert.AlertType.WARNING);
        warning.setTitle("Not found");
        warning.setContentText("No items have not been found");

        if(filteredProducts.isEmpty())
        {
            try
            {
                int id_searchedProduct = Integer.parseInt(searchedProduct);
                Product product = Inventory.lookupProduct(id_searchedProduct);
                if (product != null)
                    filteredProducts.add(product);
            }
            catch (NumberFormatException e)
            {/*ignore*/}
        }

        if (filteredProducts.isEmpty())
        {
            warning.showAndWait();
        }


        products_table.setItems(filteredProducts);
    }

    /**
     * Removes selected part from parts table.
     * @param actionEvent when delete button is clicked.
     */
    public void onDeletePartClick(ActionEvent actionEvent)
    {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you would like to remove this part from the table?");
        Optional<ButtonType> choice = confirm.showAndWait();
        confirm.setTitle("Unable to Delete");
        if (choice.isPresent() && choice.get() == ButtonType.OK)
            Inventory.deletePart(parts_table.getSelectionModel().getSelectedItem());
    }

    /**
     * Removes selected product from product table.
     * Each associated part must be removed as well.
     * @param actionEvent when delete button is clicked.
     */
    public void onDeleteProductClick(ActionEvent actionEvent)
    {
        Product deletableProduct = products_table.getSelectionModel().getSelectedItem();
        if (deletableProduct.getAllAssociatedParts().size() > 0)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Unable to delete");
            alert.setContentText("Product contains an associated part.\nPlease remove all associated parts before deleting.");
            alert.showAndWait();
        }
        else
        {
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you would like to remove this product from the table?");
            Optional<ButtonType> choice = confirm.showAndWait();

            if (choice.isPresent() && choice.get() == ButtonType.OK)
            {
                Inventory.deleteProduct(deletableProduct);
            }

        }
    }
}