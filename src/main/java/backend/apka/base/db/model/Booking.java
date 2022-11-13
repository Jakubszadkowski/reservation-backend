package backend.apka.base.db.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "booking")
public class Booking {
    @Id
    private String id;

    private User user;
    private Room room;
    private Date date;
    private int timeCount;

    public Booking(User user, Room room, Date date, int timeCount) {
        this.user = user;
        this.room = room;
        this.date = date;
        this.timeCount = timeCount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTimeCount() {
        return timeCount;
    }

    public void setTimeCount(int timeCount) {
        this.timeCount = timeCount;
    }

    public String getId() {
        return this.id;
    }
}
