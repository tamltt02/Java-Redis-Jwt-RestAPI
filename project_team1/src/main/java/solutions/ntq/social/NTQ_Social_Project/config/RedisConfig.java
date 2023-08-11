package solutions.ntq.social.NTQ_Social_Project.config;


import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

@Configuration
@EnableCaching
public class RedisConfig {


    @Bean
    public LettuceConnectionFactory connectionFactory() {
        RedisProperties properties = properties();
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName(properties.getHost());
        configuration.setPort(properties.getPort());
        return new LettuceConnectionFactory(configuration);
    }

    @Bean
    @Primary
    public RedisTemplate<?, ?> redisTemplate() {
        final RedisTemplate<?, ?> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory());
        JdkSerializationRedisSerializer jdkSeri = new JdkSerializationRedisSerializer();
        redisTemplate.setKeySerializer(jdkSeri);
        redisTemplate.setValueSerializer(new GenericToStringSerializer<>(Object.class));
        return redisTemplate;
    }

    @Bean
    @Primary
    public RedisProperties properties() {
        return new RedisProperties();
    }
}
