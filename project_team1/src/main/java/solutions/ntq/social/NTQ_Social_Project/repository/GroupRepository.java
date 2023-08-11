package solutions.ntq.social.NTQ_Social_Project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import solutions.ntq.social.NTQ_Social_Project.entity.Group;
import solutions.ntq.social.NTQ_Social_Project.entity.User;

@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {
    Group findById(int id);
}
