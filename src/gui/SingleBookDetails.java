package gui;

import java.awt.Component;

import javax.swing.JPanel;

import backend.controller.Helper;
import backend.controller.Router;
import backend.entity.Auth;
import backend.entity.Book;
import backend.entity.MainCart;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Label;
import java.io.IOException;
import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.Button;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SingleBookDetails extends JPanel {

	public SingleBookDetails(Book book) {
		setLayout(null);
		setBounds(12, 0, 1600, 800);
		GenerateSingleBook(book);
		if (book.in_stock != 0) {
			JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 0, book.in_stock, 1));
			spinner.setFont(new Font("Tahoma", Font.BOLD, 28));
			spinner.setBounds(1130, 493, 103, 80);
			add(spinner);
			Button button = new Button("Add To Cart");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if ((int) spinner.getValue() > 0 && (int) spinner.getValue() <= book.in_stock) {
						MainCart.addNewItemByProduct(book, (int) spinner.getValue());
						Router.ShowCartDetails();
					}

				}
			});
			button.setFont(new Font("Dialog", Font.BOLD, 21));
			button.setBounds(1251, 493, 143, 80);
			add(button);
		}else {
			JLabel lblOutOfStock = new JLabel("Out Of Stock");
			lblOutOfStock.setForeground(Color.RED);
			lblOutOfStock.setFont(new Font("Tahoma", Font.PLAIN, 24));
			lblOutOfStock.setBounds(987, 508, 181, 54);
			add(lblOutOfStock);
		}

	}

	private void GenerateSingleBook(Book book) {
		Component img = Helper.GenerateImage(book.photo, 250, 400);
		img.setBounds(50, 50, 250, 400);
		add(img);

		Label labelName = new Label("Name");
		labelName.setFont(new Font("Dialog", Font.BOLD, 18));
		labelName.setBounds(340, 45, 79, 46);
		add(labelName);

		Label WriterLabel = new Label("writer");
		WriterLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		WriterLabel.setBounds(339, 126, 79, 46);
		add(WriterLabel);

		Label InStockLabel = new Label("In Stock  ");
		InStockLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		InStockLabel.setBounds(340, 193, 79, 46);
		add(InStockLabel);

		Label priceLabel = new Label("price");
		priceLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		priceLabel.setBounds(340, 263, 79, 46);
		add(priceLabel);

		Label descriptionLabel = new Label("description");
		descriptionLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		descriptionLabel.setBounds(328, 333, 103, 46);
		add(descriptionLabel);

		JTextPane nameDetails = new JTextPane();
		nameDetails.setEditable(false);
		nameDetails.setFont(new Font("Tahoma", Font.PLAIN, 25));
		nameDetails.setText(book.name);
		nameDetails.setBounds(437, 45, 959, 54);
		add(nameDetails);

		JTextPane writerDetials = new JTextPane();
		writerDetials.setText(book.writer);
		writerDetials.setFont(new Font("Tahoma", Font.PLAIN, 25));
		writerDetials.setEditable(false);
		writerDetials.setBounds(437, 126, 959, 54);
		add(writerDetials);

		JTextPane inStockDetails = new JTextPane();
		inStockDetails.setText(Integer.toString(book.in_stock));
		inStockDetails.setFont(new Font("Tahoma", Font.PLAIN, 25));
		inStockDetails.setEditable(false);
		inStockDetails.setBounds(437, 193, 959, 54);
		add(inStockDetails);

		JTextPane priceDetails = new JTextPane();
		priceDetails.setText(book.price + "$");
		priceDetails.setFont(new Font("Tahoma", Font.PLAIN, 25));
		priceDetails.setEditable(false);
		priceDetails.setBounds(437, 266, 959, 54);
		add(priceDetails);

		JTextPane descriptionDetials = new JTextPane();
		descriptionDetials.setText(book.description);
		descriptionDetials.setFont(new Font("Tahoma", Font.PLAIN, 25));
		descriptionDetials.setEditable(false);
		descriptionDetials.setBounds(437, 333, 959, 133);
		add(descriptionDetials);
		
	
	}
}