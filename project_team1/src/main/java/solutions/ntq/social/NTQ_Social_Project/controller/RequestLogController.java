package solutions.ntq.social.NTQ_Social_Project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import solutions.ntq.social.NTQ_Social_Project.service.RequestLogService;

@RestController
public class RequestLogController {

    @Autowired
    RequestLogService requestLogService;
    @GetMapping("/online")
    public ResponseEntity<Object> getOnlineAccounts() {
        return (ResponseEntity<Object>) requestLogService.getOnlineAccounts();
    }
}
