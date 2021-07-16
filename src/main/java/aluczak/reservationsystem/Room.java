package aluczak.reservationsystem;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ROOMS")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long roomId;
    @Column(name = "room_name")
    String roomName;
    int seatRows;
    int seatColumns;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    List<Movie> movies;

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setSeatColumns(int seatColumns) {
        this.seatColumns = seatColumns;
    }

    public int getSeatColumns() {
        return seatColumns;
    }

    public void setSeatRows(int seatRows) {
        this.seatRows = seatRows;
    }

    public int getSeatRows() {
        return seatRows;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }
}
