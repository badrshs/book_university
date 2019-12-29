package backend.controller;

import java.awt.Color;

import backend.entity.Auth;
import backend.entity.Book;
import backend.entity.Category;
import backend.entity.MainCart;
import backend.model._Book;
import backend.model._Category;
import gui.BooksListGui;
import gui.CartGui;
import gui.CategoriesListGui;
import gui.Login;
import gui.MainPageGui;
import gui.Menu;
import gui.Payment;
import gui.Register;
import gui.SingleBookDetails;
import gui.UserInformation;

public class Router {

	public static MainPageGui mainPageGui;

	public static void ShowCategoriesList() {
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
		Login login = new Login();
		login.setBounds(500, 175, 544, 309);
		MainPageGui.jPanel.add(login);
	}

	public static void ShowUserInformationPage() {
		MainPageGui.jPanel.removeAll();
		mainPageGui.repaint();
		UserInformation info = new UserInformation();
		info.setBounds(500, 0, 800, 800);
		MainPageGui.jPanel.add(info);
	}

	public static void ShowSingleBook(Book Ibook) {
		_Book _book = new _Book();
		Book[] book = (Book[]) _book.find(Ibook.id);
		MainPageGui.jPanel.removeAll();
		mainPageGui.repaint();
		MainPageGui.jPanel.add(new SingleBookDetails(book[0]));
	}

	public static void ShowCartDetails() {
		MainPageGui.jPanel.removeAll();
		Menu menu = new Menu(0);
		menu.addCategoryStatus = true;
		menu.generateMenue();
		mainPageGui.generateMenue(menu);
		MainPageGui.jPanel.add(new CartGui());		
		mainPageGui.repaint();
		mainPageGui.revalidate();
	}

	public static void ShowRegister() {
		MainPageGui.jPanel.removeAll();
		mainPageGui.repaint();
		Register register = new Register();
		register.setBounds(500, 25, 700, 584);
		MainPageGui.jPanel.add(register);
	}
}
