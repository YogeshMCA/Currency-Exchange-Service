package com.example.microservice.CurrencyExchangeService.Controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.microservice.CurrencyExchangeService.bean.ExchangeValue;
import com.example.microservice.CurrencyExchangeService.repositories.ExchangeValueRepository;
@RestController
public class CurrencyController {
	
	@Autowired
	private Environment environment;
	
	@Resource(name="redisTemplate")
	private RedisTemplate<String, ExchangeValue> redisTemplate;
	
	@Resource(name="redisTemplate")
	private HashOperations<String, Object,ExchangeValue> hashOperations;
	
	@Autowired
	private ExchangeValueRepository repository;
	
	@GetMapping(path="/expire-redis")
	public String expireRedis(){
		if(this.redisTemplate.expire("TABLE_USER", 1, TimeUnit.SECONDS))
			return "Cleared";
		else
			return "Not Cleared";
	}
	
	@GetMapping(path="/exchange-service/from/{from}/to/{to}")
	public ExchangeValue exchange(@PathVariable String from,@PathVariable String to){
		System.out.println("Redis Cache:"+redisTemplate.hasKey("TABLE_DATA"));
		if(!redisTemplate.hasKey("TABLE_DATA")) {
			ExchangeValue exchange = repository.findByFromAndTo(from, to);
			exchange.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
			hashOperations.put("TABLE_DATA", exchange.getId(), exchange);
		}
		return hashOperations.values("TABLE_DATA").get(0) ;
	}

}
