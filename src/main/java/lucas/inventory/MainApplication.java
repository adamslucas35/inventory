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

/** This class creates an application that runs an inventory management system.
 * Also contained are simple functions that are accessible everywhere.
 * <b>Logical Error that was solved was making sure inventory, min and max were all within adequate ranges. I found it
 *  * easiest to create a custom exception. I didn't know how to do this, but through some research I found it was quite
 *  * simple and allowed me to run the same code multiple times without having to retype the same code. I corrected it by
 *  * checking the amounts entered and verifying the min was the smallest, max was the biggest and inventory was somewhere
 *  * in between.</b>
 *  Javadocs folder is contained in the main folder, three folders back from current directory.
 * */
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

    /**
     * Function created to auto generate action of returning to the main controller.
     * @param actionEvent recognizes action is taken, button being pushed
     * @throws IOException in case of error in input information
     */
    public static void returnToMain(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 927, 366);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Function to auto generate id for each part created.
     * @return new id
     */
    public static int generatePartsID()
    {
        int index;
        for (index = 0; index <= getAllParts().size();)
            index++;
        return index;
    }

    /**
     * Function to auto generate id for each product created.
     * @return new id
     */
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