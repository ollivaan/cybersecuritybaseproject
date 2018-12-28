package sec.project.domain;

import javax.persistence.Entity;
import org.springframework.data.jpa.domain.AbstractPersistable;

public class Signup{

    private String username;
    private String password;
    private int id;

    public Signup(int id ,String username, String password) {
        
        this.username = username;
        this.password = password;
        this.id = id;
    }
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }    



    public String getName() {
        return username;
    }

    public void setName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
