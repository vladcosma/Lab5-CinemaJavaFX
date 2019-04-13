package Domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class Booking extends Entity {
    private String idMovie;
    private String idCard;
    private LocalDate date;
    private LocalTime time;

    public Booking(String id,String idMovie, String idCard, LocalDate date, LocalTime time) {
        super(id);
        this.idMovie = idMovie;
        this.idCard = idCard;
        this.date = date;
        this.time = time;
    }


    @Override
    public String toString() {
        return "Booking{" +
                "idMovie='" + idMovie + '\'' +
                ", idCard='" + idCard + '\'' +
                ", date=" + date +
                ", time=" + time +
                '}';
    }

    public String getIdMovie() {
        return idMovie;
    }

    public String getIdCard() {
        return idCard;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

}