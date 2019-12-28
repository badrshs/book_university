package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import backend.controller.AdminController;
import backend.controller.MainPage;
import backend.entity.Auth;
import backend.entity.MainCart;

public class Menu extends JMenuBar {
	class JMenuButton extends JButton {
		JMenuButton(String c) {
			super(c);
			setFont(new Font("Arial", Font.PLAIN, 22));
		}
	}

	JMenu menu;
	public  boolean addCategoryStatus = false;
	public static JMenuButton login, mainPage, exit, cart, categoryList, addCategory;
	JMenuItem logout, userInfo;

	public Menu() {
		generateMenue();
	}

	public Menu(int fresh) {
	}
	public void generateMenue() {
		setPreferredSize(new Dimension(100, 40));
		generateUserInfo();
		generateLogin();
		generateCategoriesList();
		generateCart();
		if(addCategoryStatus) {
			generateAddCategory();
		}
		generateExit();
	}

	private void generateCategoriesList() {
		categoryList = new JMenuButton("CategoryList");
		categoryList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage.ShowMainPageController();
			}
		});
		add(categoryList);
	}

	private void generateExit() {
		exit = new JMenuButton("Exit");
		exit.setBounds(1000, 0, 100, 40);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		add(exit);
	}

	private void generateCart() {
		String number = "( "+MainCart.cartItems.size()+" )";
		
		cart = new JMenuButton("My Cart "+number);
		cart.setBounds(1000, 0, 100, 40);
		cart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainPage.ShowCartDetails();

			}
		});
		add(cart);
	}
	private void generateLogin() {
		if (!Auth.isAuth) {
			login = new JMenuButton("Login");
			login.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MainPage.ShowLoginPage();
				}
			});
			add(login);
		}
	}

	public void generateAddCategory() {
		if (Auth.isAdmin()) {
			addCategory = new JMenuButton("Add New Category");
			addCategory.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					AdminController.AddCategory();
				}
			});
			add(addCategory);
		}
	}

	private void generateUserInfo() {
		if (Auth.isAuth) {
			menu = new JMenu(Auth.users.name + " " + Auth.users.surname);
			menu.setFont(new Font("Arial", Font.PLAIN, 22));
			userInfo = new JMenuItem("My Information");
			userInfo.setFont(new Font("Arial", Font.PLAIN, 18));
			userInfo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MainPage.ShowUserInformationPage();
				}
			});
			logout = new JMenuItem("Logout");
			logout.setFont(new Font("Arial", Font.PLAIN, 18));
			logout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Auth.logout();
				}
			});
			menu.add(userInfo);
			menu.add(logout);
			add(menu);
		}
	}

}
