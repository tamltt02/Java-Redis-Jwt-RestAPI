package solutions.ntq.social.NTQ_Social_Project.model.mapper;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import solutions.ntq.social.NTQ_Social_Project.entity.User;
import solutions.ntq.social.NTQ_Social_Project.model.dto.UserDTO;
import solutions.ntq.social.NTQ_Social_Project.model.request.CreateUserRequest;

@Component
public class UserMapper {

    @Autowired
    private  ModelMapper mapper ;
    public User toUser(CreateUserRequest createUserRequest) {
        User user = mapper.map(createUserRequest, User.class);
        String hash = BCrypt.hashpw(createUserRequest.getPassword(), BCrypt.gensalt(12));
        user.setPassword(hash);
        return user;
    }
}
