package solutions.ntq.social.NTQ_Social_Project.model.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import solutions.ntq.social.NTQ_Social_Project.document.NewFeed;
import solutions.ntq.social.NTQ_Social_Project.model.request.NewFeedRequest;

@Component
public class NewFeedMapper {
    @Autowired
    private ModelMapper mapper;
    public NewFeed toNewFeed(NewFeedRequest newFeedRequest) {
        NewFeed newFeed = mapper.map(newFeedRequest, NewFeed.class);
        return newFeed;
    }
}
