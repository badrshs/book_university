package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import backend.entity.Auth;
import backend.entity.CartItems;
import backend.entity.MainCart;

public class CartGui extends JPanel {

	public CartGui() {
		setBounds(20, 20, 1600, 800);
		setLayout(null);
		GenerateCartItems();
		GenerateCartPaymentPanel();
		revalidate();
		repaint();
		if (Payment.price != null)
			Payment.price.setText(MainCart.calculateTotalPrice() + "$");
	}

	private void GenerateCartPaymentPanel() {
		if (MainCart.cartItems.size() == 0) {
			JLabel lblNewLabel = new JLabel("You Basket Cart Is Empty");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
			lblNewLabel.setBounds(582, 200, 547, 144);
			add(lblNewLabel);
			return;
		}

		if (Auth.isAuth) {
			Payment payment = new Payment();
			payment.setBounds(1050, 196, 410, 317);
			add(payment);
		} else {
			Login login = new Login();
			login.setBounds(1031, 200, 473, 317);
			add(login);

			JTextPane txtpnToContinuingYour = new JTextPane();
			txtpnToContinuingYour.setBackground(SystemColor.control);
			txtpnToContinuingYour.setForeground(Color.RED);
			txtpnToContinuingYour.setFont(new Font("Tahoma", Font.BOLD, 20));
			txtpnToContinuingYour.setText(
					"to continuing your purchase session , you should be a user, \r\nlogin or register please ");
			txtpnToContinuingYour.setBounds(1059, 87, 429, 138);
			add(txtpnToContinuingYour);
		}

	}

	private void GenerateCartItems() {
		int x = 0;
		int y = 0;
		int between_h = 15;
		int width = 1000;
		int height = 148;
		for (CartItems item : MainCart.cartItems) {
			SingleCartItem cartItem = new SingleCartItem(item.get_Book(), item);
			cartItem.setBackground(SystemColor.controlHighlight);
			cartItem.setBounds(x, y, width, height);
			add(cartItem);
			y += height + between_h;
		}

		if (MainCart.cartItems.size() == 0) {

		}
	}
}
