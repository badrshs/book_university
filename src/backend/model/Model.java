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
import com.google.gson.JsonSyntaxException;

import backend.entity.Users;

public class Model implements IModel {

	public List<String> fillable = new ArrayList<String>();;
	protected String table;
	private String selectedColumns = " * ";
	private String where = "";
	private String tail = "";
	private MySQLAccess db;
	private List<String> values = new ArrayList<String>();;

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

	public void init(Field[] fields) {
		setColumns(fields);
	}

	private void OpenDatabaseConnection() {
		db = new MySQLAccess();
	}

	void setTableByClassName() {
		table = this.getClass().getSimpleName().toLowerCase().replace("_", "");
	}

	private String generateQuery() {
		return "select " + selectedColumns + " from " + table + " " + where + " "+tail;
	}

	public Model where(String columns, String condition, String value) {
		if (!where.contains("where"))
			where += " where ";
		values.add(value);
		where += " " + columns + " " + condition + "( ? )";
	String query =	generateQuery();
		return this;
	}

	public Model whereIn(String columns, String list) {
		if (!where.contains("where"))
			where += " where ";
		String[] Ids = list.split(",");
		String keys = "";

		for (String id : Ids) {
			values.add(id);
			keys = keys + "?,";
		}
		keys = deleteLastCharacter(keys);
		where += " " + columns + " " + "IN" + "( " + keys + " )";
		generateQuery();
		return this;
	}

	public Model where(String columns, String condition, int value) {
		return where(columns, condition, Integer.toString(value));
	}
	
	public Model and() {
		where += " and ";
		generateQuery();
		return this;
	}

	public Model orderBy(String orderBy) {
		tail += " ORDER BY id "+orderBy+" ";
		
		String c = generateQuery();
		return this;
	}

	public Model or() {
		where += " OR ";
		generateQuery();
		return this;
	}

	public Object first() {
		tail += " limit 1";
		return get();
	}

	public Object find(int id) {
		where("id", "=", Integer.toString(id));
		return get();
	}

	public Object get() {
		ArrayList<JSONObject> arr = new ArrayList<JSONObject>();
		String query = generateQuery();
		try {
			ResultSet resultSet = db.createReaderStatement(query, values);
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
			String model = "[Lbackend.entity." + getClass().getName().replace("backend.model._", "") + ";";
			return new Gson().fromJson(arr.toString(), Class.forName(model));

		} catch (SQLException | JsonSyntaxException | ClassNotFoundException e) {
			System.out.println(query);
			e.printStackTrace();
			return new Object();
		}
	}

	@Override
	public String toString() {
		return generateQuery();
	}

	public Object delete(int id, String column) {
		String query = "delete from " + table + " where " + column + " = ?";
		values.add(Integer.toString(id));
		boolean result = db.createUpdaterStatement(query, values);
		return result;
	}

	public boolean delete(int id) {
		String query = "delete from " + table + " where id = ?";
		values.add(Integer.toString(id));
		return db.createUpdaterStatement(query, values);
	}

	private String CreateUpdateSqlStatment(Map<String, String> names, int id) {
		String query = "";
		for (Map.Entry<String, String> entry : names.entrySet()) {
			query += '`' + entry.getKey() + "` = " + "?,";
			this.values.add(entry.getValue());
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
			values += "?,";
			this.values.add(entry.getValue());
		}
		keys = deleteLastCharacter(keys);
		values = deleteLastCharacter(values);
		return "INSERT INTO `" + table + "`(" + keys + ") VALUES (" + values + ")";
	}

	public Object create(Map<String, String> names) {
		String query = CreateInsertSqlStatment(names);
		boolean result = db.createUpdaterStatement(query, values);
		return result;
	}

	public Object update(Map<String, String> data, int id) {
		String query = CreateUpdateSqlStatment(data, id);
		boolean result = db.createUpdaterStatement(query, values);
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
