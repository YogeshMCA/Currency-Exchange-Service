package com.example.microservice.CurrencyExchangeService.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.microservice.CurrencyExchangeService.Controller.service.CurrencyService;
import com.example.microservice.CurrencyExchangeService.bean.ExchangeValue;
import com.example.microservice.CurrencyExchangeService.repositories.ExchangeValueRepository;
@RestController
public class CurrencyController {
	
	
	@Value("${usdtoinr}")
	private String uSDToInr;
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private CurrencyService service;
	
	/*@Resource(name="redisTemplate")
	private RedisTemplate<String, ExchangeValue> redisTemplate;
	
	@Resource(name="redisTemplate")
	private HashOperations<String, Object,ExchangeValue> hashOperations;*/
	
	@Autowired
	private ExchangeValueRepository repository;
	
	/*@GetMapping(path="/expire-redis")
	public String expireRedis(){
		if(this.redisTemplate.expire("TABLE_USER", 1, TimeUnit.SECONDS))
			return "Cleared";
		else
			return "Not Cleared";
	}*/
	
	/*@GetMapping(path="/exchange-service/from/{from}/to/{to}")
	public ExchangeValue exchange(@PathVariable String from,@PathVariable String to){
		System.out.println("Redis Cache:"+redisTemplate.hasKey("TABLE_DATA"));
		if(!redisTemplate.hasKey("TABLE_DATA")) {
			ExchangeValue exchange = repository.findByFromAndTo(from, to);
			exchange.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
			hashOperations.put("TABLE_DATA", exchange.getId(), exchange);
		}
		return hashOperations.values("TABLE_DATA").get(0) ;
	}*/
	
	@GetMapping(path="/exchange-service-cache/from/{from}/to/{to}")
	public ExchangeValue exchange(@PathVariable String from,@PathVariable String to){
		
		ExchangeValue exchange = service.getExchangeValue(from,to);
		//exchange.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		return exchange;
	}
	
	
	@GetMapping(path="/exchange-service-delete-cache/from/{from}/to/{to}")
	public String clearExchange(@PathVariable String from,@PathVariable String to){
		
		return service.clearExchangeValue(from,to);
		//exchange.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		//return "Cleared";
	}
	
	
	@GetMapping(path="/exchange-service/from/{from}/to/{to}")
	public ExchangeValue exchangeValue(@PathVariable String from,@PathVariable String to){
		
		ExchangeValue exchange = repository.findByFromAndTo(from, to);
		exchange.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		return exchange;
	}

	@GetMapping(path="/exchange-service-cfg-server/from/{from}/to/{to}")
	public String exchangeValueFrmServer(@PathVariable String from,@PathVariable String to){
						
		return from+" To "+ to +":"+uSDToInr;
	}
}
