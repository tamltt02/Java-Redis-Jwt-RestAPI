package solutions.ntq.social.NTQ_Social_Project.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import solutions.ntq.social.NTQ_Social_Project.entity.User;
import solutions.ntq.social.NTQ_Social_Project.exception.BadRequestException;
import solutions.ntq.social.NTQ_Social_Project.model.request.CreateUserRequest;
import solutions.ntq.social.NTQ_Social_Project.model.request.JwtRequest;

import javax.servlet.http.HttpSession;

@Service
public interface UserService {

    ResponseEntity<?> saveUser(CreateUserRequest createUserRequest, HttpSession httpSession) throws BadRequestException;

    User getUserByUserName(String userName);

    User getUserFromToken();

    void authenticate(String username, String password) throws Exception;

    ResponseEntity<?> createAuthenticationToken(JwtRequest authenticationRequest, HttpSession httpSession) throws Exception;

    ResponseEntity<?> getInfo(String username) throws Exception;
}
