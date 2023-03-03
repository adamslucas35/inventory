package lucas.inventory;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import lucas.inventory.model.InHouse;
import lucas.inventory.model.Inventory;
import lucas.inventory.model.OutSourced;
import lucas.inventory.model.Product;
import lucas.inventory.model.Part;

import java.io.IOException;
import java.util.Optional;

import static lucas.inventory.model.Inventory.getAllParts;
import static lucas.inventory.model.Inventory.getAllProducts;

/** This class creates an applications that runs an inventory management system*/
public class MainApplication extends Application {
    /**
     * Creates first scene and opens application.
     * Application is displayed in new window.
     * @param stage new stage is created
     * @throws IOException in case of input/output error
     * */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 927, 366);
        stage.setTitle("");
        stage.setScene(scene);
        stage.show();
    }

    public static void returnToMain(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 927, 366);
        stage.setScene(scene);
        stage.show();
    }

    public static int generatePartsID()
    {
        int index;
        for (index = 0; index <= getAllParts().size();)
            index++;
        return index;
    }
    public static int generateProductsID() {
//        int index = 0;
        int index;
        for (index = 0; index <= getAllProducts().size(); )
            index++;
        return index;
    }


    /**Method to launch application.
     * @param args accepts all arguments*/
    public static void main(String[] args) {

        launch();
    }
}