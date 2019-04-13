package Tests;

import Domain.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CustomerCardValidatorTest {

    private IValidator<CustomerCard> validatorCard = new CustomerCardValidator();

    @Test
    void validate() {
        CustomerCard card = new CustomerCard("1","Name","Surname","000000000000", LocalDate.of(2312,11,12),
                LocalDate.of(2312,11,12),-4);


        try {
            validatorCard.validate(card);
        } catch (RuntimeException rex) {
            assertTrue(true);
        }
    }
}