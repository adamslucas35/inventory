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
import java.util.ResourceBundle;

/** This class controls elements, buttons, and text in the main-view.fxml file. */
public class MainController implements Initializable
{
    /**
     * Initiate fields for main-view tables.
     * */
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
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApplication.class.getResource("modifyPart-view.fxml"));
        loader.load();
        ModifyPartController mp_controller = loader.getController();
        mp_controller.receivePart(parts_table.getSelectionModel().getSelectedItem());
        Stage stage = (Stage)((Button)(actionEvent.getSource())).getScene().getWindow();
        Scene scene = new Scene(loader.getRoot(), 537, 546);
        stage.setScene(scene);
        stage.show();


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
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApplication.class.getResource("modifyProduct-view.fxml"));
        loader.load();
        ModifyProductController mpr_controller = loader.getController();
        mpr_controller.receiveProduct(products_table.getSelectionModel().getSelectedItem());
        Stage stage = (Stage)((Button)(actionEvent.getSource())).getScene().getWindow();
        Scene scene = new Scene(loader.getRoot(), 928, 548);
        stage.setScene(scene);
        stage.show();
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

    public void onKeyRelease_SearchParts(KeyEvent keyEvent)
    {
        String searchedPart = searchParts_textF.getText();
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

        parts_table.setItems(filteredParts);
    }
    public void onKeyRelease_SearchProducts(KeyEvent keyEvent)
    {
        String searchedProduct = searchProducts_textF.getText();
        ObservableList<Product> filteredProducts = Inventory.lookupProduct(searchedProduct);

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


        products_table.setItems(filteredProducts);
    }

    public void onDeletePartClick(ActionEvent actionEvent)
    {
        Inventory.deletePart(parts_table.getSelectionModel().getSelectedItem());
    }

    public void onDeleteProductClick(ActionEvent actionEvent)
    {
        Inventory.deleteProduct(products_table.getSelectionModel().getSelectedItem());
    }
}