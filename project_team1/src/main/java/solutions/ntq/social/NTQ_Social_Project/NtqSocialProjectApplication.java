package solutions.ntq.social.NTQ_Social_Project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableMongoRepositories
public class NtqSocialProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(NtqSocialProjectApplication.class, args);
	}

}
