package solutions.ntq.social.NTQ_Social_Project.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import solutions.ntq.social.NTQ_Social_Project.entity.RequestLog;
import solutions.ntq.social.NTQ_Social_Project.repository.RequestLogRepository;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class RequestInterceptor implements HandlerInterceptor {
    @Autowired
    private RequestLogRepository requestLogRepository;

    @Override
    public boolean preHandle
    (HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        System.out.println("Pre Handle method is Calling");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        HttpSession session = request.getSession();
        if(session==null){
            return;
        }
        String user = (String) session.getAttribute("user");
        RequestLog requestLog = requestLogRepository.findByUserName(user);
        if (user != null) {
            if(requestLog == null){
                RequestLog log = new RequestLog();
                log.setPath(request.getRequestURI());
                log.setRequestTime(new java.util.Date());
                log.setMethod(request.getMethod());
                log.setUserName(user);
                requestLogRepository.save(log);
            }
            else {
                requestLog.setPath(request.getRequestURI());
                requestLog.setRequestTime(new java.util.Date());
                requestLog.setMethod(request.getMethod());
                requestLog.setUserName(user);
                requestLogRepository.save(requestLog);
            }

        }
        System.out.println("Post Handle method is Calling");
    }

    @Override
    public void afterCompletion
            (HttpServletRequest request, HttpServletResponse response, Object
                    handler, Exception exception) throws Exception {

        System.out.println("Request and Response is completed");
    }
//
//    private String getUserName() {
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentPrincipalName = authentication.getName();
//        return currentPrincipalName;
//    }
}
