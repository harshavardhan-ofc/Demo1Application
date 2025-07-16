package com.postgresql.demo1.HealthCheck;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

public class MemoryHealthIndicator {

    @Value("${myProperty.name}")
    private String name;

    @Component
    public class memoryHealthIndicator implements HealthIndicator {
        @Override
        public Health health() {
            System.out.println("hello Im in health :" + name);
            boolean isHealthy = checkCustomLogic();
            return isHealthy ? Health.up().build() : Health.down().withDetail("Error", "Custom issue").build();
        }

        private boolean checkCustomLogic() {
// Custom logic to determine health
            return true;
        }
    }



}
