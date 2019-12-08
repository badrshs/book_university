package backend.model;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.text.html.parser.Entity;

import org.json.simple.*;
import com.google.gson.Gson;

import backend.entity.Users;

public class Model implements IModel {

	public List<String> fillable = new ArrayList<String>();;
	protected String table;
	private String selectedColumns = " * ";
	private String where = "";
	private String tail = "";
	private MySQLAccess db;

	public Model() {
		if (table == null)
			setTableByClassName();
		generateQuery();
		OpenDatabaseConnection();
	}

	public void setColumns(Field[] fields) {
		for (Field field : fields) {
			String n = field.getName();// this may case an issue
			if (n.substring(1) != "_") {
				fillable.add(n);
			}
		}
		selectedColumns = String.join(" , ", fillable); // generate columns names
	}

	public void init(Field[] fields) throws ClassNotFoundException {
		setColumns(fields);
	}

	private void OpenDatabaseConnection() {
		db = new MySQLAccess();
	}

	void setTableByClassName() {
		table = this.getClass().getSimpleName().toLowerCase().replace("_", "");
	}

	private String generateQuery() {
		return "select " + selectedColumns + " from " + table + " " + where + " ";
	}
	
	public Model where(String columns, String condition, String value) {
		where += "where " + columns + " " + condition + " " + value;
		generateQuery();
		return this;
	}

	public Model and() {
		where += " and ";
		generateQuery();
		return this;
	}

	public Model or() {
		where += " OR ";
		generateQuery();
		return this;
	}

	public Object first() throws ClassNotFoundException {
		tail += " limit 1";
		return get();
	}

	public Object find(int id) throws ClassNotFoundException {
		where("id", "=", Integer.toString(id));
		return get();
	}

	public Object get() throws ClassNotFoundException {
		ArrayList<JSONObject> arr = new ArrayList<JSONObject>();
		try {
			String query = generateQuery();
			System.out.println(" Test :  " + query);

			ResultSet resultSet = db.createReaderStatement(query);
			while (resultSet.next()) {
				JSONObject array = new JSONObject();
				fillable.forEach((v) -> {
					try {
						array.put(v, resultSet.getString(v));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
				arr.add(array);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return  new Gson().fromJson(arr.toString(),
				Class.forName("[Lbackend.entity." + getClass().getName().replace("backend.model._", "") + ";"));
	}

	@Override
	public String toString() {
		return generateQuery();
	}

	@Override
	public Object delete(int id, String column) throws SQLException, ClassNotFoundException {
		String query = "delete from " + table + " where " + column + " = " + id;
		boolean result = db.createUpdaterStatement(query);
		if (result) {
			System.out.println(query + " deleted successfully");
		}
		return result;
	}

	@Override
	public Object delete(int id) throws SQLException, ClassNotFoundException {
		String query = "delete from " + table + " where id = " + id;
		boolean result = db.createUpdaterStatement(query);
		if (result) {
			System.out.println(query + " deleted successfully");
		}
		return result;
	}

	@Override
	public Object update() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	private String CreateUpdateSqlStatment(Map<String, String> names, int id) {
		String query = "";
		for (Map.Entry<String, String> entry : names.entrySet()) {
			query += '`' + entry.getKey() + "` = " + "'" + entry.getValue() + "' ,";
		}
		query = deleteLastCharacter(query);
		query = "UPDATE `" + table + "` SET " + query + " WHERE `" + table + "`.`id` = " + id + ";";
		return query;
	}

	private String CreateInsertSqlStatment(Map<String, String> names) {
		String keys = "";
		String values = "";
		for (Map.Entry<String, String> entry : names.entrySet()) {
			keys += '`' + entry.getKey() + "` ,";
			values += "'" + entry.getValue() + "' ,";
		}
		keys = deleteLastCharacter(keys);
		values = deleteLastCharacter(values);

		return "INSERT INTO `users`(" + keys + ") VALUES (" + values + ")";
	}

	public Object create(Map<String, String> names) throws SQLException, ClassNotFoundException {
		String query = CreateInsertSqlStatment(names);
		System.out.println(query);
		boolean result = db.createUpdaterStatement(query);
		return result;
	}

	public Object update(Map<String, String> names, int id) throws SQLException, ClassNotFoundException {
		String query = CreateUpdateSqlStatment(names, id);
		System.out.println(query);
		boolean result = db.createUpdaterStatement(query);
		if (result) {
			System.out.println(query + " added successfully");
		}
		return result;
	}

	private String deleteLastCharacter(String string) {
		String result = null;
		if ((string != null) && (string.length() > 0)) {
			result = string.substring(0, string.length() - 1);
		}
		return result;
	}
}
