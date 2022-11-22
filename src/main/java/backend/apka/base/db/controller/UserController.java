package backend.apka.base.db.controller;

import backend.apka.base.db.model.User;
import backend.apka.base.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "content-type")
@RequestMapping(value = "/user",method = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PATCH})
public class UserController {
    @Autowired
    UserRepository repository;

    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@RequestBody User user){
        try{
            User test = repository.findByEmail(user.getEmail());
            if(test==null){

                User temp = repository.save(new User(user.getName(),user.getSurname(),user.getEmail(),user.getPhone(), user.getPassword()));
                return new ResponseEntity<>(temp,HttpStatus.CREATED);
            }
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);

        }
        catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/deleteAllUsers")
    public ResponseEntity<String> deleteAllUsers(){
        try{
            repository.deleteAll();
           return new ResponseEntity<>("Done",HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_ACCEPTABLE);
        }
    }
    @DeleteMapping("/deleteUserById")
    public ResponseEntity<String> deleteAllUsers(@RequestBody User user){
        try{
            repository.deleteById(user.getUserId());
            return new ResponseEntity<>("Done",HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_ACCEPTABLE);
        }
    }
    @PostMapping("/test")
    public String test(){
        try{
            repository.deleteAll();
            User temp = repository.save(new User("Kuba","Szadkowski","szad@wp.pl","123456788", "password"));
            repository.save(new User("Michal","Zgagacz","zgagacz@wp.pl","12547893", "password"));
            repository.save(new User("Marzena","Kwiatkowski","kwiat@wp.pl","789541223", "password"));
            repository.save(new User("Jakub","Szadkowski","nowak@wp.pl","396258147", "password"));
            repository.save(new User("Filip","Dada","dada@wp.pl","741852293", "password"));
            repository.save(new User("Zbyszek","Lamentowicz","lamen@wp.pl","987564333", "password"));
            return temp.getPassword();

        }
        catch (Exception e){
            return e.toString();
        }
    }
    @GetMapping("/getUserBySurname")
    public ResponseEntity<List<User>> getUserBySurname(@RequestBody User surname){
        try{
            List<User> temp = repository.findBySurname(surname.getSurname());
            return new ResponseEntity<>(temp,HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getUserByEmail")
    public ResponseEntity<User> getUserByEmail(@RequestBody User surname){
        try{
            User temp = repository.findByEmail(surname.getEmail());
            return new ResponseEntity<>(temp,HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getUserByEmail(){
        try{
            List<User> temp = repository.findAll();
            return new ResponseEntity<>(temp,HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PatchMapping("/patchPhone")
    public ResponseEntity<User> patchPhone(@RequestBody User user){
        try{
            User temp = repository.findByEmail(user.getEmail());
            temp.setPhone(user.getPhone());
            repository.save(temp);
            return new ResponseEntity<>(temp, HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PatchMapping("/patchPassword")
    public ResponseEntity<User> patchPassword(@RequestBody User user){
        try{
            User temp = repository.findByEmail(user.getEmail());
            temp.setPassword(user.getPassword());
            repository.save(temp);
            return new ResponseEntity<>(temp, HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PatchMapping("/patchSurname")
    public ResponseEntity<User> patchSurname(@RequestBody User user){
        try{
            User temp = repository.findByEmail(user.getEmail());
            temp.setSurname(user.getSurname());
            repository.save(temp);
            return new ResponseEntity<>(temp, HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PatchMapping("/patchName")
    public ResponseEntity<User> patchName(@RequestBody User user){
        try{
            User temp = repository.findByEmail(user.getEmail());
            temp.setName(user.getName());
            repository.save(temp);
            return new ResponseEntity<>(temp, HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getAllSurnames")
    public ResponseEntity<List<User>> getAllSurnames(){
        try{
            List<User> list = repository.findAll();
            List<String> stringList = new LinkedList<>();
            for(User user:list){
                stringList.add(user.getSurname());
            }
            return new ResponseEntity<>(list,HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
