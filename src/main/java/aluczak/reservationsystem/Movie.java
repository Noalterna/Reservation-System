package aluczak.reservationsystem;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "MOVIES")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long movieId;
    public String title;
    public LocalDateTime screeningDateTime;
    @ManyToOne()
    @JoinColumn(name = "roomId")
    public Room room;
    @OneToMany(mappedBy = "movie")
    public List<Reservation> reservations;

    public Movie() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getScreeningTime() {
        return screeningDateTime;
    }

    public void setScreeningTime(LocalDateTime screeningTime) {
        this.screeningDateTime = screeningTime;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }

    public long getId() {
        return movieId;
    }

}
