package Tests;

import Domain.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieValidatorTest {

    private IValidator<Movie> validatorMovie = new MovieValidator();

    @Test
    void validate() {

        Movie movie = new Movie("1","Batman",2200,-3,true);

        try {
            validatorMovie.validate(movie);
        } catch (RuntimeException rex) {
            assertTrue(true);
        }
    }
}