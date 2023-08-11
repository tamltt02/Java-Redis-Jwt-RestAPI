package solutions.ntq.social.ntq_fresher_social.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import solutions.ntq.social.ntq_fresher_social.entity.UserTeam;

@Repository
public interface UserTeamRepositpry extends JpaRepository<UserTeam,Long> {
}
