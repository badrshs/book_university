package backend.model;

import java.lang.reflect.Field;

import backend.entity.Category;
import backend.entity.OrderItems;

public class _OrderItems extends Model {
	public _OrderItems() {
		table = "order_items";
		Field[] fields = OrderItems.class.getFields();
		init(fields);
	}
}

