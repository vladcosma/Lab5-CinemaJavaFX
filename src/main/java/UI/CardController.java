package UI;

import Domain.CustomerCard;
import Service.CustomerCardService;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

public class CardController {
    public TextField txtCardName;
    public TextField txtCardSurname;
    public TextField txtCardCNP;
    public TextField txtCardDayB;
    public TextField txtCardMonthB;
    public TextField txtCardYearB;
    public TextField txtCardDayR;
    public TextField txtCardMonthR;
    public TextField txtCardYearR;
    public TextField txtCardBonusPoints;
    public Button btnAdd;
    public Button btnUpdate;
    public Button btnCancel;
    public Spinner spnId;

    private CustomerCardService cardService;

    public void setService(CustomerCardService cardService) {
        this.cardService = cardService;
    }

    public void btnAddClick(ActionEvent actionEvent) {
        try {
            CustomerCard c = upsertClick();
            cardService.insert(c.getId(),c.getName(),c.getSurname(),c.getCNP(),c.getDateOfBirth(),c.getRegistrationDate(),c.getBonusPoints());
            btnCancelClick(actionEvent);
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
    }

    public void btnUpdateClick(ActionEvent actionEvent) {
        try {
            CustomerCard c = upsertClick();
            cardService.update(c.getId(),c.getName(),c.getSurname(),c.getCNP(),c.getDateOfBirth(),c.getRegistrationDate(),c.getBonusPoints());
            btnCancelClick(actionEvent);
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }
    }

    public void btnCancelClick(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    private CustomerCard upsertClick(){
        try {
            String id = String.valueOf(spnId.getValue());
            String name = txtCardName.getText();
            String surname = txtCardSurname.getText();
            String CNP = txtCardCNP.getText();
            LocalDate birthday = LocalDate.of(Integer.parseInt(txtCardYearB.getText()),
                    Integer.parseInt(txtCardMonthB.getText()), Integer.parseInt(txtCardDayB.getText()));
            LocalDate registrationDate = LocalDate.of(Integer.parseInt(txtCardYearR.getText()),
                    Integer.parseInt(txtCardMonthR.getText()), Integer.parseInt(txtCardDayR.getText()));
            int bonusPoints = Integer.parseInt(txtCardBonusPoints.getText());

            return new CustomerCard(id, name, surname, CNP, birthday, registrationDate, bonusPoints);
        } catch (RuntimeException rex) {
            Common.showValidationError(rex.getMessage());
        }

        return null;

    }

}


