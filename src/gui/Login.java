package gui;

import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import backend.controller.Helper;
import backend.controller.MainPage;
import backend.entity.Auth;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Button;
import javax.swing.JButton;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JPanel {
	private JTextField username;
	private JTextField password;

	/**
	 * Create the panel.
	 */
	public Login() {
		setBounds(12, 100, 1600, 800);
		setLayout(null);
		
		Panel panel = new Panel();
		panel.setBounds(465, 70, 544, 309);
		add(panel);
		panel.setLayout(null);
		
		JLabel Email = new JLabel("Email");
		Email.setBounds(34, 58, 76, 64);
		panel.add(Email);
		Email.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Email.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel Password = new JLabel("Password");
		Password.setBounds(29, 143, 98, 64);
		panel.add(Password);
		Password.setHorizontalAlignment(SwingConstants.LEFT);
		Password.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		username = new JTextField();
		username.setFont(new Font("Tahoma", Font.PLAIN, 17));
		username.setBounds(150, 60, 319, 50);
		panel.add(username);
		username.setColumns(10);
		
		password = new JPasswordField();
		password.setFont(new Font("Tahoma", Font.PLAIN, 17));
		password.setBounds(149, 151, 319, 50);
		panel.add(password);
		password.setColumns(10);
		
		JButton login = new JButton("Login ");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Auth process = new Auth(username.getText(),password.getText());	
				
				if(!process.check()) {
						Helper.showError("Wrong Username Or Password");
				}else {
					MainPage.ShowMainPageController();

				}
			}
		});
		login.setBounds(197, 232, 173, 64);
		panel.add(login);
		login.setFont(new Font("Tahoma", Font.PLAIN, 25));
		
		add(panel);
	}
}
