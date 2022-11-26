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
@CrossOrigin(origins = "http://localhost:4200")
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
    @PostMapping("/deleteUserById")
    public ResponseEntity<User> deleteAllUsers(@RequestBody User user){
        try{
            repository.deleteById(user.getUserId());
            return new ResponseEntity<>(null,HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_ACCEPTABLE);
        }
    }
    @PostMapping("/test")
    public ResponseEntity<String> test(){
        try{
            repository.deleteAll();
            User temp = repository.save(new User("Kuba","Szadkowski","kszadkowski@wp.pl","123456789", "password"));
            repository.save(new User("Michal","Zgagacz","mzgagacz@wp.pl","125478923", "password"));
            repository.save(new User("Marzena","Kwiatkowska","mkwiatkowska@wp.pl","789541223", "password"));
            repository.save(new User("Jakub","Kucharski","jkucharski@wp.pl","396258147", "password"));
            repository.save(new User("Filip","Nowak","fnowak@wp.pl","741852293", "password"));
            repository.save(new User("Adam","Pawlak","apawlak@wp.pl","987564333", "password"));
            return new ResponseEntity<>("ok",HttpStatus.OK);

        }
        catch (Exception e){
            return new ResponseEntity<>(e.toString(),HttpStatus.OK);
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
    @PatchMapping("/patchUser")
    public ResponseEntity<User> patchUser(@RequestBody User user){
        try{
            User temp = repository.findByUserId(user.getUserId());
            temp.setName(user.getName());
            temp.setSurname(user.getSurname());
            temp.setPhone(user.getPhone());
            temp.setEmail(user.getEmail());
            repository.save(temp);
            return new ResponseEntity<>(temp, HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
