package gui;

import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import backend.controller.Helper;
import backend.controller.Router;
import backend.entity.Book;
import backend.entity.CartItems;
import backend.entity.MainCart;

public class SingleCartItem extends JPanel {

	public SingleCartItem(Book book, CartItems item) {
		if (book.in_stock <= 0)
			return;
		setBackground(SystemColor.controlHighlight);
		setLayout(null);
		setBounds(0, 0, 995, 151);
		JPanel imgPanel = new JPanel();
		imgPanel.setBounds(0, -5, 124, 156);
		Component img = Helper.GenerateImage(book.photo, 155, 151);
		imgPanel.add(img);
		add(imgPanel);

		JLabel name = new JLabel(book.name);
		name.setBounds(169, 13, 360, 79);
		add(name);

		JLabel price = new JLabel(book.price * item.quantity + " $");
		price.setHorizontalAlignment(SwingConstants.CENTER);
		price.setFont(new Font("Tahoma", Font.PLAIN, 18));
		price.setBounds(571, 24, 79, 52);
		add(price);

		JLabel quantity = new JLabel(item.quantity + "");
		quantity.setFont(new Font("Tahoma", Font.PLAIN, 18));
		quantity.setBounds(693, 24, 35, 52);
		add(quantity);
		JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, book.in_stock, 1));
		spinner.setBounds(794, 35, 35, 35);
		spinner.setValue(item.quantity);
		spinner.setVisible(true);
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				quantity.setText(spinner.getValue() + "");
				price.setText(book.price * (int) spinner.getValue() + "$");
				MainCart.UpdateValueOfSpesificItem((int) spinner.getValue(), item.id);
				Payment.price.setText(MainCart.calculateTotalPrice() + "$");
				;
			}
		});

		add(spinner);

		JButton DELETE = new JButton("X");
		DELETE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainCart.deleteSpesificItem(book.id);
				Router.ShowCartDetails();
			}
		});
		DELETE.setBounds(924, 13, 59, 52);
		add(DELETE);
		revalidate();
		repaint();
	}
}
