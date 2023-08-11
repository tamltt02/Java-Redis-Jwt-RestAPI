package solutions.ntq.social.ntq_fresher_social.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import solutions.ntq.social.ntq_fresher_social.service.OnlineUserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static solutions.ntq.social.ntq_fresher_social.utils.Constans.ONLINE_STATUS;

@Service
@RequiredArgsConstructor
public class OnlineUserServiceImpl implements OnlineUserService {

    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public List<String> getOnlineUsers() {
        List<String> onlineUsers = new ArrayList<>();
        Set<String> keys = redisTemplate.keys("*"); // Lấy danh sách key trong Redis
        System.out.println(keys.toString());
        if (keys != null && !keys.isEmpty()) { // Lấy thông tin value của từng key và kiểm tra xem có phải người dùng đang hoạt động hay không
            for (String key : keys) {
                if (key.endsWith(ONLINE_STATUS)){
                    Date lastActiveTime = (Date) redisTemplate.opsForValue().get(key);
                    if (lastActiveTime != null && isUserActive(lastActiveTime)) {
                        onlineUsers.add(key);
                    } else {
                        redisTemplate.delete(key);
                    }
                }
            }
        }
        return onlineUsers;
    }

    private boolean isUserActive(Date lastActiveTime) {  // Kiểm tra xem người dùng có hoạt động trong vòng 10 phút hay không
        Date now = new Date();
        long diffInMillis = now.getTime() - lastActiveTime.getTime();
        long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(diffInMillis);
        return diffInMinutes < 10;
    }

}

