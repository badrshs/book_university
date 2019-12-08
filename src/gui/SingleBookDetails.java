package gui;

import java.awt.Component;

import javax.swing.JPanel;

import backend.entity.Book;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Label;
import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.Button;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class SingleBookDetails extends JPanel {

	/**
	 * Create the panel.
	 */
	public SingleBookDetails(Book book) {
		setLayout(null);
		setBounds(12, 100, 1600, 800);
		add(generateBooksList(book));
		
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
		
		JTextPane Name = new JTextPane();
		Name.setEditable(false);
		Name.setFont(new Font("Tahoma", Font.PLAIN, 25));
		Name.setText("58.5 - The Nobel Prize for Literature Tries Harder and Fails");
		Name.setBounds(437, 45, 959, 54);
		add(Name);
		
		JTextPane textPane = new JTextPane();
		textPane.setText("58.5 - The Nobel Prize for Literature Tries Harder and Fails");
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textPane.setEditable(false);
		textPane.setBounds(437, 126, 959, 54);
		add(textPane);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setText("58.5 - The Nobel Prize for Literature Tries Harder and Fails");
		textPane_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textPane_1.setEditable(false);
		textPane_1.setBounds(437, 193, 959, 54);
		add(textPane_1);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setText("58.5 - The Nobel Prize for Literature Tries Harder and Fails");
		textPane_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textPane_2.setEditable(false);
		textPane_2.setBounds(437, 266, 959, 54);
		add(textPane_2);
		
		JTextPane textPane_3 = new JTextPane();
		textPane_3.setText("58.5 - The Nobel Prize for Literature Tries Harder and Fails");
		textPane_3.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textPane_3.setEditable(false);
		textPane_3.setBounds(437, 333, 959, 133);
		add(textPane_3);
		
		Button button = new Button("Add To Cart");
		button.setFont(new Font("Dialog", Font.BOLD, 21));
		button.setBounds(1251, 493, 143, 80);
		add(button);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinner.setFont(new Font("Tahoma", Font.BOLD, 28));
		spinner.setBounds(1130, 493, 103, 80);
		add(spinner);
	}

	private Component generateBooksList(Book book) {
		JLabel lblBookPhoto = new JLabel("Book Photo");
		lblBookPhoto.setForeground(Color.YELLOW);
		lblBookPhoto.setBackground(Color.PINK);
		lblBookPhoto.setHorizontalAlignment(SwingConstants.CENTER);
		lblBookPhoto.setVerticalAlignment(SwingConstants.TOP);
		lblBookPhoto.setBounds(12, 28, 321, 357);
		return lblBookPhoto;
	}
}
