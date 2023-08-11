package solutions.ntq.social.ntq_fresher_social.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import solutions.ntq.social.ntq_fresher_social.model.request.StoryRequest;
import solutions.ntq.social.ntq_fresher_social.model.request.UpdateStoryRequest;
import solutions.ntq.social.ntq_fresher_social.security.jwt.JwtTokenProvider;
import solutions.ntq.social.ntq_fresher_social.service.StoryService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/newFeed/")
public class StoryController {
    private final StoryService storyService;

    private final JwtTokenProvider jwtTokenProvider;

    @PutMapping("updateStory")
    public ResponseEntity updateStory(@Valid @RequestBody UpdateStoryRequest updateStoryRequest) {
        return storyService.updateStory(updateStoryRequest);
    }

    @PostMapping("createStory")
    public ResponseEntity createNew(@Valid @RequestBody StoryRequest storyRequest) {
        return storyService.createNewStory(storyRequest);
    }

    @PostMapping("getNewFeedForNewStory")
    public ResponseEntity<?> getNewFeedForNewStory(@Valid @RequestBody StoryRequest storyRequest) {
        return storyService.getNewFeedForNewStory(storyRequest);
    }

    @DeleteMapping("deleteNewfeed/{id}")
    public ResponseEntity deleteStory(@PathVariable("id") String id) {
        return storyService.deleteStory(id);
    }

    @GetMapping("getListNewFeed")
    public ResponseEntity getListNewFeed(@RequestParam("number") Integer number) {
        Integer page = number == null ? 0 : number;
        return storyService.getNewFeeds(page);
    }
}
