package solutions.ntq.social.ntq_fresher_social.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import solutions.ntq.social.ntq_fresher_social.model.request.SignInRequest;
import solutions.ntq.social.ntq_fresher_social.model.request.SignUpRequest;
import solutions.ntq.social.ntq_fresher_social.model.response.BaseResponse;
import solutions.ntq.social.ntq_fresher_social.service.AuthService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@Valid @RequestBody SignUpRequest signUpRequest) {
        return authService.signUp(signUpRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<?> signIn(@Valid @RequestBody SignInRequest signInRequest) {
        return authService.signIn(signInRequest);
    }

    @GetMapping("/logout")
    public ResponseEntity logout(HttpServletRequest httpServletRequest){
        return authService.logOut(httpServletRequest);
    }

}
