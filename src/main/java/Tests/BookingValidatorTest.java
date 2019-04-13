package Tests;

import Domain.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class BookingValidatorTest {
    private IValidator<Booking> validatorBooking = new BookingValidator();

    @Test
    void validate() {
        Booking booking = new Booking("1", "1", "1", LocalDate.of(2030,11,2), LocalTime.of(10,0));

        try {
            validatorBooking.validate(booking);
        } catch (RuntimeException rex) {
            assertTrue(true);
        }
    }
}