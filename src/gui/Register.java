package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import backend.controller.Helper;
import backend.controller.Router;
import backend.controller.Validation;
import backend.entity.Auth;

public class Register extends JPanel {
	private JTextField name;
	private JTextField surname;
	private JTextField email;
	private JPasswordField password;
	private JTextField card_numb;
	private JTextField cvv;
	private JPasswordField confirmPassword;

	/**
	 * Create the panel.
	 */
	public Register() {
		setBounds(12, 100, 700, 584);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(47, 37, 607, 447);
		add(panel);
		panel.setLayout(null);

		JLabel name_lbl = new JLabel("Name");
		name_lbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		name_lbl.setBounds(0, 13, 138, 55);
		panel.add(name_lbl);

		JLabel surname_lbl = new JLabel("Surname");
		surname_lbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		surname_lbl.setBounds(0, 82, 138, 55);
		panel.add(surname_lbl);

		JLabel email_lbl = new JLabel("Email");
		email_lbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		email_lbl.setBounds(0, 150, 138, 55);
		panel.add(email_lbl);

		JLabel password_lbl = new JLabel("Password");
		password_lbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		password_lbl.setBounds(0, 205, 138, 55);
		panel.add(password_lbl);

		JLabel card_number_lbl = new JLabel("Card Number");
		card_number_lbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		card_number_lbl.setBounds(0, 324, 138, 55);
		panel.add(card_number_lbl);

		JLabel cvv_code_lbl = new JLabel("CVV");
		cvv_code_lbl.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cvv_code_lbl.setBounds(0, 381, 138, 55);
		panel.add(cvv_code_lbl);

		name = new JTextField();
		name.setBounds(184, 22, 381, 42);
		panel.add(name);
		name.setColumns(10);

		surname = new JTextField();
		surname.setColumns(10);
		surname.setBounds(184, 84, 381, 42);
		panel.add(surname);

		email = new JTextField();
		email.setColumns(10);
		email.setBounds(184, 150, 381, 42);
		panel.add(email);

		password = new JPasswordField();
		password.setColumns(10);
		password.setBounds(184, 218, 381, 42);
		panel.add(password);

		card_numb = new JTextField();
		card_numb.setColumns(10);
		card_numb.setBounds(184, 337, 381, 42);
		panel.add(card_numb);

		cvv = new JTextField();
		cvv.setColumns(10);
		cvv.setBounds(184, 392, 381, 42);
		panel.add(cvv);

		JButton btnSave = new JButton("SAVE");
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 28));
		btnSave.setBounds(274, 496, 147, 75);

		add(btnSave);

		JLabel lblConfirmpassword = new JLabel("Confirm-Password");
		lblConfirmpassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblConfirmpassword.setBounds(0, 260, 160, 55);
		panel.add(lblConfirmpassword);

		confirmPassword = new JPasswordField();
		confirmPassword.setText((String) null);
		confirmPassword.setColumns(10);
		confirmPassword.setBounds(184, 273, 381, 42);
		panel.add(confirmPassword);

		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (validateInputs()) {
					Auth.CreateUser(name.getText(), surname.getText(), email.getText(), password.getText(),
							card_numb.getText(), cvv.getText());
					Helper.showSuccess("Your Account Created SUCCESSFULLY");
					Router.ShowCategoriesList();
				}

			}
		});

	}

	public boolean validateInputs() {

		if (!Validation.IsA_Z(name.getText())) {
			Helper.showError("  name " + name.getText() + " is not valid");
			return false;
		} else if (!Validation.IsA_Z(surname.getText())) {
			Helper.showError("  surname " + surname.getText() + " is not valid");
			return false;

		} else if (!Validation.IsEmail(email.getText())) {
			Helper.showError("  email is not valid");
			return false;

		} else if (!Validation.IsEmailTaken(email.getText())) {
			Helper.showError("email is taken");
			return false;

		} else if (password.getText().length() < 8) {
			Helper.showError("password must be at least 8 characters");
			return false;

		} else if (!password.getText().equals(confirmPassword.getText())) {
			Helper.showError("Password and confirm password does not match");
			return false;

		} else if ((card_numb.getText().length() == 0 && cvv.getText().length() == 0)) {
			return true;

		} else if (cvv.getText().length() != 3) {
			Helper.showError("cvv number must be 3 number");
			return false;

		} else if (card_numb.getText().length() != 16) {
			Helper.showError(" card number is not valid ");
			return false;
		}
		return true;

	}
}
