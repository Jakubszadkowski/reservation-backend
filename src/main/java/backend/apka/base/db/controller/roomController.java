package backend.apka.base.db.controller;


import backend.apka.base.db.model.Room;
import backend.apka.base.db.repository.roomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/room",method = {RequestMethod.GET,RequestMethod.POST})
public class roomController {
    @Autowired
    roomRepository repository;

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

}
