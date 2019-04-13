package Service;

import Domain.Movie;

public class movieByBookingsVM {
    private Movie movie;
    private int bookings;

    public movieByBookingsVM(Movie movie, int bookings) {
        this.movie = movie;
        this.bookings = bookings;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getBookings() {
        return bookings;
    }
}
