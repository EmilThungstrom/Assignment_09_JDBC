package com.lexicon.emil.JDBC_Assignment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CityDaoJDBC implements CityDao {

	private Connection connection;

	
	public CityDaoJDBC() throws SQLException {
		 connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/world?autoReconnect=true&useSSL=false&serverTimezone=UTC", "root", "password");
	}
	
	@Override
	public City findCity(int id) {

		City city = new City();
		try {
			PreparedStatement idQuery = connection.prepareStatement("SELECT * FROM city WHERE ID LIKE ?");
			idQuery.setInt(1, id);
			ResultSet resultSet = idQuery.executeQuery();
			
			resultSet.next();
			city.setId(resultSet.getInt("ID"));
			city.setName(resultSet.getString("Name"));
			city.setCountryCode(resultSet.getString("CountryCode"));
			city.setDistrict(resultSet.getString("District"));
			city.setPopulation(resultSet.getInt("Population"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return city;
	}

	@Override
	public List<City> findByCode(String code) {
		List<City> citys = new ArrayList<City>();
		try {
			PreparedStatement codeQuery = connection.prepareStatement("SELECT * FROM city WHERE CountryCode LIKE ?");
			codeQuery.setString(1, code);
			ResultSet resultSet = codeQuery.executeQuery();

			while(resultSet.next()) {
				City city = new City();
				city.setId(resultSet.getInt("ID"));
				city.setName(resultSet.getString("Name"));
				city.setCountryCode(resultSet.getString("CountryCode"));
				city.setDistrict(resultSet.getString("District"));
				city.setPopulation(resultSet.getInt("Population"));
				citys.add(city);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return citys;
	}

	@Override
	public List<City> findByName(String name) {
		List<City> citys = new ArrayList<City>();
		try {
			PreparedStatement nameQuery = connection.prepareStatement("SELECT * FROM city WHERE Name LIKE ?");
			nameQuery.setString(1, name);
			ResultSet resultSet = nameQuery.executeQuery();

			while(resultSet.next()) {
				City city = new City();
				city.setId(resultSet.getInt("ID"));
				city.setName(resultSet.getString("Name"));
				city.setCountryCode(resultSet.getString("CountryCode"));
				city.setDistrict(resultSet.getString("District"));
				city.setPopulation(resultSet.getInt("Population"));
				citys.add(city);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return citys;
	}

	@Override
	public List<City> getAll() {
		List<City> citys = new ArrayList<City>();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM city");

			while(resultSet.next()) {
				City city = new City();
				city.setId(resultSet.getInt("ID"));
				city.setName(resultSet.getString("Name"));
				city.setCountryCode(resultSet.getString("CountryCode"));
				city.setDistrict(resultSet.getString("District"));
				city.setPopulation(resultSet.getInt("Population"));
				citys.add(city);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return citys;
	}

	@Override
	public City add(City city) {
		try {
			PreparedStatement addQuery = connection.prepareStatement("INSERT INTO city (Name, CountryCode, District, Population) VALUES(?, ?, ?, ?)");
			addQuery.setString(1, city.getName());
			addQuery.setString(2, city.getCountryCode());
			addQuery.setString(3, city.getDistrict());
			addQuery.setInt(4, city.getPopulation());
			addQuery.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return city;
	}

	@Override
	public City update(City city) {
		try {
			PreparedStatement updateQuery = connection.prepareStatement("UPDATE city SET Name = ?, CountryCode = ?, District = ?, Population = ? WHERE ID = ?");
			updateQuery.setString(1, city.getName());
			updateQuery.setString(2, city.getCountryCode());
			updateQuery.setString(3, city.getDistrict());
			updateQuery.setInt(4, city.getPopulation());
			updateQuery.setInt(5, city.getId());
			updateQuery.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return city;
	}

	@Override
	public int delete(City city) {
		int rowsAffected = 0;
		try {
			PreparedStatement deleteQuery = connection.prepareStatement("DELETE FROM city WHERE ID = ?");
			deleteQuery.setInt(1, city.getId());
			rowsAffected = deleteQuery.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowsAffected;
	}

}
