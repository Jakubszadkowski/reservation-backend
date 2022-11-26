package backend.apka.base.db.controller;

import backend.apka.base.db.model.*;
import backend.apka.base.db.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "content-type")
@RequestMapping(value = "/booking",method = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE})
public class BookingController {
    @Autowired
    BookingRepository repository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoomRepository roomRepository;

    @PostMapping("/createBooking")
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking){
        try{
            try{
                Room room = roomRepository.findByRoomId(booking.getRoomId());
                room.getRoomId();
            }
            catch(Exception e){
                return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
            }
            try{
                User user = userRepository.findByUserId(booking.getUserId());
                user.getUserId();
            }
            catch(Exception e){
                return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
            }
            Booking temp = repository.save(new Booking(booking.getUserId(),booking.getRoomId(),booking.getDate(),booking.getTimeCount()));
            return new ResponseEntity<>(temp, HttpStatus.CREATED);

        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/test")
    public ResponseEntity<String> test(){
        try{
            repository.save(new Booking("63822047e89e17268501cc37","105","23/11/2022 10:15",2));
            repository.save(new Booking("63822047e89e17268501cc39","103","24/11/2022 12:15",2));
            repository.save(new Booking("63822047e89e17268501cc3c","104","25/11/2022 10:15",2));
            repository.save(new Booking("63822047e89e17268501cc3c","5","22/11/2022 8:00",2));
            repository.save(new Booking("63822047e89e17268501cc3c","4","22/11/2022 10:15",2));
            repository.save(new Booking("63822047e89e17268501cc37","5","22/11/2022 8:00",2));
            repository.save(new Booking("63822047e89e17268501cc39","4","22/11/2022 10:15",2));
            return new ResponseEntity<>("ok",HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @DeleteMapping("/deleteAllBookings")
    public ResponseEntity<String> deleteAllBookings(){
        try{
            repository.deleteAll();
            return new ResponseEntity<>("Deleted all bookings",HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.toString(),HttpStatus.NOT_ACCEPTABLE);
        }
    }
    @DeleteMapping("/deleteBookingById")
    public ResponseEntity<String> deleteBookingById(@RequestBody Booking booking){
        try{
            repository.deleteById(booking.getBookingId());
            return new ResponseEntity<>("Deleted",HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.toString(),HttpStatus.NOT_ACCEPTABLE);
        }
    }
    @PostMapping("/getAllBookings")
    public ResponseEntity<List<Booking>> getAllBookings(){
        try{
            return new ResponseEntity<>(repository.findAll(),HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getAllUserBookings")
    public ResponseEntity<List<Booking>> getAllUserBookings(@RequestBody User user){
        try{
            return new ResponseEntity<>(repository.findByUserId(user.getUserId()),HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
}
