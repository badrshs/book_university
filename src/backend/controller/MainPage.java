package backend.controller;

import javax.swing.JPanel;

import backend.entity.Book;
import backend.entity.Category;
import backend.model._Book;
import backend.model._Category;
import gui.BooksListGui;
import gui.CategoriesListGui;
import gui.MainPageGui;
import gui.SingleBookDetails;

public class MainPage {
	public static MainPageGui mainPageGui;

	public static void ShowMainPageController() {
		try {
			_Category _category = new _Category();
			Category[] categories = (Category[]) _category.get();
			mainPageGui = new MainPageGui();
			MainPageGui.jPanel.add(new CategoriesListGui(categories));	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void ShowBooksPage(Category category) {
		mainPageGui = new MainPageGui();
		MainPageGui.jPanel.add(new BooksListGui(category));	

	}

	public static void ShowSingleBook(Book Ibook) {
		
		try {
			_Book _book = new _Book();
			Book[] book = (Book[]) _book.find(Ibook.id);
			mainPageGui = new MainPageGui();
			MainPageGui.jPanel.add(new SingleBookDetails(book[0]));	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
