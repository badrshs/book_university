package backend.entity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import backend.model._Book;
import backend.model._CartItems;

public class MainCart {

	public static ArrayList<CartItems> cartItems = new ArrayList<CartItems>();

	private static boolean lockCheckForDublicate = false;

	public static void addNewItem(CartItems item) {
		cartItems.add(item);
	}

	public static void updateUserDatabaseCart() {
		if (!Auth.isAuth)
			return;
		deleteUserDatabaseCart(); // update the database, with the latest version of my cart( to make the database
		// up to date )
		Map<String, String> data = new HashMap<>();
		for (CartItems item : cartItems) {
			_CartItems _cart = new _CartItems();
			data.put("id", Integer.toString(item.id));
			data.put("user_id", Integer.toString(item.user_id));
			data.put("book_id", Integer.toString(item.book_id));
			data.put("quantity", Integer.toString(item.quantity));
			_cart.create(data);
			if (item.id <= 0) {
				item.id = getUserCartItemsByBookId(item.book_id).id; // to solve an issue when an old item has no
																		// primary id
			}
		}
	}

	public static CartItems getUserCartItemsByBookId(int book_id) {
		if (!Auth.isAuth)
			return null;
		_CartItems _cart = new _CartItems();
		CartItems[] cart = (CartItems[]) _cart.where("user_id", "=", Auth.users.id).and().where("book_id", "=", book_id)
				.first();
		return cart[0];
	}

	public static void RefreshUserIdAfterSuccessfullyLogout() { // after logout , I should remove everything related to
																// the user from here
		for (CartItems item : cartItems) {
			item.user_id = -1;
			item.RemoveUser();
		}
	}

	public static void RefreshUserIdAfterSuccessfullyLogin() { // when successfully change userId for all the previous
																// added item
		for (CartItems item : cartItems) {
			item.user_id = Auth.users.id;
		}
		ReSyncCartItem(); // after login
	}

	public static void ReSyncCartItem() {
		if (!Auth.isAuth) {
			return;
		}
		_CartItems _cart = new _CartItems();
		CartItems[] cart = (CartItems[]) _cart.where("user_id", "=", Auth.users.id).get(); // get user cart items from
																							// database
		if (cart == null)
			return;
		String list = "";
		for (CartItems item : cart) { // get books id details from database based on book ids I recived from cart
										// database
			if (list.length() > 0)
				list = list + "," + item.book_id;
			else {
				list += item.book_id;
			}
		}

		lockCheckForDublicate = true;
		Book[] books = getBookByList(list); // get all books from database in one query by using IN
		for (CartItems item : cart) { // AT THIS MOMENT most of cart item properties is null, so refill again
			Book book = search(books, item.book_id); // search on specific book
			if (book.in_stock < item.quantity)
				item.quantity = book.in_stock;
			if (book.in_stock == 0)
				continue;
			UpdateCartItemFromDatabase(book, item); // add new book item to the local cart
		}
		updateUserDatabaseCart();

		lockCheckForDublicate = false;
	}

	private static void UpdateCartItemFromDatabase(Book book, CartItems item) {
		if (!SolveDublicated(book, item.quantity, false)) {
			CartItems cartItem = new CartItems(book, item);
			cartItems.add(cartItem);
		}
	}

	public static void addNewItemByProduct(Book book, int quantity) { // call outside the class
		book = getBookById(book.id); // check if the quantity less than or equal in stock, in case someone else
										// bought it before adding the item to the basket
		if (book.in_stock < quantity)
			quantity = book.in_stock;
		if (!SolveDublicated(book, quantity, true)) {
			CartItems cartItem = new CartItems(book, quantity);
			cartItems.add(cartItem);
			updateUserDatabaseCart();
		}
	}

	public static Book getBookById(int id) {
		_Book _books = new _Book();
		Book[] books = (Book[]) _books.find(id);
		return books[0];
	}

	private static boolean SolveDublicated(Book book, int quantity, boolean update_database) {

		for (CartItems item : cartItems) {
			if (item.book_id == book.id) {
				if (lockCheckForDublicate) { // to solve the conflict after login, so if i have same item in
					// database and in local with different quantity so we should ignore the
					// database and use the local
					return true;
				}
				item.quantity += quantity;
				if (update_database)
					updateUserDatabaseCart();
				return true;
			}
		}
		return false;
	}

	private static Book[] getBookByList(String list) { // انا وقفت عند اضافة غرض عالسلة شكلو في مشكلة راجع الديبوج تصبح
		if (list == "")
			return null;
		_Book _books = new _Book();
		Book[] books = (Book[]) _books.whereIn("id", list).get();
		return books;
	}

	public static void deleteUserDatabaseCart() {
		if (!Auth.isAuth)
			return;
		_CartItems _cart = new _CartItems();
		_cart.delete(Auth.id(), "user_id");
	}

	private static Book search(Book[] books, int id) {
		for (Book item : books) {
			if (item.id == id) {
				return item;
			}
		}
		return null;
	}

	public static void deleteSpesificItem(int id) {
		ArrayList<CartItems> newItems = new ArrayList<CartItems>();

		for (CartItems item : cartItems) {
			if (id != item.book_id)
				newItems.add(item);
		}
		cartItems = newItems;
		updateUserDatabaseCart();
	}

	public static void UpdateValueOfSpesificItem(int quantity, int id) {
		if (!Auth.isAuth)
			return;
		_CartItems _cart = new _CartItems();
		Map<String, String> data = new HashMap<>();
		data.put("quantity", Integer.toString(quantity));
		_cart.update(data, id);
		
		for (CartItems item : cartItems) {
		if(item.id==id)
			item.quantity = quantity;
		}
		
	}

	public static String calculateTotalPrice() {
		double total = 0;
		for (CartItems item : cartItems) {
			total = total + (item.quantity * item.get_Book().price);
		}
		DecimalFormat df2 = new DecimalFormat("#.###");

		return df2.format(total);
	}

	public static void PurchaseOrder() {
		// TODO Auto-generated method stub
		
	}
}
