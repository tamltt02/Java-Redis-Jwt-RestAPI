package solutions.ntq.social.ntq_fresher_social.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "team")
@SuperBuilder
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name_group")
    private String nameGroup;

    @Column(name = "details", columnDefinition = "text")
    private String details;

    @OneToMany(mappedBy = "user")

    @JsonIgnore
    private List<UserTeam> userTeams ;

}
