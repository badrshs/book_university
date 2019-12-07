package backend.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.omg.CORBA.Any;

public interface IModel<Base> {
	
	public   String Queury = "";
 	
	IModel where(String columns,String condition,String value);
	IModel and();
	IModel or();
	Object first() throws  ClassNotFoundException;;
	Object find(int id) throws  ClassNotFoundException;
	Object get() throws SQLException, ClassNotFoundException;
	Object delete(int id) throws SQLException, ClassNotFoundException;
	Object update() throws SQLException, ClassNotFoundException;
	
	
	
	
}
