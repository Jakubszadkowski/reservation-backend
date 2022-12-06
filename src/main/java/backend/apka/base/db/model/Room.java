package backend.apka.base.db.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "room")
public class Room {
    @Id
    private String roomId;

    private String floor;
    private String roomNumber;
    private Info additionInformation;

    public Room(String floor, String roomNumber, Info additionInformation) {
        this.floor = floor;
        this.roomNumber = roomNumber;
        this.additionInformation = additionInformation;
    }
    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Info getAdditionInformation() {
        return additionInformation;
    }

    public void setAdditionInformation(Info additionInformation) {
        this.additionInformation = additionInformation;
    }

    public String getRoomId() {
        return this.roomId;
    }
}
