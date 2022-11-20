package backend.apka.base.db.controller;

import backend.apka.base.db.model.Session;
import backend.apka.base.db.model.User;
import backend.apka.base.db.repository.SessionRepository;
import backend.apka.base.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/session",method = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE})
public class SessionController {
    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/auth")
    public ResponseEntity<String> auth(@RequestBody User user){
        try{
             User temp = userRepository.findByEmail(user.getEmail());
             if(temp==null){
                 return new ResponseEntity<>("Błędne dane",HttpStatus.NOT_FOUND);
             }
             if(user.getPassword().equals(temp.getPassword())){
                 sessionRepository.save(new Session(temp.getId()));
                 return new ResponseEntity<>("Zalogowano",HttpStatus.ACCEPTED);
             }
             else
                 return new ResponseEntity<>("Błędne dane",HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.toString(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/getSessionByUserId")
    public ResponseEntity<Session> getSessionByUserId(@RequestBody Session session){
        try{
            Session temp = sessionRepository.findByUserId(session.getUserId());
            return new ResponseEntity<>(temp,HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody Session session){
        try {
            sessionRepository.deleteById(session.getId());
            return new ResponseEntity<>("ok",HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
}
