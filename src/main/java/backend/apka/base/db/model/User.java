package backend.apka.base.db.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class User {
    @Id
    private String id;

    private String name;
    private String surname;
    private String email;
    private String phone;
    private String role;
    private String password;

    public User(String name, String surname, String email, String phone, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = "user";
    }
//    public User(String name, String surname, String email, String phone, String role, String password) {
//        this.name = name;
//        this.surname = surname;
//        this.email = email;
//        this.phone = phone;
//        this.password = password;
//        this.role = role;
//    }

    public String getId(){
        return this.id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return this.name+" "+this.surname+" ma telefon: "+this.phone+", ID:"+this.getId();
    }


}
