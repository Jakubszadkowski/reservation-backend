package backend.apka.base.db.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "room")
public class Room {
    @Id
    private String id;

    private String floor;
    private String roomNumber;
    private String additionInformation;

    public Room(String floor, String roomNumber, String additionInformation) {
        this.floor = floor;
        this.roomNumber = roomNumber;
        this.additionInformation = additionInformation;
    }
    public Room(String id,String floor, String roomNumber, String additionInformation) {
        this.id = id;
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

    public String getAdditionInformation() {
        return additionInformation;
    }

    public void setAdditionInformation(String additionInformation) {
        this.additionInformation = additionInformation;
    }
}
