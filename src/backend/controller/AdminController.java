package backend.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import backend.entity.Book;
import backend.entity.Category;
import backend.model._Book;
import backend.model._Category;
import gui.AddCategory;
import gui.EditCategory;
import gui.ManageBook;

public class AdminController {

	public static void editCategory(Category category) {

		EditCategory x = new EditCategory(category);
	}

	public static void AddCategory() {

		AddCategory x = new AddCategory();
	}

	public static void EditBook(Book book) {

		ManageBook x = new ManageBook(book);
	}

	public static boolean updateCategory(String text, int id) {
		try {
			_Category _category = new _Category();
			Map<String, String> data = new HashMap<>();
			data.put("name", text);
			_category.update(data, id);
			MainPage.ShowMainPageController();
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean DeleteCategory(int id) {

		try {
			_Category _category = new _Category();
			_category.delete(id);
			MainPage.ShowMainPageController();
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean CreateCategory(String text) {
		try {
			_Category _category = new _Category();
			Map<String, String> data = new HashMap<>();
			data.put("name", text);
			_category.create(data);
			MainPage.ShowMainPageController();
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void UpdateBook(Map<String, String> data, Book book) {
		try {
			_Book _book = new _Book();
			_book.update(data, book.id);
			MainPage.ShowBooksPage(getCategory(book.category_id));
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	private static Category getCategory(int id) {
		try {
			_Category _category = new _Category();
			Category[] categories = (Category[]) _category.find(id);
			return categories[0];
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
