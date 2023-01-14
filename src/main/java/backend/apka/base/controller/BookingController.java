package backend.apka.base.controller;

import backend.apka.base.db.model.*;
import backend.apka.base.db.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
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
            User user;
            Room room;
            try{
                room = roomRepository.findByRoomId(booking.getRoom().getRoomId());
                room.getRoomId();
            }
            catch(Exception e){
                return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
            }
            try{
                user = userRepository.findByUserId(booking.getUser().getUserId());
                user.getUserId();
            }
            catch(Exception e){
                return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
            }
            Booking temp = repository.save(new Booking(user,room,booking.getDay(), booking.getMonth(),
                    booking.getYear(), booking.getStartTime(),booking.getEndTime()));
            return new ResponseEntity<>(temp, HttpStatus.CREATED);

        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/test")
    public ResponseEntity<String> test(){
        try{
            User user = userRepository.findByUserId("63962378bf9fe949e485e44a");
            Room room = roomRepository.findByRoomNumber("102");
            repository.save(new Booking(user,room,"09","12","2022","10:15","12:00"));

            user = userRepository.findByUserId("63962378bf9fe949e485e44a");
            room = roomRepository.findByRoomNumber("103");
            repository.save(new Booking(user,room,"09","12","2022","12:15","14:00"));

            user = userRepository.findByUserId("63962378bf9fe949e485e44b");
            room = roomRepository.findByRoomNumber("104");
            repository.save(new Booking(user,room,"09","12","2022","10:15","12:00"));

            user = userRepository.findByUserId("63962378bf9fe949e485e44b");
            room = roomRepository.findByRoomNumber("5");
            repository.save(new Booking(user,room,"09","12","2022","08:15","10:00"));

            user = userRepository.findByUserId("63962378bf9fe949e485e44b");
            room = roomRepository.findByRoomNumber("4");
            repository.save(new Booking(user,room,"10","12","2022","10:15","12:00"));

            user = userRepository.findByUserId("63962378bf9fe949e485e44d");
            room = roomRepository.findByRoomNumber("203");
            repository.save(new Booking(user,room,"10","12","2022","08:00","10:00"));

            user = userRepository.findByUserId("63962378bf9fe949e485e44e");
            room = roomRepository.findByRoomNumber("204");
            repository.save(new Booking(user,room,"10","12","2022","10:15","12:00"));
            return new ResponseEntity<>("ok",HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            System.out.println(e);
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
    @GetMapping("/getAllUserBookings/{id}")
    public ResponseEntity<List<Booking>> getAllUserBookings(@PathVariable String id){
        try{
            List<Booking> temp = repository.findByUserUserId(id);
            return new ResponseEntity<>(temp,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/getAllBookingsByRoomId/{id}")
    public ResponseEntity<List<Booking>> getAllBookingsByRoomId(@PathVariable String id){
        try{
            List<Booking> temp = repository.findByRoomRoomId(id);
            return new ResponseEntity<>(temp,HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
