package solutions.ntq.social.NTQ_Social_Project.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;


@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String email;
    private String fullName;
    private String password;
    private String department;
    private String bio;

    private LocalDateTime lastLoginTime;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Collection<UserGroup> userGroupSet = new ArrayList<>();

    public User(String userName, String email, String fullName, String password, String department, String bio) {
        this.userName = userName;
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.department = department;
        this.bio = bio;
    }
}
