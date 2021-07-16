package aluczak.reservationsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class ReservationController {
    @Autowired
    MovieRestRepository movieRestRepository;
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    SeatRepository seatRepository;


    @PostMapping(value = "/makeReservation",
            headers = "Accept=application/json;charset=utf-8",
            consumes = "application/json;charset=utf-8")
    public ResponseEntity<String> makeReservation(@RequestBody @Valid Reservation reservationInput,
                                                  BindingResult result) {
        String validSeats = validateSeats(reservationInput);
        if (!validSeats.equals("Success")) {
            return new ResponseEntity<>(validSeats, HttpStatus.BAD_REQUEST);
        } else {
            if (result.hasErrors()) {

                return new ResponseEntity<>(Objects.requireNonNull(result.getFieldError()).getDefaultMessage(),
                        HttpStatus.BAD_REQUEST);
            } else {
                saveInputReservation(reservationInput);
                LocalDateTime expirationTime = getScreeningTimeFromInput(reservationInput);
                setupSeatsId(reservationInput);
                BigDecimal totalPrice = getTotalPrice(reservationInput);
                return new ResponseEntity<>("Total amount to pay: " + totalPrice
                        + " Expiration Time: " + expirationTime,
                        HttpStatus.OK);
            }
        }
    }

    public void saveInputReservation(Reservation reservationInput) {
        Optional<Movie> movieOptional = movieRestRepository.findById(reservationInput.movie.movieId);
        movieOptional.ifPresent(movie -> reservationRepository.save(
                new Reservation(reservationInput.firstName,
                        reservationInput.surname,
                        movie,
                        reservationInput.seats)));
    }

    public LocalDateTime getScreeningTimeFromInput(Reservation reservationInput) {
        Optional<Movie> movieOptional = movieRestRepository.findById(reservationInput.movie.movieId);
        return movieOptional.map(value -> value.screeningDateTime).orElse(null);
    }

    public void setupSeatsId(Reservation reservationInput) {
        long lastReservationId = reservationRepository.count();
        Optional<Reservation> optionalReservation = reservationRepository.findById(lastReservationId);
        Reservation reservation;
        if (optionalReservation.isPresent()) {
            reservation = optionalReservation.get();
        } else {
            throw new RuntimeException("Reservation of id " + lastReservationId + " doesn't exist.");
        }
        long lastSeatId = seatRepository.count();

        for (int i = 0; i < reservation.seats.size(); i++) {

            Optional<Seat> optionalSeat = seatRepository.findById(lastSeatId - i);
            Seat seat;
            if (optionalSeat.isPresent()) {
                seat = optionalSeat.get();
            } else {
                throw new RuntimeException();
            }
            seat.setReservation(reservation);
            seatRepository.save(seat);
        }
    }

    public BigDecimal getTotalPrice(Reservation reservationInput) {
        BigDecimal totalPrice = BigDecimal.valueOf(0);
        for (Seat seat : reservationInput.seats) {
            BigDecimal price = ticketRepository.findByType(seat.ticketType).price;
            totalPrice = totalPrice.add(price);
        }
        return totalPrice;
    }

    private String validateSeats(Reservation reservationInput) {

        Optional<Movie> optionalMovie = movieRestRepository.findById(reservationInput.movie.movieId);
        Movie movie;
        if (optionalMovie.isPresent()) {
            movie = optionalMovie.get();
        } else {
            throw new RuntimeException();
        }

        List<Reservation> movieReservations = movie.reservations;
        List<Seat> takenSeats = new ArrayList<>();
        for (Reservation movieReservation : movieReservations) {
            takenSeats.addAll(movieReservation.seats);
        }
        String valid = "Success";
        //trying to reserve the same seat
        for (int j = 0; j < reservationInput.seats.size(); j++) {
            for (int i = 0; i < reservationInput.seats.size(); i++) {
                if (i == j) {
                    continue;
                }
                if (reservationInput.seats.get(j).seatRow == reservationInput.seats.get(i).seatRow) {
                    if (reservationInput.seats.get(j).seatColumn == reservationInput.seats.get(i).seatColumn) {
                        valid = "Can't reserve the same seat.";
                        return valid;
                    }
                }
            }

        }
        if (takenSeats.isEmpty()) {
            valid = "Success";
        } else {
            for (Seat seat : reservationInput.seats) {
                //seat out of boundaries
                if (seat.seatRow > movie.room.seatRows
                        || seat.seatRow < 0
                        || seat.seatColumn > movie.room.seatColumns
                        || seat.seatColumn < 0) {
                    valid = "Seat out of boundaries.";
                    break;
                }
                for (Seat takenSeat : takenSeats) {
                    //seat is taken
                    if (takenSeat.seatRow == seat.seatRow && takenSeat.seatColumn == seat.seatColumn) {
                        valid = "Seat is already taken.";
                        break;
                        //trying to reserve a seat leaving one empty seat from taken seat
                    } else if (takenSeat.seatRow == seat.seatRow
                            && (seat.seatColumn == takenSeat.seatColumn + 2
                            || seat.seatColumn == takenSeat.seatColumn - 2)) {
                        for (Seat seat2 : reservationInput.seats) {
                            if (takenSeat.seatRow == seat2.seatRow
                                    && (seat2.seatColumn == takenSeat.seatColumn + 1
                                    || seat2.seatColumn == takenSeat.seatColumn - 1)) {
                                valid = "Success";
                                break;
                            } else {
                                valid = "Can't leave empty seat between.";
                            }
                        }
                    } else if (!valid.equals("Success")) {
                        break;
                    } else {
                        valid = "Success";
                    }
                }
            }
        }
        return valid;
    }
}


