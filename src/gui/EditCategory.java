package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import backend.controller.AdminController;
import backend.controller.Helper;
import backend.controller.Validation;
import backend.entity.Category;

public class EditCategory extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

	public EditCategory(Category category) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setVisible(true);

		setBounds(100, 100, 450, 194);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			textField = new JTextField(category.name);
			textField.setFont(new Font("Tahoma", Font.PLAIN, 17));
			textField.setBounds(177, 40, 223, 40);
			contentPanel.add(textField);
			textField.setColumns(10);
		}

		JLabel lblCategoryName = new JLabel("Category Name");
		lblCategoryName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCategoryName.setBounds(12, 31, 153, 52);
		contentPanel.add(lblCategoryName);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 112, 432, 35);
			contentPanel.add(buttonPane);
			JButton okButton;
			JButton cancelButton;
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (Validation.IsA_Z(textField.getText())) {
							boolean status = AdminController.updateCategory(textField.getText(), category.id);
							if (!status)
								Helper.showError("failed updating target category");
							else {
								Helper.showSuccess("Category updated successfully");
								dispose();

							}
						} else {
							Helper.showError("category name is not valid, only A to Z are allowed ");
						}
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
		add(contentPanel);
	}

}
