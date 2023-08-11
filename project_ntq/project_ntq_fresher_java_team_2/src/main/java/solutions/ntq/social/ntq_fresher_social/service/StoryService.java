package solutions.ntq.social.ntq_fresher_social.service;

import org.springframework.http.ResponseEntity;
import solutions.ntq.social.ntq_fresher_social.model.request.StoryRequest;
import solutions.ntq.social.ntq_fresher_social.model.request.UpdateStoryRequest;

public interface StoryService {
    ResponseEntity<?> createNewStory(StoryRequest storyRequest);
 
    ResponseEntity<?> getNewFeedForNewStory(StoryRequest storyRequest);

    ResponseEntity<?> deleteStory(String id);

    ResponseEntity<?> updateStory(UpdateStoryRequest updateStoryRequest);
 
    ResponseEntity<?> getNewFeeds( int number);
 
}
