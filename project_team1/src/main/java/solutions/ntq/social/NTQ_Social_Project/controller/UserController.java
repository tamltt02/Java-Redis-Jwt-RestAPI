package solutions.ntq.social.NTQ_Social_Project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import solutions.ntq.social.NTQ_Social_Project.model.request.CreateUserRequest;
import solutions.ntq.social.NTQ_Social_Project.model.request.JwtRequest;
import solutions.ntq.social.NTQ_Social_Project.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@Valid @RequestBody CreateUserRequest user, HttpSession httpSession) {
        return userService.saveUser(user, httpSession);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest, HttpSession httpSession) throws Exception {
        return userService.createAuthenticationToken(authenticationRequest, httpSession);
    }

    @GetMapping("/getInfo/{name}")
    public ResponseEntity<?> getInfo(@PathVariable("name") String username) throws Exception {
        return userService.getInfo(username);

    }


}
