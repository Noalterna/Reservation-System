package aluczak.reservationsystem;

import aluczak.reservationsystem.validators.ValidTicketType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "SEATS")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @NotNull
    public int seatRow;

    @NotNull
    public int seatColumn;

    @ValidTicketType
    @NotNull
    public String ticketType;

    @ManyToOne
    @JoinColumn(name = "reservationId")
    Reservation reservation;

    public Seat() {
    }

    public Seat(int seatRow, int seatColumn, String ticketType) {
        this.seatRow = seatRow;
        this.seatColumn = seatColumn;
        this.ticketType = ticketType;
    }

    public int getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(int seatRow) {
        this.seatRow = seatRow;
    }

    public int getSeatColumn() {
        return seatColumn;
    }

    public void setSeatColumn(int seatColumn) {
        this.seatColumn = seatColumn;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Reservation getReservation() {
        return reservation;
    }
}
