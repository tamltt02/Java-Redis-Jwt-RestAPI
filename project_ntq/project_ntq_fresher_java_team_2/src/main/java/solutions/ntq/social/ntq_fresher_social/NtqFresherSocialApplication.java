package solutions.ntq.social.ntq_fresher_social;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class NtqFresherSocialApplication {
    public static void main(String[] args) {
        SpringApplication.run(NtqFresherSocialApplication.class, args);
    }

}
