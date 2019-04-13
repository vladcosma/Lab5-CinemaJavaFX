package UI;

import Domain.Booking;
import Domain.CustomerCard;
import Domain.Movie;
import Service.BookingService;
import Service.CustomerCardService;
import Service.MovieService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainController {
    public TableView tableViewMovies;
    public TableColumn tableColumnIdMovie;
    public TableColumn tableColumnNameMovie;
    public TableColumn tableColumnPriceMovie;
    public TableColumn tableColumnYearMovie;
    public TableColumn tableColumnOnScreensMovie;

    public TableView tableViewCards;
    public TableColumn tableColumnIdCard;
    public TableColumn tableColumnNameCard;
    public TableColumn tableColumnSurnameCard;
    public TableColumn tableColumnCnpCard;
    public TableColumn tableColumnDateOfBirthCard;
    public TableColumn tableColumnDateOfRegistrationCard;
    public TableColumn tableColumnBonusPointsCard;

    public TableView tableViewBookings;
    public TableColumn tableColumnIdBooking;
    public TableColumn tableColumnIdMovieBooking;
    public TableColumn tableColumnIdCardBooking;
    public TableColumn tableColumnDateOfBooking;
    public TableColumn tableColumnTimeOfBooking;

    private MovieService movieService;
    private CustomerCardService cardService;
    private BookingService bookingService;

    private ObservableList<Movie> movies = FXCollections.observableArrayList();
    private ObservableList<CustomerCard> cards = FXCollections.observableArrayList();
    private ObservableList<Booking> bookings = FXCollections.observableArrayList();


    public void setServices(MovieService movieService, CustomerCardService cardService, BookingService bookingService) {
        this.movieService = movieService;
        this.cardService = cardService;
        this.bookingService = bookingService;
    }

    @FXML
    private void initialize() {
        Platform.runLater(() -> {
            movies.addAll(movieService.getAll());
            tableViewMovies.setItems(movies);
            cards.addAll(cardService.getAll());
            tableViewCards.setItems(cards);
            bookings.addAll(bookingService.getAll());
            tableViewBookings.setItems(bookings);
        });
    }

    public void btnAddMovieClick(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/movieAdd.fxml"));
        upsertMovie(fxmlLoader, "Movie add");
    }


    public void btnMovieDeleteClick(ActionEvent actionEvent) {
        Movie selected = (Movie) tableViewMovies.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                movieService.remove(selected.getId());
                movies.clear();
                movies.addAll(movieService.getAll());
            } catch (RuntimeException rex) {
                Common.showValidationError(rex.getMessage());
            }
        }
    }

    public void btnUpdateMovieClick(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/movieUpdate.fxml"));
        upsertMovie(fxmlLoader, "Movie update");
    }

    public void upsertMovie(FXMLLoader fxmlLoader, String title) {
        try {
            Scene scene = new Scene(fxmlLoader.load(), 600, 200);
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            MovieController controller = fxmlLoader.getController();
            controller.setService(movieService);
            stage.showAndWait();
            movies.clear();
            movies.addAll(movieService.getAll());
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window: Movie update.", e);
        }
    }


    public void btnAddCardClick(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/cardAdd.fxml"));
        upsertCard(fxmlLoader, "Card add");
    }


    public void btnUpdateCardClick(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/cardUpdate.fxml"));
        upsertCard(fxmlLoader, "Card update");
    }


    public void upsertCard(FXMLLoader fxmlLoader, String title) {
        try {
            Scene scene = new Scene(fxmlLoader.load(), 600, 300);
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            CardController controller = fxmlLoader.getController();
            controller.setService(cardService);
            stage.showAndWait();
            cards.clear();
            cards.addAll(cardService.getAll());
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window: Card ", e);
        }
    }

    public void btnDeleteCardClick(ActionEvent actionEvent) {
        CustomerCard selected = (CustomerCard) tableViewCards.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                cardService.remove(selected.getId());
                cards.clear();
                cards.addAll(cardService.getAll());
            } catch (RuntimeException rex) {
                Common.showValidationError(rex.getMessage());
            }
        }
    }


    public void btnAddBookingClick(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/bookingAdd.fxml"));
        upsertBooking(fxmlLoader, "Booking add");
    }


    public void btnUpdateBookingClick(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/bookingUpdate.fxml"));
        upsertBooking(fxmlLoader, "Booking update");
    }


    public void upsertBooking(FXMLLoader fxmlLoader, String title) {
        try {
            Scene scene = new Scene(fxmlLoader.load(), 600, 300);
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            BookingController controller = fxmlLoader.getController();
            controller.setService(bookingService);
            stage.showAndWait();
            bookings.clear();
            bookings.addAll(bookingService.getAll());

            cards.clear();
            cards.addAll(cardService.getAll());
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window: Booking ", e);
        }
    }

    public void btnDeleteBookingClick(ActionEvent actionEvent) {
        Booking selected = (Booking) tableViewBookings.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                bookingService.remove(selected.getId());
                bookings.clear();
                bookings.addAll(bookingService.getAll());

                cards.clear();
                cards.addAll(cardService.getAll());
            } catch (RuntimeException rex) {
                Common.showValidationError(rex.getMessage());
            }
        }
    }

    public void searchClick(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/searchResults.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
            Stage stage = new Stage();
            stage.setTitle("Full Text Search");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            SearchResultsController controller = fxmlLoader.getController();
            controller.setService(movieService, cardService, bookingService);
            stage.showAndWait();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window: Full Text Search add.", e);
        }
    }

    public void customBookingSearch(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/customBookingSearch.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 600, 600);
            Stage stage = new Stage();
            stage.setTitle("Custom Booking Search");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            CustomBookingSearchController controller = fxmlLoader.getController();
            controller.setService(bookingService);
            stage.showAndWait();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window: Custom Booking Search add.", e);
        }
    }

    public void moviesByBookings(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/showMoviesOrderedByBookings.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 600, 600);
            Stage stage = new Stage();
            stage.setTitle("Movies ordered by bookings");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            ShowMoviesOrderedByBookingsController controller = fxmlLoader.getController();
            controller.setService(movieService);
            stage.showAndWait();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window: Movies ordered by bookings add.", e);
        }
    }

    public void cardsByBonusPoints(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/showCardsByBonusPoints.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 900, 600);
            Stage stage = new Stage();
            stage.setTitle("Cards ordered by bonus points");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            ShowCardsByBonusPointsController controller = fxmlLoader.getController();
            controller.setService(cardService);
            stage.showAndWait();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window: Cards ordered by bonus points add.", e);
        }
    }

    public void deleteBookings(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/deleteAllBookingsBetweenTwoDates.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 1000, 400);
            Stage stage = new Stage();
            stage.setTitle("Deleting bookings between two dates");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            DeleteAllBookingsBetweenTwoDatesController controller = fxmlLoader.getController();
            controller.setService(bookingService);
            stage.showAndWait();
            bookings.clear();
            bookings.addAll(bookingService.getAll());
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window: Delete bookings add.", e);
        }
    }

    public void extraBonusPoints(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/extraBonusPoints.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 800, 600);
            Stage stage = new Stage();
            stage.setTitle("Lucky bonus");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            ExtraBonusPointsController controller = fxmlLoader.getController();
            controller.setService(cardService);
            stage.showAndWait();
            //
            cards.clear();
            cards.addAll(cardService.getAll());
            //
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window: Extra bonus points add.", e);
        }
    }

    public void btnMovieUndoClick(ActionEvent actionEvent) {
        movieService.undo();
        movies.clear();
        movies.addAll(movieService.getAll());
    }

    public void btnMovieRedoClick(ActionEvent actionEvent) {
        movieService.redo();
        movies.clear();
        movies.addAll(movieService.getAll());
    }

    public void btnCardUndoClick(ActionEvent actionEvent) {
        cardService.undo();
        cards.clear();
        cards.addAll(cardService.getAll());
    }

    public void btnCardRedoClick(ActionEvent actionEvent) {
        cardService.redo();
        cards.clear();
        cards.addAll(cardService.getAll());
    }

    public void btnBookingUndoClick(ActionEvent actionEvent) {
        bookingService.undo();
        bookings.clear();
        bookings.addAll(bookingService.getAll());
    }

    public void btnBookingRedoClick(ActionEvent actionEvent) {
        bookingService.redo();
        bookings.clear();
        bookings.addAll(bookingService.getAll());
    }
}
