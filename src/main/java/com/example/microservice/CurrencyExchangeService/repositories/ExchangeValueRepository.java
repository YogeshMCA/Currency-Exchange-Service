package com.example.microservice.CurrencyExchangeService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.microservice.CurrencyExchangeService.bean.ExchangeValue;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long>{
	ExchangeValue findByFromAndTo(String from,String to);
}
