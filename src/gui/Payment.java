package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Payment extends JPanel {
	private JTextField cardNumber;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public Payment() {
		setLayout(null);
		
		JLabel lblCardNumber = new JLabel("Card Number");
		lblCardNumber.setBounds(12, 120, 88, 31);
		add(lblCardNumber);
		
		cardNumber = new JTextField();
		cardNumber.setBounds(103, 118, 292, 34);
		add(cardNumber);
		cardNumber.setColumns(10);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(103, 162, 102, 34);
		add(textField);
		
		JLabel lblCvs = new JLabel("CVS");
		lblCvs.setBounds(62, 164, 33, 31);
		add(lblCvs);
		
		JButton btnNewButton = new JButton("Process Payment");
		btnNewButton.setBounds(103, 209, 155, 25);
		add(btnNewButton);
		
		JLabel lblTotal = new JLabel("Total Price");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblTotal.setBounds(23, 28, 170, 64);
		add(lblTotal);
		
		JLabel price = new JLabel("");
		price.setHorizontalAlignment(SwingConstants.CENTER);
		price.setFont(new Font("Tahoma", Font.PLAIN, 35));
		price.setBounds(193, 28, 202, 64);
		add(price);

	}
}
