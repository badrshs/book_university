package backend.controller;

import backend.entity.Auth;
import backend.entity.Book;
import backend.entity.Category;
import backend.model._Book;
import backend.model._Category;
import gui.BooksListGui;
import gui.CartGui;
import gui.CategoriesListGui;
import gui.Login;
import gui.MainPageGui;
import gui.Menu;
import gui.SingleBookDetails;
import gui.UserInformation;

public class MainPage {
	
	public static MainPageGui mainPageGui;

	public static void ShowMainPageController() {
		try {
			//new Auth("shs1bader2@gmail.com","shs1bader2@gmail.com").check();
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

	public static void ShowLoginPage() {
		mainPageGui = new MainPageGui();
		MainPageGui.jPanel.add(new Login());
	}
	public static void ShowUserInformationPage() {
		mainPageGui = new MainPageGui();
		UserInformation info =	new UserInformation();
		info.setBounds(500, 0, 800, 800);
		MainPageGui.jPanel.add(info);
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

	public static void ShowCartDetails() {
		mainPageGui = new MainPageGui();
		CartGui cart =	new CartGui();
		cart.setBounds(500, 0, 800, 800);
		MainPageGui.jPanel.add(cart);
	}

}
