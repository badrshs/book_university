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
	public boolean isStringInt(String s)
	{
	    try
	    {
	        Integer.parseInt(s);
	        return true;
	    } catch (NumberFormatException ex)
	    {
	        return false;
	    }
	}
	public ResultSet createReaderStatement(String query, List<String> list) {
		try {
			PreparedStatement statement = connect.prepareStatement(query);
			for (int i = 0; i < list.size(); i++) {
				String val = list.get(i);
				if(isStringInt(val)) {
					statement.setInt(i + 1, Integer.valueOf(val));
				}else
				statement.setString(i + 1, val);
			}
			System.out.println(statement);
			System.err.println("************************************************");

			ResultSet resultSet = statement.executeQuery();
			return resultSet;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public boolean createUpdaterStatement(String query, List<String> list) {
		// https://stackoverflow.com/questions/9516625/prevent-sql-injection-attacks-in-a-java-program
		// check this before , i may create new class that do it automaticly for me .
		// #TODO: use prepare statement before uploading this project ( sql
		// injection )
		try {
			PreparedStatement statement = connect.prepareStatement(query);
			System.out.println(query);
			for (int i = 0; i < list.size(); i++) {
				String x = list.get(i);
				statement.setString(i + 1, x);
			}
			System.out.println(statement);
			System.err.println("************************************************");
			return statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println(query);
			return false;
		}

	}

}
