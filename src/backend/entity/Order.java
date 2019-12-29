package backend.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import backend.controller.Helper;
import backend.model.*;

public class Order {

	public int id;
	public int user_id;
	public String created_at;
	private boolean status = false;
	private ArrayList<CartItems> cartItems;

	public Order() {
		cartItems = MainCart.cartItems;
		checkStatus();

	}

	private void checkStatus() {
		boolean status = true;
		for (CartItems items : cartItems) {
			if (!checkIfInStock(items)) {
				status = false;
			}
		}
		this.status = status;
	}

	public boolean StartPurchase() {
		if (!status)
			return false; // break here and refresh items again
		init();

		for (CartItems items : cartItems) {
			Purchase(items);
		}
		return true;
	}

	private void Purchase(CartItems cartItems) {
		_OrderItems _order = new _OrderItems();
		Map<String, String> data = new HashMap<>();
		data.put("order_id", Integer.toString(id));
		data.put("total_price", Integer.toString(cartItems.get_Book().price * cartItems.quantity));
		data.put("book_id", Integer.toString(cartItems.book_id));
		data.put("quantity", Integer.toString(cartItems.quantity));
		_order.create(data);
		updateInStock(cartItems);
	}

	private void updateInStock(CartItems cartItems) {
		_Book _book = new _Book();
		Map<String, String> data = new HashMap<>();
		data.put("in_stock", Integer.toString(cartItems.get_Book().in_stock - cartItems.quantity));
		_book.update(data, cartItems.book_id);
	}

	private void init() {
		if (status) {
			Order O = CreateNewOrder();
			this.id = O.id;
			this.user_id = O.user_id;
		}
	}

	public Order CreateNewOrder() {
		_Order _order = new _Order();
		Map<String, String> data = new HashMap<>();
		data.put("user_id", Integer.toString(Auth.id()));
		_order.create(data);
		_order = new _Order();
		Order[] order = (Order[]) _order.where("id", "=", Auth.id()).orderBy("DESC").first();
		return order[0];
	}

	private static boolean checkIfInStock(CartItems item) {
		_Book _book = new _Book();
		Book books[] = (Book[]) _book.find(item.book_id);
		return books[0].in_stock >= item.quantity;
	}

	public boolean verify(String cardNumber,String cvv) {
		_Users _user = new _Users();
		Users[] users = (Users[]) _user.where("card_number", "=", Helper.encryption(cardNumber)).and()
				.where("cvv_code", "=", Helper.encryption(cvv)).and().where("id","=", Auth.id()).first();
		
 		return users.length>0;
	}
}
