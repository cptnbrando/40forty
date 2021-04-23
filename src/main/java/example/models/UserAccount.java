package example.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="UserAccount")
public class UserAccount {

    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userID;

    @Column(name="email", unique = true, nullable = false)
    private String email;

    @Column(name = "username", unique = true, nullable = false, length=15)
    private String username;

    @Column(name = "password", nullable = false, length=150)
    private String password;

    @Column(name = "first_name", nullable = false, length=20)
    private String firstName;

    @Column(name = "last_name", nullable = false, length=20)
    private String lastName;

    @Column(name = "avatar_url")
    private String avatarURL;

    @JsonIgnore
    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    private List<Post> posts;

    @OneToMany(mappedBy = "reactor", fetch = FetchType.EAGER)
    private List<Reaction> reactions;

    public UserAccount() {
    }

    public UserAccount(int userID, String email, String username, String password, String firstName, String lastName, String avatarURL, List<Post> posts, List<Reaction> reactions) {
        this.userID = userID;
        this.email = email;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatarURL = avatarURL;
        this.posts = posts;
        this.reactions = reactions;
    }

    public UserAccount(String email, String username, String password, String firstName, String lastName, String avatarURL) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatarURL = avatarURL;
        this.posts = new ArrayList<>();
        this.reactions = new ArrayList<>();
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Reaction> getReactions() {
        return reactions;
    }

    public void setReactions(List<Reaction> reactions) {
        this.reactions = reactions;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userID=" + userID +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", avatarURL='" + avatarURL + '\'' +
                '}';
    }
}
