package solutions.ntq.social.NTQ_Social_Project.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "groups")
public class Group implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    public Group( String name, String description) {
        this.name = name;
        this.description = description;
    }

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private Collection<UserGroup> userGroupSet = new ArrayList<>();
}
