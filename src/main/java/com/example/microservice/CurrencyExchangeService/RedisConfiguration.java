package com.example.microservice.CurrencyExchangeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/*@Configuration
public class RedisConfiguration {

	@Value("${spring.redis.host}")
	private String HOST;
	@Value("${spring.redis.port}")
	private Integer PORT;
	@Value("${spring.redis.password}")
	private String PASS;
	
	
	@Bean(name="jedisConnFactory")
	public JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
		config.setHostName(HOST);
		config.setPort(PORT);
		config.setPassword(PASS);
		JedisClientConfiguration clientConfig = JedisClientConfiguration.builder().usePooling().build();
		JedisConnectionFactory factory = new JedisConnectionFactory(config,clientConfig);
		return factory;
	}
	
	
	@Autowired
	RedisConnectionFactory redisConnectionFactory;
	
	@Bean
	public RedisTemplate<String, Object> redisTemplate(){
		RedisTemplate<String,Object> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);
		template.setKeySerializer(new StringRedisSerializer());
		return template;
	}
}*/
