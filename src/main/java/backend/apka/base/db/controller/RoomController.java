package backend.apka.base.db.controller;


import backend.apka.base.db.model.Info;
import backend.apka.base.db.model.Room;
import backend.apka.base.db.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/room",method = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE})
public class RoomController {
    @Autowired
    RoomRepository repository;

    @PostMapping("/createRoom")
    public ResponseEntity<Room> createRoom(@RequestBody Room room){
        try{
            Room temp = repository.save(new Room(room.getFloor(),room.getRoomNumber(),room.getAdditionInformation()));
            return new ResponseEntity<>(temp, HttpStatus.CREATED);

        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/test")
    public ResponseEntity<String> test(){
        try{
            Room temp = repository.save(new Room("0","3",new Info("Programowanie",16,"")));
            repository.save(new Room("0","4",new Info("Programowanie",16,"Windows")));
            repository.save(new Room("0","5",new Info("Programowanie",16,"Mac")));
            repository.save(new Room("0","6",new Info("Programowanie",16,"Mac")));
            repository.save(new Room("1","101",new Info("Programowanie",16,"")));
            repository.save(new Room("1","102",new Info("Programowanie",16,"")));
            repository.save(new Room("1","103",new Info("Sala lekcyjna",16,"")));
            repository.save(new Room("1","104",new Info("Programowanie",16,"")));
            repository.save(new Room("2","201",new Info("Grafika",16,"")));
            repository.save(new Room("2","202",new Info("Grafika",16,"")));
            repository.save(new Room("2","203",new Info("Grafika",16,"")));
            repository.save(new Room("2","204",new Info("Aula",200,"")));
            return new ResponseEntity<>("Ok", HttpStatus.CREATED);

        }
        catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/deleteAllRooms")
    public ResponseEntity<String> deleteAllRooms(){
        try{
            repository.deleteAll();
            return new ResponseEntity<>("Deleted all rooms",HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.toString(),HttpStatus.NOT_ACCEPTABLE);
        }
    }
    @DeleteMapping("/deleteRoomById")
    public ResponseEntity<String> deleteRoomById(@RequestBody Room room){
        try{
            repository.deleteById(room.getRoomId());
            return new ResponseEntity<>("Deleted",HttpStatus.OK);
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
            return new ResponseEntity<>(temp,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getRoomById")
    public ResponseEntity<Room> getRoomById(String roomId){
        try{
            return new ResponseEntity<>(repository.findByRoomId(roomId),HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
    @PatchMapping("/patchRoom")
    public ResponseEntity<Room> patchRoom(@RequestBody Room room){
        try{
            Room temp = repository.findByRoomId(room.getRoomId());
            temp.setRoomNumber(room.getRoomNumber());
            temp.setFloor(temp.getFloor());
            temp.setAdditionInformation(room.getAdditionInformation());
            repository.save(temp);
            return new ResponseEntity<>(temp,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
