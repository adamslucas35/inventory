/**Create Modules*/
module lucas.inventory {
    requires javafx.controls;
    requires javafx.fxml;


    opens lucas.inventory to javafx.fxml;
    exports lucas.inventory;
    exports lucas.inventory.controller;
    opens lucas.inventory.controller to javafx.fxml;
    exports lucas.inventory.model;
    opens lucas.inventory.model to javafx.fxml;
}