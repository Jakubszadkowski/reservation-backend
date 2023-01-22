package backend.apka.base.db.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "info")
public class Info {

    @Id
    private String id;

    private String properties;
    private String count;
    private String additional;

    public Info(String properties,String count,String additional){
        this.properties=properties;
        this.count=count;
        this.additional=additional;
    }


    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getAdditional() {
        return additional;
    }

    public void setAdditional(String additional) {
        this.additional = additional;
    }
}
