package solutions.ntq.social.ntq_fresher_social.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import solutions.ntq.social.ntq_fresher_social.entity.User;
import solutions.ntq.social.ntq_fresher_social.model.dto.UserInforDTO;
import solutions.ntq.social.ntq_fresher_social.model.request.SignInRequest;
import solutions.ntq.social.ntq_fresher_social.service.OnlineUserService;
import solutions.ntq.social.ntq_fresher_social.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/")
public class UserController {
    private final UserService userService;
    private final OnlineUserService onlineUserService;

    @PostMapping("getUser")
    public ResponseEntity<?> getUser(@Valid @RequestBody SignInRequest signInRequest) {
        User user = userService.findByUsername(signInRequest.getUsername()).get();
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("getUserInfor")
    public UserInforDTO getUserInfor(@RequestParam("username") String username) {
        return userService.getUserInfor(username);
    }

    @GetMapping("onlineUsers")
    public ResponseEntity<List<String>> getOnlineUsers() {
        List<String> onlineUsers = onlineUserService.getOnlineUsers();
        return new ResponseEntity<>(onlineUsers, HttpStatus.OK);
    }

    @GetMapping("getUserInfoLogin")
    public ResponseEntity<?> getUserInfoLogin() {
        return userService.getUserInfoLogin();
    }

}
