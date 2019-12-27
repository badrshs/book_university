package backend.controller;

import java.awt.Color;

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
			 new Auth("shs1bader@gmail.com","shs1bader@gmail.com").check();
			_Category _category = new _Category();
			Category[] categories = (Category[]) _category.get();
			if (mainPageGui == null) {
				mainPageGui = new MainPageGui();

			}

			MainPageGui.jPanel.removeAll();
			MainPageGui.jPanel.add(new CategoriesListGui(categories));
			Menu menu = new Menu(0);
			menu.addCategoryStatus = true;
			menu.generateMenue();
			mainPageGui.generateMenue(menu);
			mainPageGui.revalidate();
			mainPageGui.repaint();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void ShowBooksPage(Category category) {
		MainPageGui.jPanel.removeAll();
		mainPageGui.generateMenue(new Menu());
		mainPageGui.repaint();

		MainPageGui.jPanel.add(new BooksListGui(category));
	}

	public static void ShowLoginPage() {
		MainPageGui.jPanel.removeAll();
		mainPageGui.repaint();
		MainPageGui.jPanel.add(new Login());
	}

	public static void ShowUserInformationPage() {
		MainPageGui.jPanel.removeAll();
		mainPageGui.repaint();
		UserInformation info = new UserInformation();
		info.setBounds(500, 0, 800, 800);
		MainPageGui.jPanel.add(info);
	}

	public static void ShowSingleBook(Book Ibook) {
		try {
			_Book _book = new _Book();
			Book[] book = (Book[]) _book.find(Ibook.id);
			MainPageGui.jPanel.removeAll();
			mainPageGui.repaint();
			MainPageGui.jPanel.add(new SingleBookDetails(book[0]));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void ShowCartDetails() {
		mainPageGui = new MainPageGui();

		CartGui cart = new CartGui();
		cart.setBounds(10, 10, 800, 1000);
		MainPageGui.jPanel.add(cart);
		mainPageGui.repaint();

	}

}
