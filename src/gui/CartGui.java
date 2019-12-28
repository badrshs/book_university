package gui;

import java.awt.Color;

import javax.swing.JPanel;

import backend.entity.CartItems;
import backend.entity.MainCart;
import java.awt.FlowLayout;
import java.awt.SystemColor;

import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.SpinnerNumberModel;

public class CartGui extends JPanel {

	public CartGui() {
		setBounds(20, 20, 1600, 800);
		setLayout(null);
		GenerateCartItems();
		revalidate();
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
	}
}
