package solutions.ntq.social.ntq_fresher_social.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import solutions.ntq.social.ntq_fresher_social.document.Story;
import solutions.ntq.social.ntq_fresher_social.entity.User;
import solutions.ntq.social.ntq_fresher_social.model.dto.UserDTO;
import solutions.ntq.social.ntq_fresher_social.model.request.StoryRequest;
import solutions.ntq.social.ntq_fresher_social.model.request.UpdateStoryRequest;
import solutions.ntq.social.ntq_fresher_social.repositories.GroupRepository;
import solutions.ntq.social.ntq_fresher_social.repositories.StoryRepository;
import solutions.ntq.social.ntq_fresher_social.repositories.UserRepository;
import solutions.ntq.social.ntq_fresher_social.security.jwt.JwtTokenProvider;
import solutions.ntq.social.ntq_fresher_social.service.StoryService;
import solutions.ntq.social.ntq_fresher_social.utils.RequestStatus;

import java.util.Date;

import java.util.Optional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class StoryServiceImpl implements StoryService {
    private final RedisTemplate<String, Object> redisTemplate;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final StoryRepository iStoryRepository;
    private final ModelMapper modelMapper;
    private final AuthServiceImpl authService;

    @Override
    public ResponseEntity<?> createNewStory(StoryRequest storyRequest) {
        try {
            Date now = new Date();
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            User user = userRepository.findByUsername(username).get();
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            Boolean checkGroup = groupRepository.existsById(storyRequest.getGroupId());
            if (!checkGroup){
                log.error("Group not fond");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RequestStatus.NOT_FOUND.getMessage());
            }
            if (user == null) {
                log.error("User not fond");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RequestStatus.NOT_FOUND.getMessage());
            }
            Story story = Story.builder().user(userDTO).groupId(storyRequest.getGroupId())
                    .img(storyRequest.getImg()).content(storyRequest.getContent()).createDay(now.getTime())
                    .build();
            iStoryRepository.save(story);
            return ResponseEntity.status(HttpStatus.OK.value())
                    .header("Create_new_feed", "Success!")
                    .body(story);
        } catch (Exception ex) {
            log.error("Error create new story : ", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(RequestStatus.ERROR.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> updateStory(UpdateStoryRequest updateStoryRequest) {
        try {
            Optional<Story> optionalStory = iStoryRepository.findById(updateStoryRequest.getId());
            User user = userRepository.findById(optionalStory.get().getUser().getId()).get();
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            if (!user.getUsername().equals(username)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Must not delete");
            }
            optionalStory.ifPresent(story -> {
                        story.setContent(updateStoryRequest.getContent());
                        story.setImg(updateStoryRequest.getImg());
                        iStoryRepository.save(story);
                    }
            );
            return ResponseEntity.status(HttpStatus.OK.value()).body(optionalStory.get());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RequestStatus.NOT_FOUND.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> getNewFeedForNewStory(StoryRequest storyRequest) {
        if (storyRequest == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RequestStatus.NOT_FOUND.getMessage());
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            User user = userRepository.findByUsername(username).get();
            Pageable pageable = PageRequest.of(storyRequest.getPageRequest().getPage() - 1, storyRequest.getPageRequest().getPageSize());
            List<Long> listIdGroup = groupRepository.findListIdGroup(user.getId());
            Page<Story> listNewFeed = iStoryRepository.getNewFeeds(listIdGroup, user.getId(), pageable);
            return ResponseEntity.status(HttpStatus.OK.value())
                    .header("Create_new_feed", "Success!").body(listNewFeed.getContent());
        } catch (Exception e) {
            log.error("New feed error: ", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RequestStatus.NOT_FOUND.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> deleteStory(String idStory) {
        try {
            Story story = iStoryRepository.findById(idStory).get();
            User user = userRepository.findById(story.getUser().getId()).get();
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();
            if (!user.getUsername().equals(username)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RequestStatus.NOT_DELETE.getMessage());
            }
            iStoryRepository.deleteById(idStory);
            return ResponseEntity.status(HttpStatus.OK.value())
                    .header("Delete", RequestStatus.DELETE_STATUS.getMessage())
                    .body(RequestStatus.DELETE_STATUS.getMessage());
        } catch (Exception e) {
            log.error("Delete error:", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RequestStatus.NOT_DELETE.getMessage());
        }
    }

    public static int numberPageOfNewfeed(int stories) {
        if ((stories % 5) > 0) {
            return stories / 5 + 1;
        } else return stories / 5;
    }

    @Override
    public ResponseEntity<?> getNewFeeds(int number) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepository.findByUsername(username).get();
        Pageable pageable = PageRequest.of(number, 5);
        List<Long> listIdGroup = groupRepository.findListIdGroup(user.getId());
        Page<Story> listNewFeed = iStoryRepository.getNewFeeds(listIdGroup, user.getId(), pageable);
        int theBiggestPage = numberPageOfNewfeed(listNewFeed.getSize());
        if (listNewFeed.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("Don't have any story!");
        } else {
            if (number > theBiggestPage)
                return ResponseEntity.status(HttpStatus.OK).body("Don't have story at this page");
            else return ResponseEntity.status(HttpStatus.OK.value())
                    .header("Get_new_feed", "Success!").body(listNewFeed.getContent());
        }
    }
}
