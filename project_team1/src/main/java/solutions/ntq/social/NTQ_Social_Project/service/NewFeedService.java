package solutions.ntq.social.NTQ_Social_Project.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import solutions.ntq.social.NTQ_Social_Project.document.NewFeed;
import solutions.ntq.social.NTQ_Social_Project.entity.User;
import solutions.ntq.social.NTQ_Social_Project.model.request.NewFeedRequest;


public interface NewFeedService {

    Page<NewFeed> getPageNewFeed(Pageable pageable, User user);

    ResponseEntity<?> createNewFeed(NewFeedRequest newFeed);

    ResponseEntity<?> updateNewFeed(String idNewFeed, NewFeedRequest newFeed);

    ResponseEntity<?> deleteNewFeed(String id);

    ResponseEntity<?> get5NewStory( int page, int size);
}
