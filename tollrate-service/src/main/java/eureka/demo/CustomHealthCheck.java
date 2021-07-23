package eureka.demo;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;


@Component
public class CustomHealthCheck implements HealthIndicator {
	
	
	int errorCode=0;
	@Override
	public Health health() {
		System.out.println("Health check performed! Error code is:"+ errorCode);
		if(errorCode>3 && errorCode<9)
		{
			errorCode++;
			return Health.down().withDetail("Custom error code", errorCode).build();
		}
		errorCode++;
		return Health.up().build();
	}

}
