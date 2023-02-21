package lucas.inventory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/** This class creates an applications that runs an inventory management system*/
public class MainApplication extends Application {
    @Override
    /**Creates first scene and opens application.
     * Application is displayed in new window.
     * @param stage new stage is created
     * @throws IOException incase of input/output error*/
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