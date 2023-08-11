package solutions.ntq.social.ntq_fresher_social.model.handler_interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

import static solutions.ntq.social.ntq_fresher_social.utils.Constans.ONLINE_STATUS;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserOnlineServiceInterceptor implements HandlerInterceptor{

    private final RedisTemplate<String,Object> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) { // Lấy thông tin người dùng từ Spring Security sau đó thêm log người dùng vào redis
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            String username = auth.getName();
            Date now = new Date();
            redisTemplate.opsForValue().set(username+ ONLINE_STATUS,now);
            log.info("User online :" +username+" Time new request: " + now);
        }
        System.out.println("Pre Handle method is Calling");
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {
    }
}
