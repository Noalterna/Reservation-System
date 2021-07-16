package aluczak.reservationsystem;

import java.time.LocalDateTime;

public class MovieDTO {
    public long movieId;
    public String title;
    public LocalDateTime screeningDateTime;

    public MovieDTO() {
    }

    public MovieDTO(long movieId, String title, LocalDateTime screeningDateTime) {
        this.movieId = movieId;
        this.title = title;
        this.screeningDateTime = screeningDateTime;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getScreeningDateTime() {
        return screeningDateTime;
    }

    public void setScreeningDateTime(LocalDateTime screeningDateTime) {
        this.screeningDateTime = screeningDateTime;
    }
}
