package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;

import backend.controller.Helper;
import backend.controller.Router;
import backend.entity.CartItems;
import backend.entity.MainCart;
import backend.entity.Order;

import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Payment extends JPanel {
	private JTextField cardNumber;
	private JTextField cvv;
	public static JLabel price;

	public Payment() {
		setLayout(null);
		setBounds(0, 0, 410, 317);

		JLabel lblCardNumber = new JLabel("Card Number");
		lblCardNumber.setBounds(12, 120, 88, 31);
		add(lblCardNumber);

		cardNumber = new JTextField();
		cardNumber.setBounds(103, 118, 292, 34);
		add(cardNumber);
		cardNumber.setColumns(10);

		cvv = new JTextField();
		cvv.setBounds(103, 162, 102, 34);
		add(cvv);

		JLabel lblCvs = new JLabel("cvv");
		lblCvs.setBounds(62, 164, 33, 31);
		add(lblCvs);

		JButton processPayment = new JButton("Process Payment");
		processPayment.setFont(new Font("Tahoma", Font.PLAIN, 25));
		processPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (cvv.getText().length() != 3) {
					Helper.showError("cvv number must be 3 number");
					return;

				} else if (cardNumber.getText().length() != 16) {
					Helper.showError(" card number is not valid ");
					return;
				}
				Order order = new Order();
				if (!order.verify(cardNumber.getText(), cvv.getText())) {
					Helper.showError("your credit card is invalid, please try again with another card.");
					return;
				}
				if (!order.StartPurchase()) {
					Helper.showError("data mismatch, trying to Re-Sync your Cart, try again please");
					MainCart.cartItems = new ArrayList<CartItems>();
					MainCart.ReSyncCartItem();
					Router.ShowCartDetails();
				} else {
					MainCart.cartItems = new ArrayList<CartItems>();
					MainCart.deleteUserDatabaseCart();
					Helper.showSuccess("thank you for your purchase, check your email for the details");
					Router.ShowCartDetails();
				}
			}
		});
		processPayment.setBounds(103, 225, 236, 54);
		add(processPayment);

		JLabel lblTotal = new JLabel("Total Price");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblTotal.setBounds(23, 28, 170, 64);
		add(lblTotal);

		price = new JLabel("");
		price.setHorizontalAlignment(SwingConstants.CENTER);
		price.setFont(new Font("Tahoma", Font.PLAIN, 35));
		price.setBounds(193, 28, 202, 64);
		add(price);
		setVisible(true);

	}
}
