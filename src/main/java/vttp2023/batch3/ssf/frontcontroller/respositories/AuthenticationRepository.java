package vttp2023.batch3.ssf.frontcontroller.respositories;

import java.time.Duration;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AuthenticationRepository {

	// TODO Task 5
	// Use this class to implement CRUD operations on Redis

	@Autowired
	@Qualifier("authTemplate")
	RedisTemplate<String, Object> template;

	public void disableUser(String username) {
		template.opsForValue().set("username", username, Duration.ofMinutes(30));
	}

	public boolean checkUserDisabled(String username) {
		if (template.opsForValue().get(username) != null) {
			return false;
		}
		return true;
	}

	public void saveToRedis(String username, String json) {
        template.opsForValue().append(username, json);
    }
    
    public Optional<String> retrieveRedisOrder(String username) {
        if (template.opsForValue().get(username) == null) {
            return Optional.empty();
        }
        return Optional.of((String)template.opsForValue().get(username));
    }

}
