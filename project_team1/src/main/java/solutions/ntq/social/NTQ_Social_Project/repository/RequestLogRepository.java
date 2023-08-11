package solutions.ntq.social.NTQ_Social_Project.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import solutions.ntq.social.NTQ_Social_Project.entity.RequestLog;

import java.util.List;

@Repository
public interface RequestLogRepository extends JpaRepository<RequestLog, Long> {
    RequestLog findByUserName(String username);

    @Query(
            value = "select a.user_name from request_log a where minute (current_time) - minute (a.request_time) <11;",
            nativeQuery = true)
    List<Object> findAllReserved();;


}