package gui;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import backend.controller.AdminController;
import backend.controller.Helper;
import backend.controller.Router;
import backend.entity.*;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Component;
import java.awt.Rectangle;
import javax.swing.JTextPane;
import java.awt.Panel;
import javax.swing.JButton;

public class SingleBookItem extends JPanel {

	public SingleBookItem(Book book) {
		super();
		setBackground(Color.GRAY);
		setLayout(null);

		try {
			File parentPath = new File("").getAbsoluteFile();
			String imageUrl = parentPath + "\\images\\" + book.photo;
			BufferedImage image = ImageIO.read(new File(imageUrl));

			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			panel.setBounds(0, 138, 275, 115);
			add(panel);
			panel.setLayout(null);

			Panel panel_1 = new Panel();
			panel_1.setBounds(0, 0, 59, 112);
			panel.add(panel_1);
			panel_1.setLayout(null);

			JLabel bookNameLabel = new JLabel("Name");
			bookNameLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
			bookNameLabel.setBounds(5, 0, 54, 24);
			panel_1.add(bookNameLabel);

			JLabel writerLabel = new JLabel("Writer");
			writerLabel.setHorizontalAlignment(SwingConstants.LEFT);
			writerLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
			writerLabel.setBounds(5, 34, 54, 33);
			panel_1.add(writerLabel);

			JLabel priceLabel = new JLabel("Price");
			priceLabel.setHorizontalAlignment(SwingConstants.LEFT);
			priceLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
			priceLabel.setBounds(5, 75, 54, 24);
			panel_1.add(priceLabel);

			JTextPane name = new JTextPane();
			name.setText(book.name);
			name.setEditable(false);
			name.setBounds(59, 0, 204, 33);
			panel.add(name);

			Button button = new Button("Details");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Router.ShowSingleBook(book);
				}
			});
			button.setFont(new Font("Dialog", Font.BOLD, 15));
			button.setBounds(186, 73, 79, 33);
			panel.add(button);

			JTextPane writer = new JTextPane();
			writer.setText(book.writer);
			writer.setEditable(false);
			writer.setBounds(59, 42, 204, 33);
			panel.add(writer);
			JLabel price = new JLabel(book.price + "$");
			price.setHorizontalAlignment(SwingConstants.LEFT);
			price.setFont(new Font("Tahoma", Font.BOLD, 20));
			price.setBounds(65, 73, 79, 28);
			panel.add(price);
			Component img = Helper.GenerateImage(book.photo, 275, 138);
			img.setBounds(0, 0, 275, 138);
			add(img);

			JButton btnNewButton = new JButton("edit");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					AdminController.EditBook(book);
				}
			});
			btnNewButton.setBounds(0, 252, 137, 25);
			add(btnNewButton);

			JButton btnDelete = new JButton("delete");
			btnDelete.setBounds(138, 252, 137, 25);
			add(btnDelete);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
