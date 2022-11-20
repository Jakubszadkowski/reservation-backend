package backend.apka.base.db.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "session")
public class Session {
    @Id
    private String id;
    private String userId;

    public Session(String userId){
        this.userId = userId;
    }
    public String getUserId() {
        return this.userId = userId;
    }
    public String getId(){
        return this.id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
