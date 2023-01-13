package com.tp2.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.tp2.model.FF;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;


public class FFDao {
	private String jdbcURL = "jdbc:mysql://localhost:3308/tp2?useSSL=false&serverTimezone=UTC";
	private String jdbcUsername = "root";
	private String jdbcPassword = "test";
	private String jdbcDriver = "com.mysql.cj.jdbc.Driver";
	
	private static final String INSERT_FF_SQL = "INSERT INTO ff" + "  (theme, nom) VALUES "
			+ " (?, ?);";

	private static final String SELECT_FF_BY_ID = "select theme, nom from ff where id =?";
	private static final String SELECT_ALL_FF = "select * from ff";
	private static final String DELETE_FF_SQL = "delete from ff where id = ?;";
	private static final String UPDATE_FF_SQL = "update ff set theme = ?, nom = ? where id = ?;";

	public FFDao() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName(jdbcDriver);
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
	public void insererFF(FF ff) throws SQLException {
		System.out.println(INSERT_FF_SQL);
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FF_SQL)) {
			preparedStatement.setString(1, ff.getTheme());
			preparedStatement.setString(2, ff.getNom());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public FF selectionnerFF(int id) {
		FF ff = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FF_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String theme = rs.getString("theme");
				String nom = rs.getString("nom");
				ff = new FF(theme, nom);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return ff;
	}

	public List<FF> selectAllFF() {

		List<FF> formations = new ArrayList<>();
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FF);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String theme = rs.getString("theme");
				String nom = rs.getString("nom");
				formations.add(new FF(id,theme,nom));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return formations;
	}

	public boolean supprimerFF(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_FF_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateFF(FF formation) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_FF_SQL);) {
			System.out.println("updated Formation:"+statement);
			statement.setString(1, formation.getTheme());
			statement.setString(2, formation.getNom());
			statement.setInt(3, formation.getId());
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}
