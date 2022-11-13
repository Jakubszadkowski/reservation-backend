package backend.apka.base.db.controller;

import backend.apka.base.db.model.Booking;
import backend.apka.base.db.model.Room;
import backend.apka.base.db.model.User;
import backend.apka.base.db.repository.bookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/booking",method = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE})
public class bookingController {
    @Autowired
    bookingRepository repository;
    @PostMapping("/createBooking")
    public ResponseEntity<Booking> createUser(@RequestBody Booking booking){
        try{
            Booking temp = repository.save(new Booking(booking.getUser(),booking.getRoom(),booking.getDate(),booking.getTimeCount()));
            return new ResponseEntity<>(temp, HttpStatus.CREATED);

        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
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
