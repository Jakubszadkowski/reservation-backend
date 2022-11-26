package backend.apka.base.db.repository;

import backend.apka.base.db.model.Session;
import backend.apka.base.db.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepository extends MongoRepository <Session,String> {
    public List<Session> findByUserUserId(String userId);
    public List<Session> findByUserEmail(String email);
}
