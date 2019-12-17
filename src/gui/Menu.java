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

import backend.controller.MainPage;
import backend.entity.Auth;

public class Menu extends JMenuBar {
	class JMenuButton extends JButton {
		JMenuButton(String c) {
			super(c);
			setFont(new Font("Arial", Font.PLAIN, 22));
		}
	}

	JMenu menu;
	JMenuButton login, mainPage, exit, cart, categoryList;
	JMenuItem logout, userInfo;
	public Menu() {
		setPreferredSize(new Dimension(100, 40));
		generateUserInfo();
		generateLogin();
		generateCategoriesList();
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
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		});
		add(exit);
	}



	private void generateLogin() {
		if (!Auth.isAuth) {
			login = new JMenuButton("Login");
			login.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MainPage.ShowLoginPage();}
			});
			add(login);
		}
	}

	private void generateUserInfo() {
		if (Auth.isAuth) {
			menu = new JMenu(Auth.users.name + " " + Auth.users.surname);
			menu.setFont(new Font("Arial", Font.PLAIN, 22));

			userInfo = new JMenuItem("My Information");
			userInfo.setFont(new Font("Arial", Font.PLAIN, 18));
			userInfo.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					MainPage.ShowUserInformationPage();
				}
			});

			logout = new JMenuItem("Logout");
			logout.setFont(new Font("Arial", Font.PLAIN, 18));
			logout.addActionListener(new ActionListener() {

				@Override
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
