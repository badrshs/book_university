package backend.model;
import java.lang.reflect.Field;

import backend.entity.CartItems;

public class _CartItems extends Model{

 	
	public _CartItems()  {
		table ="Cart";
		Field[] fields = CartItems.class.getFields();
		init(fields);
	}
}
