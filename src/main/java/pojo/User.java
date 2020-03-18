package pojo;


import java.util.Objects;

//@Entity
//@Table(name = "user")
public class User {

//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @Column(name = "name")
    private String userName;

//    @Column(name = "email")
    private String email;

//    @Column(name = "password")
    private String passWord;
//    @Transient
    private String passwordConfirm;

    public User() {
    }

    public User(int id, String userName, String email, String passWord) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.passWord = passWord;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(passWord, user.passWord) &&
                Objects.equals (passwordConfirm, user.passwordConfirm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, email, passWord,passwordConfirm);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", passWord='" + passWord + '\'' +
                ", passwordConfirm='" + passwordConfirm + '\'' +
                '}';
    }


}

