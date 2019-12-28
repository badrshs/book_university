package backend.model;
import java.lang.reflect.Field;
import backend.entity.*;

public class _Book extends Model {
	public _Book()  {
		Field[] fields = Book.class.getFields();
		init(fields);
	}
}
