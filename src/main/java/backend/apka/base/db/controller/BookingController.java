package backend.apka.base.db.controller;

import backend.apka.base.db.model.*;
import backend.apka.base.db.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(value = "/booking",method = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE})
public class BookingController {
    @Autowired
    BookingRepository repository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoomRepository roomRepository;

    @PostMapping("/createBooking")
    public ResponseEntity<Booking> createUser(@RequestBody Booking booking){
        try{
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
            repository.save(new Booking("nowak@wp.pl","105",new Date(2022,4,13,12,30),2));
            repository.save(new Booking("nowak@wp.pl","103",new Date(2022,4,13,12,30),2));
            repository.save(new Booking("nowak@wp.pl","104",new Date(2022,4,13,12,30),2));
            repository.save(new Booking("nowak@wp.pl","5",new Date(2022, 4,13,12,30),2));
            repository.save(new Booking("nowak@wp.pl","4",new Date(2022,4,13,12,30),2));
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
            repository.deleteById(booking.getId());
            return new ResponseEntity<>("Deleted",HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.toString(),HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
