package com.example.microservice.CurrencyExchangeService.Controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.microservice.CurrencyExchangeService.bean.ExchangeValue;
import com.example.microservice.CurrencyExchangeService.repositories.ExchangeValueRepository;
@RestController
public class CurrencyController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private ExchangeValueRepository repository;
	
	@GetMapping(path="/exchange-service/from/{from}/to/{to}")
	public ExchangeValue exchange(@PathVariable String from,@PathVariable String to){
		ExchangeValue exchange = repository.findByFromAndTo(from, to);
		//System.out.println(environment.getProperty("local.server.port"));
		exchange.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		return exchange ;
	}

}
