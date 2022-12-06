package backend.apka.base.db.controller;

import backend.apka.base.db.model.User;
import backend.apka.base.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
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

                User temp = repository.save(new User(user.getTitle(),user.getName(),user.getSurname(),user.getEmail(),user.getPhone(), user.getPassword()));
                return new ResponseEntity<>(temp,HttpStatus.OK);
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
            return new ResponseEntity<>(null,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_ACCEPTABLE);
        }
    }
    @PostMapping("/test")
    public ResponseEntity<String> test(){
        try{
            repository.deleteAll();
            User temp = repository.save(new User("dr inż.","Kuba","Szadkowski","kszadkowski@wp.pl","123456789", "password"));
            temp.setRole("admin");
            repository.save(temp);
            repository.save(new User("dr inż.","Michal","Zgagacz","mzgagacz@wp.pl","125478923", "password"));
            repository.save(new User("dr inż.","Marzena","Kwiatkowska","mkwiatkowska@wp.pl","789541223", "password"));
            repository.save(new User("dr inż.","Jakub","Kucharski","jkucharski@wp.pl","396258147", "password"));
            repository.save(new User("dr inż.","Filip","Nowak","fnowak@wp.pl","741852293", "password"));
            repository.save(new User("dr inż.","Adam","Pawlak","apawlak@wp.pl","987564333", "password"));
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
            return new ResponseEntity<>(temp,HttpStatus.OK);
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
            return new ResponseEntity<>(temp,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PatchMapping("/patchPassword")
    public ResponseEntity<User> patchPassword(@RequestBody User user){
        try{
            User temp = repository.findByUserId(user.getUserId());
            temp.setPassword(user.getPassword());
            repository.save(temp);
            return new ResponseEntity<>(temp, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getAllNames")
    public ResponseEntity<List<String>> getAllNames(){
        try{
            List<User> list = repository.findAll();
            List<String> stringList = new LinkedList<>();
            for(User user:list){
                stringList.add(user.getTitle()+" "+user.getName()+" "+user.getSurname());
            }
            return new ResponseEntity<>(stringList,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PatchMapping("/patchUser")
    public ResponseEntity<User> patchUser(@RequestBody User user){
        try{
            System.out.println(user);
            User temp = repository.findByUserId(user.getUserId());
            temp.setTitle(user.getTitle());
            temp.setName(user.getName());
            temp.setSurname(user.getSurname());
            temp.setPhone(user.getPhone());
            temp.setEmail(user.getEmail());
            temp.setRole(user.getRole());
            repository.save(temp);
            return new ResponseEntity<>(temp, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getUserById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id){
        try{
            return new ResponseEntity<>(repository.findByUserId(id),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
}
