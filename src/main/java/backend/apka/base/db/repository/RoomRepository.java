package backend.apka.base.db.repository;

import backend.apka.base.db.model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends MongoRepository<Room,String> {
    public List<Room> findByFloor (String floor);
    public Room findByRoomNumber (String roomNumber);
    public Room findByRoomId (String roomId);
}
