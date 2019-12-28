package backend.model;

import java.sql.ResultSet;
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
	Object first();
	Object find(int id);
	Object get();
	boolean delete(int id);
	Object delete(int id,String column);
 	
	
	
	
}
