package com.lexicon.emil.JDBC_Assignment;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.AfterClass;
import org.junit.Assert;

public class CityDaoJDBCTest {

	static public CityDao cityDao;
	static public City city = new City("test", "SWE", "testDistrict", 1);
	
	@BeforeClass
	static public void setUp() throws Exception {
		cityDao = new CityDaoJDBC("root", "password");
		cityDao.add(city);
		Assert.assertEquals(city, cityDao.findByName(city.getName()).get(0));
		Assert.assertEquals(1, cityDao.findByName(city.getName()).size());
	}
	@Test
	public void testUpdate() {
		city = cityDao.findByName(city.getName()).get(0);
		city.setPopulation(0);
		cityDao.update(city);
		Assert.assertEquals(city, cityDao.findByName(city.getName()).get(0));
	}
	@AfterClass
	static public void tearDown() {
		city = cityDao.findByName(city.getName()).get(0);
		Assert.assertEquals(1, cityDao.delete(city));
	}
}
