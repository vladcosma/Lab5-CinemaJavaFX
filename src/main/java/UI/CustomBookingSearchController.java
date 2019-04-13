package UI;

import Domain.Booking;
import Service.BookingService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomBookingSearchController {
    public TableView tableViewBookings;
    public TableColumn tableColumnIdBooking;
    public TableColumn tableColumnIdMovieBooking;
    public TableColumn tableColumnIdCardBooking;
    public TableColumn tableColumnDateOfBooking;
    public TableColumn tableColumnTimeOfBooking;

    public Button bookingSearch;
    public Button btnCancel;

    public TextField startHour;
    public TextField startMinutes;
    public TextField endHour;
    public TextField endMinutes;

    private BookingService bookingService;

    private ObservableList<Booking> bookings = FXCollections.observableArrayList();

    public void setService(BookingService bookingService) {
        this.bookingService = bookingService;
    }


    public void btnSearchClick(ActionEvent actionEvent) {
        try {
            bookings.clear();
            LocalTime begin = LocalTime.of(Integer.parseInt(startHour.getText()), Integer.parseInt(startMinutes.getText()));
            LocalTime end = LocalTime.of(Integer.parseInt(endHour.getText()), Integer.parseInt(endMinutes.getText()));
            bookings.addAll(bookingService.bookingsByPeriod(begin, end));
            tableViewBookings.setItems(bookings);
        } catch (RuntimeException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window: Movie update.", e);
            Common.showValidationError(e.getMessage());

        }
    }

    public void btnCancelClick(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
}
