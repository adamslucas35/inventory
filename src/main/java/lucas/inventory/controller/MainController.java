package lucas.inventory.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lucas.inventory.MainApplication;
import lucas.inventory.model.*;

import java.io.IOException;
import java.net.URL;
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
    public void onModifyProductClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("modifyProduct-view.fxml"));
        Stage stage = (Stage)((Button)(actionEvent.getSource())).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 928, 548);
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


    public void onDeletePartClick(ActionEvent actionEvent) {
    }

    public void onDeleteProductClick(ActionEvent actionEvent) {
    }
}