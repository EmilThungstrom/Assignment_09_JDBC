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

	private String userName;
	private String password;
	
	public CityDaoJDBC(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	@Override
	public City findCity(int id) {

		City city = new City();
		PreparedStatement preStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/world?autoReconnect=true&useSSL=false&serverTimezone=UTC", userName, password);
			preStatement = connection.prepareStatement("SELECT * FROM city WHERE ID LIKE ?");
			preStatement.setInt(1, id);
			resultSet = preStatement.executeQuery();
			
			resultSet.next();
			city.setId(resultSet.getInt("ID"));
			city.setName(resultSet.getString("Name"));
			city.setCountryCode(resultSet.getString("CountryCode"));
			city.setDistrict(resultSet.getString("District"));
			city.setPopulation(resultSet.getInt("Population"));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { if(resultSet != null) resultSet.close(); } catch (SQLException e) { }
			try { if(preStatement != null) preStatement.close(); } catch (SQLException e) { }
			try { if(connection != null) connection.close(); } catch (SQLException e) { }
		}
		
		return city;
	}

	@Override
	public List<City> findByCode(String code) {
		List<City> citys = new ArrayList<City>();
		PreparedStatement preStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/world?autoReconnect=true&useSSL=false&serverTimezone=UTC", userName, password);
			preStatement = connection.prepareStatement("SELECT * FROM city WHERE CountryCode LIKE ?");
			preStatement.setString(1, code);
			resultSet = preStatement.executeQuery();

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
		} finally {
			try { if(resultSet != null) resultSet.close(); } catch (SQLException e) { }
			try { if(preStatement != null) preStatement.close(); } catch (SQLException e) { }
			try { if(connection != null) connection.close(); } catch (SQLException e) { }
		}
		return citys;
	}

	@Override
	public List<City> findByName(String name) {
		List<City> citys = new ArrayList<City>();
		PreparedStatement preStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/world?autoReconnect=true&useSSL=false&serverTimezone=UTC", userName, password);
			preStatement = connection.prepareStatement("SELECT * FROM city WHERE Name LIKE ?");
			preStatement.setString(1, name);
			resultSet = preStatement.executeQuery();

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
		} finally {
			try { if(resultSet != null) resultSet.close(); } catch (SQLException e) { }
			try { if(preStatement != null) preStatement.close(); } catch (SQLException e) { }
			try { if(connection != null) connection.close(); } catch (SQLException e) { }
		}
		return citys;
	}

	@Override
	public List<City> getAll() {
		List<City> citys = new ArrayList<City>();
		Statement statement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/world?autoReconnect=true&useSSL=false&serverTimezone=UTC", userName, password);
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM city");

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
		} finally {
			try { if(resultSet != null) resultSet.close(); } catch (SQLException e) { }
			try { if(statement != null) statement.close(); } catch (SQLException e) { }
			try { if(connection != null) connection.close(); } catch (SQLException e) { }
		}
		return citys;
	}

	@Override
	public City add(City city) {
		PreparedStatement preStatement = null;
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/world?autoReconnect=true&useSSL=false&serverTimezone=UTC", userName, password);
			preStatement = connection.prepareStatement("INSERT INTO city (Name, CountryCode, District, Population) VALUES(?, ?, ?, ?)");
			preStatement.setString(1, city.getName());
			preStatement.setString(2, city.getCountryCode());
			preStatement.setString(3, city.getDistrict());
			preStatement.setInt(4, city.getPopulation());
			preStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { if(preStatement != null) preStatement.close(); } catch (SQLException e) { }
			try { if(connection != null) connection.close(); } catch (SQLException e) { }
		}
		return city;
	}

	@Override
	public City update(City city) {
		PreparedStatement preStatement = null;
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/world?autoReconnect=true&useSSL=false&serverTimezone=UTC", userName, password);
			preStatement = connection.prepareStatement("UPDATE city SET Name = ?, CountryCode = ?, District = ?, Population = ? WHERE ID = ?");
			preStatement.setString(1, city.getName());
			preStatement.setString(2, city.getCountryCode());
			preStatement.setString(3, city.getDistrict());
			preStatement.setInt(4, city.getPopulation());
			preStatement.setInt(5, city.getId());
			preStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { if(preStatement != null) preStatement.close(); } catch (SQLException e) { }
			try { if(connection != null) connection.close(); } catch (SQLException e) { }
		}
		return city;
	}

	@Override
	public int delete(City city) {
		int rowsAffected = 0;
		PreparedStatement preStatement = null;
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/world?autoReconnect=true&useSSL=false&serverTimezone=UTC", userName, password);
			preStatement = connection.prepareStatement("DELETE FROM city WHERE ID = ?");
			preStatement.setInt(1, city.getId());
			rowsAffected = preStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try { if(preStatement != null) preStatement.close(); } catch (SQLException e) { }
			try { if(connection != null) connection.close(); } catch (SQLException e) { }
		}
		return rowsAffected;
	}

}
