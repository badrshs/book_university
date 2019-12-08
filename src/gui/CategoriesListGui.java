package gui;

import java.awt.Button;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import backend.controller.MainPage;
import backend.entity.Category;

public class CategoriesListGui extends JPanel {

	/**
	 * Create the panel.
	 */
	public CategoriesListGui(Category[] categories) {
		setLayout(null);
		setBounds(12, 100, 1600, 800);
		add(GenerateCategories(categories));
	}

	
	public JPanel GenerateCategories(Category[] categories) {
		return  generateCategories(categories); 

/*
		JScrollPane scrollCategories = new JScrollPane();
		scrollCategories.setViewportBorder(null);
		scrollCategories.setBounds(39, 125, 1469, 588);
		scrollCategories.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollCategories.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		JPanel categoriesFields = generateCategories(categories); 
		scrollCategories.setViewportView(categoriesFields);
		return scrollCategories;*/
	}

	public JPanel generateCategories(Category[] categories) {
		JPanel categoriesContainer = new JPanel();
		categoriesContainer.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		categoriesContainer.setBounds(12, 13, 1543, 800);
		categoriesContainer.setLayout(null);
		int x = 15;
		int y = 15;
		int between_w = 70;
		int between_h = 15;
		int width = 240;
		int height = 80;

		for (int i = 0; i < categories.length; i++) {
			Button categoryBotton = new Button(categories[i].name+" ("+categories[i].GetBooks().length+")");
			categoryBotton.setBounds(x, y, width, height);
			final int index = i;
			categoryBotton.setFont(new Font("Bahnschrift", Font.BOLD, 20));
			categoriesContainer.add(categoryBotton);
			x += width + between_w;
			if ((i + 1) % 5 == 0) {
				y += height + between_h;
				x = 15;
			}
			categoryBotton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					MainPage.ShowBooksPage(categories[index]);
				}
			});
		}
		return categoriesContainer;
	}
	
}
