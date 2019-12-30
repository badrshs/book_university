package gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import backend.controller.Helper;

//created by badr
public class UploadButton extends JButton {
	private String uploadedImage;

	public String toString() {
		return uploadedImage;
	}

	public UploadButton(String text) {
		super(text);
		JButton x = new JButton();
	}

	public void processUpload() {
		File file = selectImage();
		if (file == null)
			return;
		File parentPath = new File("").getAbsoluteFile();
		String fileName = file.getName();
		String fileExtension = fileName.substring(fileName.lastIndexOf("."));

		fileExtension = (fileExtension != "png" || fileExtension != "jpg") ? "png" : fileExtension;

		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date = new Date();
			fileName = Helper.encryption(formatter.format(date)) + "." + fileExtension;
			BufferedImage image = ImageIO.read(file.toURL());
			System.out.println(parentPath + "/" + "images/" + fileName);
			ImageIO.write(image, fileExtension, new File(parentPath + "\\" + "images\\" + fileName));
			uploadedImage = fileName;
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static File selectImage() {
		// https://stackoverflow.com/questions/40255039/how-to-choose-file-in-java
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "png");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			return new File(chooser.getSelectedFile().getAbsolutePath());
		}
		return null;
	}
}
