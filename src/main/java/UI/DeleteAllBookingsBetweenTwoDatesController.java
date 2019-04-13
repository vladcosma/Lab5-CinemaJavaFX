package UI;

import Domain.Booking;
import Service.BookingService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

public class DeleteAllBookingsBetweenTwoDatesController {
    public TableView tableViewBookings;
    public TableColumn tableColumnIdBooking;
    public TableColumn tableColumnIdMovieBooking;
    public TableColumn tableColumnIdCardBooking;
    public TableColumn tableColumnDateOfBooking;
    public TableColumn tableColumnTimeOfBooking;
    public Button deleteButton;
    public Button cancelButton;
    private BookingService bookingService;
    public TextField startDay;
    public TextField startMonth;
    public TextField startYear;
    public TextField endDay;
    public TextField endMonth;
    public TextField endYear;

    public void setService(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    private ObservableList<Booking> bookings = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        Platform.runLater(() -> {
            bookings.addAll(bookingService.getAll());
            tableViewBookings.setItems(bookings);
        });
    }

    public void deleteBookingsClick(ActionEvent actionEvent) {
        try {
            LocalDate begin = LocalDate.of(Integer.parseInt(startYear.getText()), Integer.parseInt(startMonth.getText()), Integer.parseInt(startDay.getText()));
            LocalDate end = LocalDate.of(Integer.parseInt(endYear.getText()), Integer.parseInt(endMonth.getText()), Integer.parseInt(endDay.getText()));
            bookingService.removeBookingsByPeriod(begin, end);
            bookings.clear();
            bookings.addAll(bookingService.getAll());
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
    }

    public void cancelClick(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void btnBookingsDeleteUndoClick(ActionEvent actionEvent) {
        bookingService.undo();
        bookings.clear();
        bookings.addAll(bookingService.getAll());

    }

    public void btnBookingsDeleteRedoClick(ActionEvent actionEvent) {
        bookingService.redo();
        bookings.clear();
        bookings.addAll(bookingService.getAll());
    }
}
