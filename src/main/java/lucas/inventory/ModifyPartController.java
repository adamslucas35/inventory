package lucas.inventory;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyPartController implements Initializable {
    // Initializer to make sure code is being run through the console
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("... addPart-view has been initialized ...");
    }

    //Fields
    @FXML
    private Label mp_changableText;
    @FXML
    private Button mp_cancelBtn;
    @FXML
    private RadioButton mp_inHouse_rbtn;
    @FXML
    private RadioButton mp_outsource_rbtn;




    // Cancel button to go back to previous pane
    @FXML
    public void mp_onCancelClick(ActionEvent actionEvent) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 927, 366);
        stage.setScene(scene);
        stage.show();
    }

    // text changes to machine id when in house is selected
    public void mp_onInHouseSelect(ActionEvent actionEvent)
    {
        mp_changableText.setText("Machine ID");
    }

    // text changes to company name when outsource is selected
    public void mp_onOutsourceSelect(ActionEvent actionEvent)
    {
        mp_changableText.setText("Company Name");

    }
}
