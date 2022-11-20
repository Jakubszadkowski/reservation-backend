package backend.apka.base.db.repository;

import backend.apka.base.db.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    public List<User> findBySurname(String surname);
    public User findByPhone(String phone);
    public User findByEmail(String email);


}
