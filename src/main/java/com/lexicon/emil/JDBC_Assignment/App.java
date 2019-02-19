package com.lexicon.emil.JDBC_Assignment;

public class App 
{
    public static void main( String[] args )
    {
        CityDao cityDao = new CityDaoJDBC("root", "password");
        System.out.println("Find by ID:");
        System.out.println(cityDao.findCity(1));
        System.out.println();
        
        System.out.println("Find by name:");
        City city = cityDao.findByName("Sundsvall").get(0);
        System.out.println(city);
        System.out.println();
        
        System.out.println("Find by country code:");
        System.out.println(cityDao.findByCode("SWE"));
        System.out.println();
        
        System.out.println("Delete city:");
        cityDao.delete(city);
        System.out.println(cityDao.findByName("Sundsvall"));
        System.out.println();
        
        System.out.println("Add city:");
        cityDao.add(city);
        System.out.println(cityDao.findByName("Sundsvall"));
        System.out.println();
        
        System.out.println("Update city:");
        city = cityDao.findByName("Sundsvall").get(0);
        int pop = city.getPopulation();
        city.setPopulation(0);
        cityDao.update(city);
        System.out.println(cityDao.findByName("Sundsvall"));
        city.setPopulation(pop);
        cityDao.update(city);
        System.out.println(cityDao.findByName("Sundsvall"));
        System.out.println();
    }
}
