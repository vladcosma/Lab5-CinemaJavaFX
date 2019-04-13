package UI;

import Domain.CustomerCard;
import Service.CustomerCardService;
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

public class ExtraBonusPointsController {
    public TableView tableViewCards;
    public TableColumn tableColumnIdCard;
    public TableColumn tableColumnNameCard;
    public TableColumn tableColumnSurnameCard;
    public TableColumn tableColumnCnpCard;
    public TableColumn tableColumnDateOfBirthCard;
    public TableColumn tableColumnDateOfRegistrationCard;
    public TableColumn tableColumnBonusPointsCard;
    public TextField startDay;
    public TextField startMonth;
    public TextField endDay;
    public TextField endMonth;
    public TextField bonusPoints;
    public Button extraButton;
    public Button cancelButton;
    private CustomerCardService cardService;

    public void setService(CustomerCardService cardService) {
        this.cardService = cardService;
    }

    private ObservableList<CustomerCard> cards = FXCollections.observableArrayList();


    @FXML
    private void initialize() {
        Platform.runLater(() -> {
            cards.addAll(cardService.getAll());
            tableViewCards.setItems(cards);
        });
    }

    public void extraPointsClick(ActionEvent actionEvent) {
        try {
            LocalDate begin = LocalDate.of(1990, Integer.parseInt(startMonth.getText()), Integer.parseInt(startDay.getText()));
            LocalDate end = LocalDate.of(1990, Integer.parseInt(endMonth.getText()), Integer.parseInt(endDay.getText()));
            int bonus = Integer.parseInt(bonusPoints.getText());
            cardService.luckyBonusPoints(begin, end, bonus);
            cards.clear();
            cards.addAll(cardService.getAll());
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
    }

    public void cancelClick(ActionEvent actionEvent) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void btnCardsPointsUndoClick(ActionEvent actionEvent) {
        cardService.undo();
        cards.clear();
        cards.addAll(cardService.getAll());
    }

    public void btnCardsPointsRedoClick(ActionEvent actionEvent) {
        cardService.redo();
        cards.clear();
        cards.addAll(cardService.getAll());
    }
}
