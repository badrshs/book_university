package backend.model;
import java.lang.reflect.Field;
import backend.entity.*;

public class _Book extends Model {
	public _Book() throws ClassNotFoundException {
		Field[] fields = Book.class.getFields();
		init(fields);
	}
}
