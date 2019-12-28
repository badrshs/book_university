package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import backend.controller.AdminController;
import backend.controller.Helper;
import backend.controller.Validation;
import backend.entity.Book;
import backend.entity.Category;
import backend.model._Category;

import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.SwingConstants;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.DropMode;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ManageBook extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField name;
	private JTextField writer;
	private JSpinner price;
	private Category[] categories;
	private JComboBox category;
	private JTextArea description;
	private JLabel imageContainer;
	private String selectedImage;

	public ManageBook(Book book) {
		categories = getAllCategory();
		generate(book);
	}

	private Category[] getAllCategory() {
		_Category _category = new _Category();
		return (Category[]) _category.get();
	}

	private int getSelectedCategory(int id) {
		for (int i = 0; i < categories.length; i++) {
			if (categories[i].id == id)
				return i;
		}
		return -1;
	}

	public void generate(Book book) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setBounds(100, 100, 450, 689);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblName = new JLabel("name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblName.setBounds(22, 19, 70, 29);
		contentPanel.add(lblName);

		JSeparator separator = new JSeparator();
		separator.setBounds(22, 68, 418, 11);
		contentPanel.add(separator);

		name = new JTextField(book.name);
		name.setBounds(128, 15, 260, 40);
		contentPanel.add(name);
		name.setColumns(10);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(22, 132, 418, 11);
		contentPanel.add(separator_1);

		writer = new JTextField(book.writer);
		writer.setColumns(10);
		writer.setBounds(128, 79, 260, 40);
		contentPanel.add(writer);

		JLabel lblWriter = new JLabel("writer");
		lblWriter.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblWriter.setBounds(22, 77, 70, 40);
		contentPanel.add(lblWriter);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(22, 280, 418, 11);
		contentPanel.add(separator_2);

		JLabel lblDescriptionlbl = new JLabel("description");
		lblDescriptionlbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDescriptionlbl.setBounds(22, 144, 95, 35);
		contentPanel.add(lblDescriptionlbl);

		price = new JSpinner();
		price.setBounds(301, 370, 104, 40);
		price.setValue(book.price);

		contentPanel.add(price);

		JLabel lblPrice = new JLabel("price");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPrice.setBounds(209, 371, 51, 35);
		contentPanel.add(lblPrice);

		JButton btnUploadNewImage = new JButton("upload new image");
		btnUploadNewImage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				selectedImage = Helper.selectImage();

				imageProcessing(selectedImage);

			}
		});
		btnUploadNewImage.setBounds(12, 581, 173, 25);
		contentPanel.add(btnUploadNewImage);

		description = new JTextArea(book.description);
		description.setLineWrap(true);
		description.setBounds(128, 150, 260, 117);
		contentPanel.add(description);

		category = new JComboBox();
		category.setModel(new DefaultComboBoxModel(categories));
		category.setSelectedIndex(getSelectedCategory(book.category_id));
		category.setBounds(128, 293, 260, 40);
		contentPanel.add(category);

		JLabel lblCategory = new JLabel("category");
		lblCategory.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCategory.setBounds(22, 294, 70, 35);
		contentPanel.add(lblCategory);

		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(22, 346, 418, 11);
		contentPanel.add(separator_4);

		JLabel lblInstock = new JLabel("In Stock");
		lblInstock.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblInstock.setBounds(209, 513, 70, 35);
		contentPanel.add(lblInstock);
		imageProcessing(book.photo);
		JSpinner InStock = new JSpinner();
		InStock.setBounds(301, 512, 104, 40);
		InStock.setValue(book.in_stock);
		contentPanel.add(InStock);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Map<String, String> data = new HashMap<>();
						data.put("name", name.getText());
						data.put("writer", writer.getText());
						data.put("description", description.getText());
						data.put("price", price.getValue().toString());
						data.put("in_stock", InStock.getValue().toString());
						data.put("category_id", Integer.toString(categories[category.getSelectedIndex()].id));
						data.put("photo", "xx");

						if (valdation(data)) {
							AdminController.UpdateBook(data, book);
							Helper.showSuccess("Book updated successfully");
							dispose();
						}
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}

	private void imageProcessing(String PATH) {
		imageContainer = new JLabel();
		imageContainer.setBounds(12, 358, 175, 210);
		Component img = Helper.GenerateImage("C:\\Users\\bader\\Desktop\\20150414_211356.jpg", 200, 210);
		img.setBounds(0, 0, 175, 210);
		imageContainer.add(img);
		contentPanel.add(imageContainer);
		revalidate();
		repaint();
	}

	public boolean valdation(Map<String, String> data) {

		if (Validation.IsA_Z(data.get("name"))) {
			Helper.showError("can't validate name text");

			return false;
		} else if (!Validation.IsA_Z(data.get("writer"))) {
			Helper.showError("can't validate writer text");
			return false;
		} else if (!Validation.IsA_Z(data.get("description"))) {
			Helper.showError("can't validate description text");

			return false;
		} else if (!Validation.IsValidNumber(data.get("in_stock"))) {
			Helper.showError("can't validate in_stock number");

			return false;
		}

		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
