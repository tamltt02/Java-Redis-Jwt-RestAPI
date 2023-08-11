package solutions.ntq.social.NTQ_Social_Project.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import solutions.ntq.social.NTQ_Social_Project.repository.RequestLogRepository;
import solutions.ntq.social.NTQ_Social_Project.service.RequestLogService;

import java.util.List;

@Service
@EnableCaching
@RequiredArgsConstructor
public class RequestLogImpl implements RequestLogService {

   private final RequestLogRepository requestLogRepository;
    @Cacheable(cacheNames="cache2",key = "'#key'")
    @Override
    public List<Object> getOnlineAccounts() {
        return requestLogRepository.findAllReserved();
    }
}
