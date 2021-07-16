package aluczak.reservationsystem;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class MovieController {
    @Autowired
    MovieRestRepository movieRestRepository;

    @GetMapping("/movies/byScreeningTimeResponse")
    public List<MovieDTO> getMovieFromScreeningTime(
            @RequestParam("startDateTime")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDateTime,
            @RequestParam("endDateTime")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDateTime) {

        List<Movie> movies = movieRestRepository
                .findByScreeningDateTimeBetweenOrderByTitleAscScreeningDateTimeAsc(
                        startDateTime,
                        endDateTime);

        List<MovieDTO> results = new ArrayList<>();
        for (Movie movie : movies) {
            long id = movie.movieId;
            String title = movie.title;
            LocalDateTime screeningTime = movie.screeningDateTime;
            results.add(new MovieDTO(id, title, screeningTime));
        }
        return results;
    }

    @GetMapping("/movies/{movieId}")
    public ResponseEntity<String> getSeatsAndRoom(@PathVariable @NotNull long movieId) {
        Optional<Movie> optionalMovie = movieRestRepository.findById(movieId);
        if (optionalMovie.isEmpty()) {
            return new ResponseEntity<>("Wrong movieId" + HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST);
        }
        Movie movie = optionalMovie.get();
        List<Reservation> reservations = movie.reservations;
        List<Seat> seats = new ArrayList<>();
        for (Reservation reservation : reservations) {
            seats.addAll(reservation.seats);
        }

        List<Pair<Integer, Integer>> availableSeats = new ArrayList<>();
        int roomRows = movie.room.seatRows;
        int roomColumns = movie.room.seatColumns;

        for (int i = 0; i < roomRows; i++) {
            for (int j = 0; j < roomColumns; j++) {
                if (seats.isEmpty()) {
                    availableSeats.add(Pair.of(i, j));
                } else {
                    availableSeats.add(Pair.of(i, j));
                    for (Seat seat : seats) {
                        if (seat.seatRow == i && seat.seatColumn == j) {
                            availableSeats.remove(Pair.of(i, j));
                        }
                    }
                }

            }
        }
        return new ResponseEntity<>("Room: " + movie.room.roomName + "\nAvailable seats: " + availableSeats, HttpStatus.OK);
    }

}
