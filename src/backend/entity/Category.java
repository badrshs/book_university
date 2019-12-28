package backend.entity;

import java.util.Arrays;
import backend.model.*;

public class Category {

	public int id;
	public String name;
	private Book[] books;

	public Category() {
		super();

	}

	private Book[] AppendCategoryName(Book[] books) {
		for (Book book : books) {
			book.SetCategoryName(name);
		}
		return books;
	}

	public Book[] GetBooks() {
		_Book _book = new _Book();
		Book[] books = (Book[]) _book.where("category_id", "=", Integer.toString(id)).get();
		books = AppendCategoryName(books);
		if (books != null) {
			return books;
		}
		return null;
	}

	@Override
	public String toString() {
		return name;
	}
}
