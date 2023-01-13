package com.tp2.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.tp2.model.Formateur;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;


public class FormateurDao {
	private String jdbcURL = "jdbc:mysql://localhost:3308/tp2?useSSL=false&serverTimezone=UTC";
	private String jdbcUsername = "root";
	private String jdbcPassword = "test";
	private String jdbcDriver = "com.mysql.cj.jdbc.Driver";
	
	private static final String INSERT_FORMATEURS_SQL = "INSERT INTO formateurs" + "  (cin, nom, age) VALUES "
			+ " (?, ?, ?);";

	private static final String SELECT_FORMATEURS_BY_ID = "select cin, nom, age from formateurs where id =?";
	private static final String SELECT_ALL_FORMATEURS = "select * from formateurs";
	private static final String DELETE_FORMATEURS_SQL = "delete from formateurs where id = ?;";
	private static final String UPDATE_FORMATEURS_SQL = "update formateurs set cin = ?, nom= ?, age =? where id = ?;";

	public FormateurDao() {
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
	public void insererFormateur(Formateur formateur) throws SQLException {
		System.out.println(INSERT_FORMATEURS_SQL);
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FORMATEURS_SQL)) {
			preparedStatement.setString(1, formateur.getCin());
			preparedStatement.setString(2, formateur.getNom());
			preparedStatement.setInt(3, formateur.getAge());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public Formateur selectionnerFormateur(int id) {
		Formateur formateur = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FORMATEURS_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String cin = rs.getString("cin");
				String nom = rs.getString("nom");
				int age = rs.getInt("age");
				formateur = new Formateur(cin, nom, age);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return formateur;
	}

	public List<Formateur> selectAllFormateurs() {

		List<Formateur> formateurs = new ArrayList<>();
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FORMATEURS);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String cin = rs.getString("cin");
				String nom = rs.getString("nom");
				int age = rs.getInt("age");
				formateurs.add(new Formateur(id,cin, nom, age));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return formateurs;
	}

	public boolean supprimerFormateur(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_FORMATEURS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateFormateur(Formateur formateur) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_FORMATEURS_SQL);) {
			System.out.println("updated Formateur:"+statement);
			statement.setString(1, formateur.getCin());
			statement.setString(2, formateur.getNom());
			statement.setInt(3, formateur.getAge());
			statement.setInt(4, formateur.getId());

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
