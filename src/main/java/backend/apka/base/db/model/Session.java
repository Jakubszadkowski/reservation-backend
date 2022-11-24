package backend.apka.base.db.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "session")
public class Session {
    @Id
    private String sessionId;

    private User user;

    public Session(User user){
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }
    public String getSessionId(){
        return this.sessionId;
    }

    public void setUserId(User user) {
        this.user = user;
    }
}
