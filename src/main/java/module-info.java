module lucas.inventory {
    requires javafx.controls;
    requires javafx.fxml;


    opens lucas.inventory to javafx.fxml;
    exports lucas.inventory;
}