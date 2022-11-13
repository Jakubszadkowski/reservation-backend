package backend.apka.base.db.controller;

import backend.apka.base.db.model.User;
import backend.apka.base.db.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user",method = {RequestMethod.GET,RequestMethod.POST})
public class userController {
    @Autowired
    userRepository repository;

    @PostMapping("/api2")
    public ResponseEntity<User> createUser(@RequestBody User user){
        try{
            User temp = repository.save(new User(user.getName(),user.getSurname(),user.getEmail(),user.getPhone()));
            return new ResponseEntity<>(temp,HttpStatus.CREATED);

        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/api")
    public String create(){
        try{
            User temp = repository.save(new User("Kuba","Szadkowski","szad@wp.pl","123456788"));
            return temp.toString();

        }
        catch (Exception e){
            return e.toString();
        }
    }
    @GetMapping("/get")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name){
        try{
            return String.format(repository.findBySurname("Szadkowski").toString());
        }
        catch (Exception e){
            return e.toString();
        }
    }

}
