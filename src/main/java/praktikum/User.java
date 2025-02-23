package praktikum;

public class User {
    private String email;
    private String password;
    private String name;
    private String accessToken;

    public User (String email, String password, String name, String accessToken) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.accessToken = accessToken;
    }

    public User (String email , String password , String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public User (String email , String password) {
        this.email = email;
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
}

