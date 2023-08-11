package solutions.ntq.social.NTQ_Social_Project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import solutions.ntq.social.NTQ_Social_Project.model.request.NewFeedRequest;
import solutions.ntq.social.NTQ_Social_Project.service.NewFeedService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/newfeeds")
public class NewFeedController {
    private final NewFeedService newFeedService;

    @PostMapping("/create")
    public ResponseEntity<?> createNewStory(
            @Valid @RequestBody NewFeedRequest newFeed
    ) {
        return newFeedService.createNewFeed(newFeed);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateNewFeed(
            @PathVariable("id") String idNewFeed,
            @Valid @RequestBody NewFeedRequest newFeed
    ) {
        return newFeedService.updateNewFeed(idNewFeed, newFeed);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteNewFeed(@PathVariable("id") String id) {
        return newFeedService.deleteNewFeed(id);
    }

    @GetMapping("/Get-Story")
    public ResponseEntity<?> get5NewStory(@RequestParam int page,
                                          @RequestParam int size) {
        return newFeedService.get5NewStory(page, size);
    }
}
