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
		_Category _category = new _Category();
		Map<String, String> data = new HashMap<>();
		data.put("name", text);
		_category.update(data, id);
		Router.ShowCategoriesList();
		return true;
	}

	public static boolean DeleteCategory(int id) {

		_Category _category = new _Category();
		_category.delete(id);
		Router.ShowCategoriesList();
		return true;
	}

	public static boolean CreateCategory(String text) {
		_Category _category = new _Category();
		Map<String, String> data = new HashMap<>();
		data.put("name", text);
		_category.create(data);
		Router.ShowCategoriesList();
		return true;
	}

	public static void UpdateBook(Map<String, String> data, Book book) {
		_Book _book = new _Book();
		_book.update(data, book.id);
		Router.ShowBooksPage(getCategory(book.category_id));
	}

	private static Category getCategory(int id) {
		_Category _category = new _Category();
		Category[] categories = (Category[]) _category.find(id);
		return categories[0];
	}
}
