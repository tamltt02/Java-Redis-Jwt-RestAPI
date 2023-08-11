package solutions.ntq.social.NTQ_Social_Project.service.Impl;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import solutions.ntq.social.NTQ_Social_Project.config.MapperConfig;
import solutions.ntq.social.NTQ_Social_Project.document.NewFeed;
import solutions.ntq.social.NTQ_Social_Project.entity.User;
import solutions.ntq.social.NTQ_Social_Project.helper.ErrorMessage;
import solutions.ntq.social.NTQ_Social_Project.helper.SuccessMessage;
import solutions.ntq.social.NTQ_Social_Project.model.mapper.NewFeedMapper;
import solutions.ntq.social.NTQ_Social_Project.model.request.NewFeedRequest;
import solutions.ntq.social.NTQ_Social_Project.repository.NewFeedRepository;
import solutions.ntq.social.NTQ_Social_Project.service.NewFeedService;
import solutions.ntq.social.NTQ_Social_Project.service.UserGroupService;
import solutions.ntq.social.NTQ_Social_Project.service.UserService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NewFeedServiceImpl implements NewFeedService {
    private final NewFeedRepository newFeedRepository;
    private final UserGroupService userGroupService;
    private final UserService userService;
    private final NewFeedMapper mapper;

    @Override
    public Page<NewFeed> getPageNewFeed(Pageable pageable, User user) {
        List<Long> lstGroupofUser = userGroupService.getAllGroupOfUser(String.valueOf(user.getId()));
        return newFeedRepository.findPrivateNewFeed( lstGroupofUser, user.getId(),pageable);
    }

    @Override
    public ResponseEntity<?> createNewFeed(NewFeedRequest newFeed) {
        User user = userService.getUserFromToken();
        newFeed.setUserId(user.getId());
        Date date = new java.util.Date();
        newFeed.setCreateDate(date);
        return ResponseEntity.ok(newFeedRepository.save(mapper.toNewFeed(newFeed)));
    }

    @Override
    public ResponseEntity<?> updateNewFeed(String idNewFeed, NewFeedRequest newFeed) {
        Optional<NewFeed> entity = newFeedRepository.findById(idNewFeed);
        if (entity.isPresent()) {
            NewFeed newFeed1 = entity.get();
            newFeed1.setContent(newFeed.getContent() != null ? newFeed.getContent() : newFeed1.getContent());
            newFeed1.setImage(newFeed.getImage() != null ? newFeed.getImage() : newFeed1.getImage());
            Date date = new Date(System.currentTimeMillis());
            newFeed1.setUpdateDate(date);
            newFeedRepository.save(newFeed1);
            return new ResponseEntity<>(newFeed1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(ErrorMessage.NEW_FEED_NOT_FOUND.getMessage() + idNewFeed, ErrorMessage.NEW_FEED_NOT_FOUND.getStatus());
        }
    }

    @Override
    public ResponseEntity<?> deleteNewFeed(String id) {
        try {
            newFeedRepository.deleteById(id);
            return ResponseEntity.ok(SuccessMessage.DELETED_SUCCESSFULLY.getMessage() + id);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> get5NewStory(int page, int size) {
        User user = userService.getUserFromToken();
        Pageable paging = PageRequest.of(page - 1, size, Sort.by("createDate").descending());
        return ResponseEntity.ok(getPageNewFeed(paging, user).getContent());
    }


}
