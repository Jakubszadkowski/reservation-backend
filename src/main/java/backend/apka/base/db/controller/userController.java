package backend.apka.base.db.controller;

import backend.apka.base.db.model.User;
import backend.apka.base.db.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user",method = {RequestMethod.GET,RequestMethod.POST})
public class userController {
    @Autowired
    userRepository repository;

    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@RequestBody User user){
        try{
            User temp = repository.save(new User(user.getName(),user.getSurname(),user.getEmail(),user.getPhone(), user.getPassword()));
            return new ResponseEntity<>(temp,HttpStatus.CREATED);

        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/test")
    public String create(){
        try{
            repository.deleteAll();
            User temp = repository.save(new User("Kuba","Szadkowski","szad@wp.pl","123456788", "password"));
            repository.save(new User("Michal","Zgagacz","zgagacz@wp.pl","12547893", "password"));
            repository.save(new User("Marzena","Kwiatkowski","kwiat@wp.pl","789541223", "password"));
            repository.save(new User("Jakub","Szadkowski","nowak@wp.pl","396258147", "password"));
            repository.save(new User("Filip","Dada","dada@wp.pl","741852293", "password"));
            repository.save(new User("Zbyszek","Lamentowicz","lamen@wp.pl","987564333", "password"));
            return temp.toString();

        }
        catch (Exception e){
            return e.toString();
        }
    }
    @GetMapping("/getUserBySurname")
    public ResponseEntity<List<User>> hello(@RequestBody User surname){
        try{
            System.out.println(surname);
            List<User> temp = repository.findBySurname(surname.getSurname());
            return new ResponseEntity<>(temp,HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
