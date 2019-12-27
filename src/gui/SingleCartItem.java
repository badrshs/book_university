package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Component;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import backend.controller.Helper;
import backend.entity.Book;
import javax.swing.SwingConstants;

public class SingleCartItem extends JPanel {

	/**
	 * Create the panel.
	 */
	public SingleCartItem(Book book, int quantityNumber) {
		setLayout(null);
		setBounds(0, 0, 780, 120);
		JPanel imgPanel = new JPanel();
		imgPanel.setBounds(10, 10, 124, 110);
		Component img = Helper.GenerateImage(
				"C:\\Users\\bader\\eclipse-workspace\\library-master\\images\\001-1-254x377@2x.jpg", 135, 100);

		imgPanel.add(img);
		add(imgPanel);

		JLabel name = new JLabel(book.name);
		name.setBounds(169, 13, 360, 79);
		add(name);

		JLabel price = new JLabel(book.price * quantityNumber + " $");
		price.setHorizontalAlignment(SwingConstants.CENTER);
		price.setFont(new Font("Tahoma", Font.PLAIN, 18));
		price.setBounds(541, 24, 79, 52);
		add(price);

		JLabel quantity = new JLabel(quantityNumber + "");
		quantity.setFont(new Font("Tahoma", Font.PLAIN, 18));
		quantity.setBounds(631, 24, 35, 52);
		add(quantity);

		JSpinner spinner = new JSpinner(new SpinnerNumberModel(0, 0, 1, 1));

		spinner.setBounds(720, 35, 35, 35);
		spinner.setValue(quantityNumber);
		spinner.setVisible(true);
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				quantity.setText(spinner.getValue() + "");
				price.setText(book.price * (int) spinner.getValue() + "");
			}
		});
		add(spinner);
	}
}
