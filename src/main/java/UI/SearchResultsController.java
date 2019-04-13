package UI;

import Domain.Booking;
import Domain.CustomerCard;
import Domain.Movie;
import Service.BookingService;
import Service.CustomerCardService;
import Service.MovieService;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SearchResultsController {
    public Label result;
    public TextField textToSearch;
    public Button btnSearch;
    public Button btnCancel;
    private MovieService movieService;
    private CustomerCardService cardService;
    private BookingService bookingService;

    public void setService(MovieService movieService, CustomerCardService cardService, BookingService bookingService) {
        this.movieService = movieService;
        this.cardService = cardService;
        this.bookingService = bookingService;
    }

    public void btnCancelClick(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    public void btnSearchClick(ActionEvent actionEvent) {
        String txt = textToSearch.getText();
        String found = txt + " found here:\n" + movieSearch(txt) + "\n" + cardSearch(txt) + "\n" + bookingSearch(txt);
        if(txt.length()>=1)
            result.setText(found);
    }

    private String bookingSearch(String text) {
        String bookingsTextFound = "";
        for (Booking b : bookingService.fullTextSearch(text)) {
            bookingsTextFound += b + "\n";
        }
        return bookingsTextFound;
    }

    private String cardSearch(String text) {
        String cardsTextFound = "";
        for (CustomerCard c : cardService.fullTextSearch(text)) {
            cardsTextFound += c + "\n";
        }
        return cardsTextFound;
    }

    private String movieSearch(String text) {
        String moviesTextFound = "";
        for (Movie m : movieService.fullTextSearch(text)) {
            moviesTextFound += m + "\n";
        }
        return moviesTextFound;
    }
}
