package backend.model;


import java.lang.reflect.Field;

import backend.entity.Users;

public class _Users extends Model {
	
	
	public _Users()  {
		Field[] fields = Users.class.getFields();
		init(fields);
	}




}
