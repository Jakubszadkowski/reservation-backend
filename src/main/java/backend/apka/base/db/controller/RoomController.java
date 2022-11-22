package backend.apka.base.db.controller;


import backend.apka.base.db.model.Room;
import backend.apka.base.db.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "content-type")
@RequestMapping(value = "/room",method = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE})
public class RoomController {
    @Autowired
    RoomRepository repository;

    @PostMapping("/createRoom")
    public ResponseEntity<Room> createRoom(@RequestBody Room room){
        try{
            System.out.println();
            Room temp = repository.save(new Room(room.getFloor(),room.getRoomNumber(),room.getAdditionInformation()));
            return new ResponseEntity<>(temp, HttpStatus.CREATED);

        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/test")
    public ResponseEntity<Room> test(){
        try{
            Room temp = repository.save(new Room("0","4",""));
            repository.save(new Room("0","4",""));
            repository.save(new Room("0","5",""));
            repository.save(new Room("0","6",""));
            repository.save(new Room("1","104",""));
            repository.save(new Room("1","103",""));
            repository.save(new Room("1","105",""));
            return new ResponseEntity<>(temp, HttpStatus.CREATED);

        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/deleteAllRooms")
    public ResponseEntity<String> deleteAllRooms(){
        try{
            repository.deleteAll();
            return new ResponseEntity<>("Deleted all rooms",HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.toString(),HttpStatus.NOT_ACCEPTABLE);
        }
    }
    @DeleteMapping("/deleteRoomById")
    public ResponseEntity<String> deleteRoomById(@RequestBody Room room){
        try{
            repository.deleteById(room.getRoomId());
            return new ResponseEntity<>("Deleted",HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.toString(),HttpStatus.NOT_ACCEPTABLE);
        }
    }
    @GetMapping("/getAllRoomsFromFloor")
    public ResponseEntity<List<Room>> getAllRoomsFromFloor(@RequestBody Room room){
        try{
            List<Room> temp = repository.findByFloor(room.getFloor());
            return new ResponseEntity<>(temp, HttpStatus.OK);

        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getAllRooms")
    public ResponseEntity<List<Room>> getAllRooms() {
        try {
            List<Room> temp = repository.findAll();
            return new ResponseEntity<>(temp,HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
