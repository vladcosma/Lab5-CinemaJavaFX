package UI;

import Domain.CustomerCard;
import Service.CustomerCardService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ShowCardsByBonusPointsController {
    public TableView tableViewOrderedCards;
    public TableColumn tableColumnIdCard;
    public TableColumn tableColumnNameCard;
    public TableColumn tableColumnSurnameCard;
    public TableColumn tableColumnCnpCard;
    public TableColumn tableColumnDateOfBirthCard;
    public TableColumn tableColumnDateOfRegistrationCard;
    public TableColumn tableColumnBonusPointsCard;
    private CustomerCardService cardService;

    private ObservableList<CustomerCard> cards = FXCollections.observableArrayList();

    public void setService(CustomerCardService cardService) {
        this.cardService = cardService;
    }

    @FXML
    private void initialize() {
        Platform.runLater(() -> {
            try {
                List<CustomerCard> cardsOrdered = cardService.sortedCardsByBonusPoints();

                cards.addAll(cardsOrdered);
                tableViewOrderedCards.setItems(cards);
            } catch (RuntimeException e) {
                Logger logger = Logger.getLogger(getClass().getName());
                logger.log(Level.SEVERE, "Failed to create new Window: Movies By Bookings.", e);
            }
        });
    }


}
