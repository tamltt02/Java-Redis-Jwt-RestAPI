package solutions.ntq.social.NTQ_Social_Project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import solutions.ntq.social.NTQ_Social_Project.document.NewFeed;

import java.util.List;
import java.util.Optional;

@Repository
public interface NewFeedRepository extends MongoRepository<NewFeed,String> {

    @Query("{$or:[{groupId: { $in:  ?0 }} , {groupId: null , userId : ?1}] }")
    Page<NewFeed> findPrivateNewFeed ( List<Long> groupid, Long userId,Pageable pageable);
}
