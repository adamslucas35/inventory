package lucas.inventory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lucas.inventory.model.InHouse;
import lucas.inventory.model.Inventory;
import lucas.inventory.model.OutSourced;
import lucas.inventory.model.Product;

import java.io.IOException;

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



    /**Method to launch application.
     * @param args accepts all arguments*/
    public static void main(String[] args) {



        launch();
    }
}