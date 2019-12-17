package gui;

import java.awt.Component;

import javax.swing.JPanel;

import backend.controller.Helper;
import backend.controller.MainPage;
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

	/**
	 * Create the panel.
	 */
	public SingleBookDetails(Book book) {
		setLayout(null);
		setBounds(12, 100, 1600, 800);

		GenerateSingleBook(book);

		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinner.setFont(new Font("Tahoma", Font.BOLD, 28));
		spinner.setBounds(1130, 493, 103, 80);
		add(spinner);

		Button button = new Button("Add To Cart");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if ((int) spinner.getValue() > 0) {
					MainCart.addNewItemByProduct(book, (int) spinner.getValue());
					MainPage.ShowCartDetails();
				}

			}
		});
		button.setFont(new Font("Dialog", Font.BOLD, 21));
		button.setBounds(1251, 493, 143, 80);
		add(button);

	}

	private void GenerateSingleBook(Book book) {
		JLabel lblBookPhoto = new JLabel("Book Photo");
		lblBookPhoto.setForeground(Color.YELLOW);
		lblBookPhoto.setBackground(Color.PINK);
		lblBookPhoto.setHorizontalAlignment(SwingConstants.CENTER);
		lblBookPhoto.setVerticalAlignment(SwingConstants.TOP);
		lblBookPhoto.setBounds(12, 28, 321, 357);
		add(lblBookPhoto);

		try {
			Component img = Helper.GenerateImage(
					"C:\\Users\\bader\\eclipse-workspace\\library-master\\images\\001-1-254x377@2x.jpg", 250, 400);
			img.setBounds(50, 50, 250, 400);
			add(img);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Label labelName = new Label("Name");
		labelName.setFont(new Font("Dialog", Font.BOLD, 18));
		labelName.setBounds(340, 45, 79, 46);
		add(labelName);

		Label WriterLabel = new Label("writer");
		WriterLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		WriterLabel.setBounds(339, 126, 79, 46);
		add(WriterLabel);

		Label quantityLabel = new Label("quantity");
		quantityLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		quantityLabel.setBounds(340, 193, 79, 46);
		add(quantityLabel);

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

		JTextPane quantityDetails = new JTextPane();
		quantityDetails.setText("--" + book.quantity + "--");
		quantityDetails.setFont(new Font("Tahoma", Font.PLAIN, 25));
		quantityDetails.setEditable(false);
		quantityDetails.setBounds(437, 193, 959, 54);
		add(quantityDetails);

		JTextPane priceDetails = new JTextPane();
		priceDetails.setText(book.price + "TL");
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
