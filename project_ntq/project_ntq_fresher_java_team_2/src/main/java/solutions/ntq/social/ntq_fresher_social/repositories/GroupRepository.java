package solutions.ntq.social.ntq_fresher_social.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import solutions.ntq.social.ntq_fresher_social.entity.Group;
import solutions.ntq.social.ntq_fresher_social.entity.UserTeam;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group,Long> {
    @Query(value = "select user_team.team_id from team JOIN user_team on team.id = user_team.team_id JOIN user on user_team.user_id = user.id  where user_team.user_id = ?1",nativeQuery = true)
    List<Long> findListIdGroup(@Param("id") Long idUser);

    @Query(value = "select u from UserTeam u where u.user.id =?1 and u.group.id = ?2")
    UserTeam findByUserAndGroup(Long idUser, Long idGroup);


}
