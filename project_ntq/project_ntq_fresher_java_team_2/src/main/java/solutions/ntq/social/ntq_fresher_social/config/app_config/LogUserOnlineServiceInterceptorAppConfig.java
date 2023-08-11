package solutions.ntq.social.ntq_fresher_social.config.app_config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import solutions.ntq.social.ntq_fresher_social.model.handler_interceptor.UserOnlineServiceInterceptor;

@Configuration
@RequiredArgsConstructor
public class LogUserOnlineServiceInterceptorAppConfig extends WebMvcConfigurerAdapter {
    private final UserOnlineServiceInterceptor userOnlineServiceInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userOnlineServiceInterceptor);
    }
}
