package backend.controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Helper {
	public static Component GenerateImage(String imageUrl, int width, int height) {

		try {
			BufferedImage bufferedImage = ImageIO.read(new File(imageUrl));
			JLabel image = new JLabel("Image");
			image.setBounds(0, 0, width, height);
			image.setVerticalAlignment(SwingConstants.TOP);
			image.setBackground(Color.GRAY);
			image.setHorizontalAlignment(SwingConstants.CENTER);
			ImageIcon imageIcon = new ImageIcon(fitimage(bufferedImage, width + 20, height));
			image.setIcon(imageIcon);
			return image;
		} catch (IOException e) {
			e.printStackTrace();
			return null;

		}
	}

	private static Image fitimage(Image img, int w, int h) {
		// https://stackoverflow.com/a/34169084/3587326
		BufferedImage resizedimage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = resizedimage.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(img, 0, 0, w, h, null);
		g2.dispose();
		return resizedimage;
	}

	public static String selectImage() {
		// https://stackoverflow.com/questions/40255039/how-to-choose-file-in-java
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {

			System.out.println("You chose to open this file: " + chooser.getSelectedFile().getAbsolutePath());
			return chooser.getSelectedFile().getAbsolutePath();

		}
		return null;
	}

	public static void showError(String message) {

		JOptionPane.showMessageDialog(new JFrame(), message, "Dialog", JOptionPane.ERROR_MESSAGE);
	}

	public static void showSuccess(String message) {

		JOptionPane.showMessageDialog(new JFrame(), message, "Dialog", JOptionPane.INFORMATION_MESSAGE);
	}
}
