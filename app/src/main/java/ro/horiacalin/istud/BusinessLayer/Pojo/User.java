package ro.horiacalin.istud.BusinessLayer.Pojo;

/**
 * Created by Razvan'S PC on 05.03.2017.
 */

public class User {
    private String email;
    private String username;
    private String password;
    private int type;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
