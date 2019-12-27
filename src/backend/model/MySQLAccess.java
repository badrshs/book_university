package backend.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

public class MySQLAccess {
	private static Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public MySQLAccess() {
		if (connect == null)
			ConnectToDatabase();
	}

	private void ConnectToDatabase() {

		try {

			connect = DriverManager.getConnection(
					"jdbc:mysql://localhost/books?user=root&password=&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}

	public ResultSet createReaderStatement(String query, List<String> list) throws SQLException {
		PreparedStatement statement = connect.prepareStatement(query);
		for (int i = 0; i < list.size(); i++) {
			String x = list.get(i);
			statement.setString(i + 1, x);
		}
		ResultSet resultSet = statement.executeQuery();
		return resultSet;
	}

	public boolean createUpdaterStatement(String query, List<String> list) throws SQLException {
		// https://stackoverflow.com/questions/9516625/prevent-sql-injection-attacks-in-a-java-program
		// check this before , i may create new class that do it automaticly for me .
		// don't forget to use prepare statement before uploading this project ( sql
		// injection )
		PreparedStatement statement = connect.prepareStatement(query);
		System.out.println(query);
		for (int i = 0; i < list.size(); i++) {
			String x = list.get(i);
			statement.setString(i + 1, x);
		}

		return statement.execute();
	}

}
