package backend.apka.base.db.repository;

import backend.apka.base.db.model.Booking;
import backend.apka.base.db.model.Room;
import backend.apka.base.db.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends MongoRepository<Booking,String> {
    public List<Booking> findByUser(User user);
    public List<Booking> findByRoom(Room room);
    public List<Booking> findByMonth(String month);
    public List<Booking> findByUserUserId(String userId);
    public List<Booking> findByRoomRoomId (String roomId);
}
