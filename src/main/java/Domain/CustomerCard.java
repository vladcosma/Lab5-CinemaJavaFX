package Domain;

import java.time.LocalDate;

public class CustomerCard extends Entity{
    private String name;
    private String surname;
    private String CNP;
    private LocalDate dateOfBirth;
    private LocalDate registrationDate;
    private int bonusPoints;

    public CustomerCard(String id, String name, String surname, String CNP, LocalDate dateOfBirth, LocalDate registrationDate, int bonusPoints) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.CNP = CNP;
        this.dateOfBirth = dateOfBirth;
        this.registrationDate = registrationDate;
        this.bonusPoints = bonusPoints;
    }

    @Override
    public String toString() {
        return "CustomerCard{" +
                "id='" + getId() + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", CNP='" + CNP + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", registrationDate='" + registrationDate + '\'' +
                ", bonusPoints=" + bonusPoints +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public int getBonusPoints() {
        return bonusPoints;
    }

    public void setBonusPoints(int bonusPoints) {
        this.bonusPoints = bonusPoints;
    }
}
