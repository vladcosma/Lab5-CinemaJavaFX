import Domain.*;

import Repository.IRepository;
import Repository.JsonFileRepository;
import Service.CustomerCardService;
import Service.MovieService;
import Service.BookingService;

import UI.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainWindow.fxml"));
        Parent root = fxmlLoader.load();

        MainController mainController = fxmlLoader.getController();

        IValidator<Movie> movieValidator = new MovieValidator();
        IValidator<CustomerCard> cardValidator = new CustomerCardValidator();
        IValidator<Booking> bookingValidator = new BookingValidator();

        IRepository<Movie> moviesRepository = new JsonFileRepository<>(movieValidator, "movies.json", Movie.class);
        IRepository<CustomerCard> cardsRepository = new JsonFileRepository<>(cardValidator, "cards.json", CustomerCard.class);
        IRepository<Booking> bookingsRepository = new JsonFileRepository<>(bookingValidator, "bookings.json", Booking.class);


        MovieService movieService = new MovieService(moviesRepository,bookingsRepository);
        CustomerCardService cardService = new CustomerCardService(cardsRepository);
        BookingService bookingService = new BookingService(bookingsRepository, moviesRepository, cardsRepository);


        mainController.setServices(movieService, cardService, bookingService);

        primaryStage.setTitle("Cinema manager");
        primaryStage.setScene(new Scene(root, 1100, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}