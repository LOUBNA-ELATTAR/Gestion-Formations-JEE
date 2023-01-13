package com.tp2.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.tp2.model.Formation;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;


public class FormationDao {
	private String jdbcURL = "jdbc:mysql://localhost:3308/tp2?useSSL=false&serverTimezone=UTC";
	private String jdbcUsername = "root";
	private String jdbcPassword = "test";
	private String jdbcDriver = "com.mysql.cj.jdbc.Driver";
	
	private static final String INSERT_FORMATIONS_SQL = "INSERT INTO formations" + "  (theme, lieu) VALUES "
			+ " (?, ?);";

	private static final String SELECT_FORMATIONS_BY_ID = "select theme, lieu from formations where id =?";
	private static final String SELECT_ALL_FORMATIONS = "select * from formations";
	private static final String DELETE_FORMATIONS_SQL = "delete from formations where id = ?;";
	private static final String UPDATE_FORMATIONS_SQL = "update formations set theme = ?, lieu = ? where id = ?;";

	public FormationDao() {
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
	public void insererFormation(Formation formation) throws SQLException {
		System.out.println(INSERT_FORMATIONS_SQL);
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FORMATIONS_SQL)) {
			preparedStatement.setString(1, formation.getTheme());
			preparedStatement.setString(2, formation.getLieu());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Formation selectionnerFormation(int id) {
		Formation formation = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FORMATIONS_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String theme = rs.getString("theme");
				String lieu = rs.getString("lieu");
				formation = new Formation(theme, lieu);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return formation;
	}

	public List<Formation> selectAllFormations() {

		List<Formation> formations = new ArrayList<>();
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FORMATIONS);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String theme = rs.getString("theme");
				String lieu = rs.getString("lieu");
				formations.add(new Formation(id,theme,lieu));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return formations;
	}

	public boolean supprimerFormation(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_FORMATIONS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateFormation(Formation formation) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_FORMATIONS_SQL);) {
			System.out.println("updated Formation:"+statement);
			statement.setString(1, formation.getTheme());
			statement.setString(2, formation.getLieu());
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
