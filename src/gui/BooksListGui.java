package gui;

import java.awt.Button;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import backend.controller.MainPage;
import backend.entity.Auth;
import backend.entity.Book;
import backend.entity.Category;

public class BooksListGui extends JPanel {

	/**
	 * Create the panel.
	 */
	public BooksListGui(Category category) {
		setLayout(null);
		setBounds(0, 0, 1600, 800);
		add(generateBooksList(category.GetBooks()));
	}

	public JPanel generateBooksList(Book[] books) {
		JPanel categoriesContainer = new JPanel();
		categoriesContainer.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		categoriesContainer.setBounds(12, 13, 1543, 800);
		categoriesContainer.setLayout(null);
		int x = 25;
		int y = 12;
		int between_w = 25;
		int between_h = 15;
		int width = 275;
		int height = Auth.isAdmin()?277:250;
		 JPanel book_box ;
		for (int i = 0; i < books.length; i++) {
			book_box = new SingleBookItem(books[i]);
			book_box.setBounds(x, y, width, height);
			final int index = 0;
			categoriesContainer.add(book_box);
			x += width + between_w;
			if ((i + 1) % 5 == 0) {
				y += height + between_h;
				x = 25;
			}

		}
		return categoriesContainer;
	}
}
