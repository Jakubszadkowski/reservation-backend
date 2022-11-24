package backend.apka.base.db.controller;

import backend.apka.base.db.model.Session;
import backend.apka.base.db.model.User;
import backend.apka.base.db.repository.SessionRepository;
import backend.apka.base.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/session",method = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PATCH})
public class SessionController {
    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    UserRepository userRepository;


    @PostMapping("/auth")
    public ResponseEntity<Session> auth(@RequestBody User user){
        try{

            User temp = userRepository.findByEmail(user.getEmail());
            if(temp==null){
                return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
            }
            if(user.getPassword().equals(temp.getPassword())){
                Session newSession = new Session(temp);
                sessionRepository.save(newSession);
                return new ResponseEntity<>(newSession,HttpStatus.OK);
            }
            else
                return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getSessionByUserId")
    public ResponseEntity<List<Session>> getSessionByUserId(@RequestBody User user){
        try{
            List<Session> temp = sessionRepository.findByUserUserId(user.getUserId());
            return new ResponseEntity<>(temp,HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/Logout")
    public ResponseEntity<String> logout(@RequestBody User user){
        try{
            List<Session> temp = sessionRepository.findByUserUserId(user.getUserId());
            sessionRepository.deleteAll(temp);
            return new ResponseEntity<>("Done",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getAllSessions")
    public ResponseEntity<List<Session>> getAllSessions(){
        try {
            List<Session> list = sessionRepository.findAll();
            return new ResponseEntity<>(list,HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/deleteAllSessions")
    public ResponseEntity<String> deleteAllSessions(){
        try {
            sessionRepository.deleteAll();
            return new ResponseEntity<>("Done",HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
}
