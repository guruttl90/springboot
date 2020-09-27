package com.spring.demoexceptionhandling.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.demoexceptionhandling.exceptionhandling.CityNotFoundException;
import com.spring.demoexceptionhandling.exceptionhandling.NoDataFoundException;
import com.spring.demoexceptionhandling.model.City;

@Service
public class CityService {

	public City findById(Long id) {
		throw new CityNotFoundException(id);       
    }
	
	public City save(City city) {
        return null;
    }
	
	public List<City> findAll() {
		throw new NoDataFoundException();
    }
}
