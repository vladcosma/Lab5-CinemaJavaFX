package UI;

import Domain.Movie;
import Service.MovieService;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class MovieController {
    public TextField txtMovieName;
    public TextField txtMovieYear;
    public TextField txtMoviePrice;
    public CheckBox chkOnScreen;
    public Button btnAdd;
    public Button btnUpdate;
    public Button btnCancel;
    public Spinner spnId;
    private MovieService movieService;

    public void setService(MovieService movieService) {
        this.movieService = movieService;
    }

    public void btnCancelClick(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    public void btnAddClick(ActionEvent actionEvent) {
        try {
            Movie m = upsertClick();
            movieService.insert(m.getId(),m.getName(),m.getYear(),m.getPrice(),m.isOnScreens());
            btnCancelClick(actionEvent);
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
    }

    public void btnUpdateClick(ActionEvent actionEvent) {
        try {
            Movie m = upsertClick();
            movieService.update(m.getId(),m.getName(),m.getYear(),m.getPrice(),m.isOnScreens());
            btnCancelClick(actionEvent);
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
    }

    private Movie upsertClick(){
        try {
            String id = String.valueOf(spnId.getValue());
            String name = txtMovieName.getText();
            int year = Integer.parseInt(txtMovieYear.getText());
            double price = Double.parseDouble(txtMoviePrice.getText());
            boolean onScreen = chkOnScreen.isSelected();

            return new Movie(id, name, year, price, onScreen);
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }

        return null;

    }
}
