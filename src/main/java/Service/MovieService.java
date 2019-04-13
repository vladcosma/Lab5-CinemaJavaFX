package Service;

import Domain.Booking;
import Domain.Movie;
import Repository.IRepository;

import java.util.*;

public class MovieService {
    private IRepository<Movie> movieRepository;
    private IRepository<Booking> bookingRepository;

    private Stack<UndoRedoOperation<Movie>> undoableOperations = new Stack<>();
    private Stack<UndoRedoOperation<Movie>> redoableeOperations = new Stack<>();

    public MovieService(IRepository<Movie> movieRepository, IRepository<Booking> bookingRepository) {
        this.movieRepository = movieRepository;
        this.bookingRepository = bookingRepository;
    }

    /**
     * adds a movie
     *
     * @param id        the movie's id
     * @param name      the movie's name
     * @param year      the movie's year
     * @param price     the price of a ticker for this movie
     * @param onScreens boolean for setting if movie is on screens or not
     */
    public void insert(String id, String name, int year, double price, boolean onScreens) {
        Movie movie = new Movie(id, name, year, price, onScreens);
        movieRepository.insert(movie);
        undoableOperations.add(new AddOperation<>(movieRepository, movie));
        redoableeOperations.clear();
    }

    /**
     * updates a movie
     *
     * @param id        the movie's id we want to update
     * @param name      the movie's name
     * @param year      the movie's year
     * @param price     the price of a ticker for this movie
     * @param onScreens boolean for setting if movie is on screens or not
     */
    public void update(String id, String name, int year, double price, boolean onScreens) {
        Movie actualMovie = movieRepository.getById(id);
        Movie movieUpdate = new Movie(id, name, year, price, onScreens);
        movieRepository.update(movieUpdate);
        undoableOperations.add(new UpdateOperation(movieRepository, movieUpdate, actualMovie));
        redoableeOperations.clear();
    }

    /**
     * removes a movie
     *
     * @param id the id of the movie that we want to remove
     */
    public void remove(String id) {
        Movie movie = movieRepository.getById(id);
        movieRepository.remove(id);
        undoableOperations.add(new DeleteOperation<>(movieRepository, movie));
        redoableeOperations.clear();
    }

    /**
     * list of all movies
     *
     * @return a list with all movies
     */
    public List<Movie> getAll() {
        return movieRepository.getAll();
    }

    /**
     * searches a text in all movies
     *
     * @param text the text to find
     * @return a list with all movies where text was found
     */
    public List<Movie> fullTextSearch(String text) {
        List<Movie> found = new ArrayList<>();
        for (Movie m : movieRepository.getAll()) {
            if ((m.getId().contains(text) || m.getName().contains(text) ||
                    Integer.toString(m.getYear()).contains(text) || Double.toString(m.getPrice()).contains(text) ||
                    Boolean.toString(m.isOnScreens()).contains(text)) && !found.contains(m)) {
                found.add(m);
            }
        }
        return found;
    }

    public void undo() {
        if (!undoableOperations.empty()) {
            UndoRedoOperation<Movie> lastOperation = undoableOperations.pop();
            lastOperation.doUndo();
            redoableeOperations.add(lastOperation);

        }
    }

    public void redo() {
        if (!redoableeOperations.empty()) {
            UndoRedoOperation<Movie> lastOperation = redoableeOperations.pop();
            lastOperation.doRedo();
            undoableOperations.add(lastOperation);
        }
    }

    public List<movieByBookingsVM> sortByBookings() {
        Map<String,Integer> frequences = new HashMap<>();
        for (Booking b: bookingRepository.getAll()) {
            String movieId = b.getIdMovie();
            if(frequences.containsKey(movieId)){
                frequences.put(movieId,frequences.get(movieId)+1);
            } else {
                frequences.put(movieId,1);
            }
        }

        List<movieByBookingsVM> orderedMovies = new ArrayList<>();
        for (String movieId:frequences.keySet()) {
            Movie movie = movieRepository.getById(movieId);
            orderedMovies.add(new movieByBookingsVM(movie,frequences.get(movieId)));

    }
        orderedMovies.sort((m1,m2) -> Integer.compare(m2.getBookings(), m1.getBookings()));
        return orderedMovies;
    }
}
