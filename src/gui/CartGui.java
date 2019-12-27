package gui;

import java.awt.Color;

import javax.swing.JPanel;

import backend.entity.CartItems;
import backend.entity.MainCart;
import java.awt.FlowLayout;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.SpinnerNumberModel;

public class CartGui extends JPanel {

	public CartGui() {
		setBounds(0, 0, 1600, 800);
		setLayout(null);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(216, 288, 30, 22);
		add(spinner);

		GenerateCartItems();

	}

	private void GenerateCartItems() {
		int x = 0;
		int y = 0;
 		int between_h = 15;
		int width = 1000;
		int height = 110;

		for (CartItems item : MainCart.cartItems) {
			SingleCartItem cartItem = new SingleCartItem(item.get_Book(), item.quantity);
			cartItem.setBackground(Color.GRAY);
			cartItem.setBounds(x, y, width, height);
			add(cartItem);
			y += height + between_h;

		}
	}
}
