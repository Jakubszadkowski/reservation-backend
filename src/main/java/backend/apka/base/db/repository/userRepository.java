package backend.apka.base.db.repository;

import backend.apka.base.db.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends MongoRepository<User,String> {
    public User findBySurname(String surname);
}
