package backend.apka.base.db.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "session")
public class Session {
    @Id
    private String sessionId;

    private String userId;

    public Session(String userId){
        this.userId = userId;
    }

    public String getUserId() {
        return this.userId;
    }
    public String getSessionId(){
        return this.sessionId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
