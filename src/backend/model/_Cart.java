package backend.model;
import java.lang.reflect.Field;
import backend.entity.CartItems;

public class _Cart extends Model{
	public _Cart() throws ClassNotFoundException {
		Field[] fields = CartItems.class.getFields();
		init(fields);
	}
	
	
}
