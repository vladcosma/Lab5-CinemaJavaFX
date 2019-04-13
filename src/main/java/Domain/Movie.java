package Domain;

public class Movie extends Entity {
    private String name;
    private int year;
    private double price;
    private boolean onScreens;
    private int bookings;


    public Movie(String id, String name, int year, double price, boolean onScreens) {
        super(id);
        this.name = name;
        this.year = year;
        this.price = price;
        this.onScreens = onScreens;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", onScreens=" + onScreens +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isOnScreens() {
        return onScreens;
    }


}
