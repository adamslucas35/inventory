package lucas.inventory;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {



//    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 927, 366);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
//    }
    @FXML
    public void onAddPartClick(ActionEvent actionEvent) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("addPart-view.fxml"));
        Stage stage = (Stage)((Button)(actionEvent.getSource())).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 537, 546);
        stage.setScene(scene);
        stage.show();
    }

    public void onModifyPartClick(ActionEvent actionEvent) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("modifyPart-view.fxml"));
        Stage stage = (Stage)((Button)(actionEvent.getSource())).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 537, 546);
        stage.setScene(scene);
        stage.show();
    }





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("... main-view has been initialized ...");

    }
}