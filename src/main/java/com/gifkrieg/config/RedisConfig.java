package com.gifkrieg.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Created by robbie on 4/4/17.
 */

@Configuration
@Profile("redis")
@EnableRedisHttpSession
public class RedisConfig {
}