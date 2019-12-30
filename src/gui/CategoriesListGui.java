package gui;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import backend.entity.Auth;
import backend.entity.Category;

public class CategoriesListGui extends JPanel {

	/**
	 * Create the panel.
	 */
	public CategoriesListGui(Category[] categories) {
		setLayout(null);
		setBounds(0, 0, 1600, 800);
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
		int height = Auth.isAdmin()?120:80;

		for (int i = 0; i < categories.length; i++) {
			int bookNumber = categories[i].GetBooks().length;
			/*if(bookNumber==0)
				continue;*/
			CategoryButton categoryBotton = new CategoryButton(categories[i]);
			categoryBotton.setBounds(x, y, width, height);
			categoriesContainer.add(categoryBotton);
			x += width + between_w;
			if ((i + 1) % 5 == 0) {
				y += height + between_h;
				x = 15;
			}
		}
		return categoriesContainer;
	}
}
