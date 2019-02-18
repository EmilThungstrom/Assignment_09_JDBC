package com.lexicon.emil.JDBC_Assignment;

import java.util.List;

public interface CityDao {

	City findCity(int id);
	
	List<City> findByCode(String code);
	List<City> findByName(String name);
	List<City> getAll();
	
	City add(City city);
	City update(City city);
	int delete(City city);
}
