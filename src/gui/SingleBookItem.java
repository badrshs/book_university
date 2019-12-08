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

import backend.controller.MainPage;
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

public class SingleBookItem extends JPanel {
	private String photo;
	private String title;
	private double price;
	private int quantity;
	private int in_stock;
	private String writer;
	private String category;

//https://stackoverflow.com/a/34169084/3587326
	public SingleBookItem(Book book) {
		super();
		setBackground(Color.GRAY);
		setLayout(null);

		try {
			BufferedImage image = ImageIO.read(
					new File("C:\\Users\\bader\\eclipse-workspace\\library-master\\images\\001-1-254x377@2x.jpg"));

			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			panel.setBounds(0, 138, 275, 112);
			add(panel);
			panel.setLayout(null);
			
			Panel panel_1 = new Panel();
			panel_1.setBounds(0, 0, 59, 112);
			panel.add(panel_1);
			panel_1.setLayout(null);
			
			JLabel bookNameLabel = new JLabel("Name");
			bookNameLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
			bookNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
			bookNameLabel.setBounds(5, 0, 54, 41);
			panel_1.add(bookNameLabel);
			
			JLabel writerLabel = new JLabel("Writer");
			writerLabel.setHorizontalAlignment(SwingConstants.LEFT);
			writerLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
			writerLabel.setBounds(5, 35, 54, 41);
			panel_1.add(writerLabel);
			
			JLabel priceLabel = new JLabel("Price");
			priceLabel.setHorizontalAlignment(SwingConstants.LEFT);
			priceLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
			priceLabel.setBounds(5, 75, 54, 41);
			panel_1.add(priceLabel);
			
			JTextPane bookName = new JTextPane();
			bookName.setText("Read Alikes for Our 2019 Podcast Reads");
			bookName.setEditable(false);
			bookName.setBounds(59, 0, 204, 43);
			panel.add(bookName);
			
			Button button = new Button("Details");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					MainPage.ShowSingleBook(book);
				}
			});
			button.setFont(new Font("Dialog", Font.BOLD, 15));
			button.setBounds(186, 73, 79, 33);
			panel.add(button);
			
			JTextPane textPane = new JTextPane();
			textPane.setText("Read Alikes for Our 2019 Podcast Reads");
			textPane.setEditable(false);
			textPane.setBounds(59, 42, 204, 33);
			panel.add(textPane);
			
			JLabel label = new JLabel("500$");
			label.setHorizontalAlignment(SwingConstants.LEFT);
			label.setFont(new Font("Tahoma", Font.BOLD, 20));
			label.setBounds(65, 80, 79, 28);
			panel.add(label);

			JLabel lblImage = new JLabel("Image");
			lblImage.setBounds(0, 0, 275, 138);
			lblImage.setVerticalAlignment(SwingConstants.TOP);
			lblImage.setBackground(Color.GRAY);
			lblImage.setHorizontalAlignment(SwingConstants.CENTER);
			ImageIcon imageIcon = new ImageIcon(fitimage(image, lblImage.getWidth()+20, lblImage.getHeight()));

			lblImage.setIcon(imageIcon);
			add(lblImage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		SingleBookItem();
	}

	public void SingleBookItem() {

	}

	private Image fitimage(Image img, int w, int h) {
		BufferedImage resizedimage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = resizedimage.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(img, 0, 0, w, h, null);
		g2.dispose();
		return resizedimage;
	}
}
