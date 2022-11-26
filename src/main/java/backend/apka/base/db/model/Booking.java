package backend.apka.base.db.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "booking")
public class Booking {
    @Id
    private String bookingId;

    private String userId;
    private String roomId;
    private String date;
    private int timeCount;

    public Booking(String userId, String roomId, String date, int timeCount) {
        this.userId = userId;
        this.roomId = roomId;
        this.date = date;
        this.timeCount = timeCount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUser(String userId) {
        this.userId = userId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoom(String roomId) {
        this.roomId = roomId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTimeCount() {
        return timeCount;
    }

    public void setTimeCount(int timeCount) {
        this.timeCount = timeCount;
    }

    public String getBookingId() {
        return this.bookingId;
    }
}
