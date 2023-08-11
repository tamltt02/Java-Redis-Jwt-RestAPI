package solutions.ntq.social.ntq_fresher_social.service;

import org.springframework.http.ResponseEntity;
import solutions.ntq.social.ntq_fresher_social.model.request.SignInRequest;
import solutions.ntq.social.ntq_fresher_social.model.request.SignUpRequest;
import solutions.ntq.social.ntq_fresher_social.model.response.BaseResponse;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {
    ResponseEntity<?> signUp(SignUpRequest signUpRequest);

    ResponseEntity<?> signIn(SignInRequest signInRequest);
    ResponseEntity logOut(HttpServletRequest request);

}
