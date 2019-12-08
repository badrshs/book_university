package backend.model;
import java.lang.reflect.Field;

import backend.entity.*;

public class _Category extends Model {

	public _Category() throws ClassNotFoundException {
		Field[] fields = Category.class.getFields();
		init(fields);
	}
	
	

	
}
