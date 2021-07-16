package aluczak.reservationsystem;

import aluczak.reservationsystem.validators.ValidFirstName;
import aluczak.reservationsystem.validators.ValidSurname;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "RESERVATIONS")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long reservationId;

    @NotBlank(message = "First Name can't be empty")
    @Size(min = 3, max = 255)
    @ValidFirstName
    String firstName;

    @NotBlank(message = "Surname can't be empty")
    @Size(min = 3, max = 255)
    @ValidSurname
    String surname;

    @NotNull(message = "Movie can't be empty. Give movieId")
    @ManyToOne
    @JoinColumn(name = "movieId")
    Movie movie;

    @NotNull(message = "Seats can't be empty")
    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    List<Seat> seats;

    public Reservation() {
    }

    public Reservation(String firstName, String surname, Movie movie, List<Seat> seats) {
        this.firstName = firstName;
        this.surname = surname;
        this.movie = movie;
        this.seats = seats;
    }

    public long getReservationId() {
        return reservationId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setReservationId(long reservationId) {
        this.reservationId = reservationId;
    }
}
