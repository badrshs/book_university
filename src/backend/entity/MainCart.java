package backend.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import backend.model._Book;
import backend.model._Cart;

public class MainCart {

	public  static  ArrayList<CartItems> cartItems = new ArrayList<CartItems>();

	private static boolean lockCheckForDublicate = false;

 

	public static void addNewItem(CartItems item) {
		cartItems.add(item);
	}

	public static void addNewItemByProduct(Book book, int quantity) {
		if (!SolveDublicated(book, quantity)) {
			CartItems cartItem = new CartItems(book, quantity);
			cartItems.add(cartItem);
		}
	}

	public static void RefreshUserIdAfterSuccessfullyLogin() {
		for (CartItems item : cartItems) {
			item.user_id = Auth.users.id;
		}
		getUserCartList();
	}

	private static void getUserCartList() {
		if (!Auth.isAuth) {
			return;
		}
		try {
			_Cart _cart = new _Cart();
			CartItems[] cart = (CartItems[]) _cart.where("user_id", "=", Auth.users.id).get();
			String list = "";
			for (CartItems item : cart) {
				if (list.length() > 0)
					list += ',' + item.book_id;
				else {
					list += item.book_id;
				}
			}
			lockCheckForDublicate = true;
			Book[] books = getBookByList(list);
			for (CartItems item : cart) {
				Book x = search(books, item.book_id);
				addNewItemByProduct(x, item.quantity);
			}
			lockCheckForDublicate = false;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static Book search(Book[] books, int id) {
		for (Book item : books) {
			if (item.id == id) {
				return item;
			} 
		}
		return null;
	}

	private static Book[] getBookByList(String list) { // انا وقفت عند اضافة غرض عالسلة شكلو في مشكلة راجع الديبوج تصبح عخير
		try {
			_Book _books = new _Book();
			return (Book[]) _books.where("id", " IN ", "(" + list + ")").get();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static void RefreshUserIdAfterSuccessfullyLogout() {
		for (CartItems item : cartItems) {
			item.user_id = -1;
			item.RemoveUser();
		}
	}

	private static boolean SolveDublicated(Book book, int quantity) {
		if (lockCheckForDublicate) {
			return true;
		}
		for (CartItems item : cartItems) {
			if (item.book_id == book.id) {
				item.quantity += quantity;
				return true;
			}
		}
		return false;
	}
}
