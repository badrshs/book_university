package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import backend.controller.AdminController;
import backend.controller.Helper;
import backend.controller.Router;
import backend.entity.Auth;
import backend.entity.Category;

public class CategoryButton extends JPanel {

	JButton categoryBotton;
	public CategoryButton(Category category) {
		categoryBotton = new JButton(category.name);
		categoryBotton.setBounds(0, 0, 240, 80);
		setLayout(null);
		categoryBotton.setFont(new Font("Bahnschrift", Font.BOLD, 20));
		categoryBotton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Router.ShowBooksPage(category);
			}
		});
		add(categoryBotton);

		AdminUtilities(category);
	}

	private void AdminUtilities(Category category) {
		if (Auth.isAdmin()) {
			JButton btnNewButton = new JButton("edit");
			btnNewButton.setLayout(null);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					AdminController.editCategory(category);
				}
			});
			btnNewButton.setBounds(0, 80, 120, 25);
			add(btnNewButton);
			JButton btnDelete = new JButton("delete");
			btnDelete.setLayout(null);
			btnDelete.setBounds(120, 80, 120, 25);
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure that you want to delete ?",
							"Warning", JOptionPane.YES_NO_OPTION);
					if (dialogResult == 0) {
						boolean status = AdminController.DeleteCategory(category.id);
						if (status) {
							Helper.showSuccess("deleted successfully");
						} else {
							Helper.showError("Can't delete the category, if this category contain any product, please remove them  and try again");
						}
					}
				}
			});
			add(btnDelete);
		}
	}
}
