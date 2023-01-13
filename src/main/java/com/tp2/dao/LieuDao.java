package com.tp2.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.tp2.model.Lieu;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;


public class LieuDao {
	private String jdbcURL = "jdbc:mysql://localhost:3308/tp2?useSSL=false&serverTimezone=UTC";
	private String jdbcUsername = "root";
	private String jdbcPassword = "test";
	private String jdbcDriver = "com.mysql.cj.jdbc.Driver";
	
	private static final String INSERT_LIEUX_SQL = "INSERT INTO lieux" + "  (adresse, ville) VALUES "
			+ " (?,?);";
	private static final String SELECT_LIEUX_BY_ID = "select adresse, ville from lieux where id =?";
	private static final String SELECT_ALL_LIEUX = "select * from lieux";
	private static final String DELETE_LIEUX_SQL = "delete from lieux where id = ?;";
	private static final String UPDATE_LIEUX_SQL = "update lieux set adresse = ?, ville= ? where id = ?;";

	public LieuDao() {
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
	public void insererLieu(Lieu lieu) throws SQLException {
		System.out.println(INSERT_LIEUX_SQL);
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LIEUX_SQL)) {
			preparedStatement.setString(1, lieu.getAdresse());
			preparedStatement.setString(2, lieu.getVille());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Lieu selectionnerLieu(int id) {
		Lieu lieu = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LIEUX_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String adresse = rs.getString("adresse");
				String ville = rs.getString("ville");
				lieu = new Lieu(adresse, ville);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return lieu;
	}

	public List<Lieu> selectAllLieux() {

		List<Lieu> lieux = new ArrayList<>();
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LIEUX);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String adresse = rs.getString("adresse");
				String ville = rs.getString("ville");
				lieux.add(new Lieu(id,adresse, ville));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return lieux;
	}

	
	public boolean supprimerLieu(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_LIEUX_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateLieu(Lieu lieu) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_LIEUX_SQL);) {
			System.out.println("updated Lieu:"+statement);
			statement.setString(1, lieu.getAdresse());
			statement.setString(2, lieu.getVille());
			statement.setInt(3, lieu.getId());
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
