package com.example.microservice.CurrencyExchangeService.Controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.example.microservice.CurrencyExchangeService.bean.ExchangeValue;
import com.example.microservice.CurrencyExchangeService.repositories.ExchangeValueRepository;

@Component
public class CurrencyService {

	
	@Autowired
	private ExchangeValueRepository repository;
	
	@Cacheable(value="Details", key="#from")
	public ExchangeValue getExchangeValue(String from, String to) {
		System.out.println("=============>Cache Calling");
		return repository.findByFromAndTo(from, to);
	}

	@CacheEvict(cacheNames = "Details", key = "#from")
	public String clearExchangeValue(String from, String to) {
			return "Cleared";
	}
	
	
	
}
