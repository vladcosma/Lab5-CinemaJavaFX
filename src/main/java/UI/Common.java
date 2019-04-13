package UI;

import javafx.scene.control.Alert;

class Common {

    static void showValidationError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setHeaderText("Error:");
        alert.setContentText(message);
        alert.showAndWait();
    }
}