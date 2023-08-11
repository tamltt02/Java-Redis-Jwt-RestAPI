package solutions.ntq.social.ntq_fresher_social.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import solutions.ntq.social.ntq_fresher_social.document.Story;
 

import java.util.List;

@Repository
public interface StoryRepository extends MongoRepository<Story,String> {
    @Query(value = "{$or : [{groupId : {$in : ?0 }},{groupId : null, userId : ?1}]}")
    Page<Story> getNewFeeds(List<Long> groupId, Long idUser, Pageable pageable);

}
