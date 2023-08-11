package solutions.ntq.social.NTQ_Social_Project.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import solutions.ntq.social.NTQ_Social_Project.entity.User;
import solutions.ntq.social.NTQ_Social_Project.repository.UserRepository;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    private final UserRepository userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
//        MyUser myUser = new MyUser(user.getUserName(), user.getPassword(),true,true, true, true,null);
//        BeanUtils.copyProperties(user,myUser);
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                new ArrayList<>());
//        return myUser;
    }

}
