package backend.model;

import java.lang.reflect.Field;

import backend.entity.Order;

public class _Order  extends Model{

	
	public _Order()  {
		table = "main_order";
		Field[] fields = Order.class.getFields();
		init(fields);
	}


}
