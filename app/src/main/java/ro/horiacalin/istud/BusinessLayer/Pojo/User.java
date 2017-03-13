package ro.horiacalin.istud.BusinessLayer.Pojo;

/**
 * Created by Razvan'S PC on 05.03.2017.
 */

public class User {
    private int id;
    private String email;
    private String username;
    private String name;
    private String password;
    private int type;
    private String token;

    public User(int id, String email, String username, String name, String password, int type) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.name = name;
        this.password = password;
        this.type = type;
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
