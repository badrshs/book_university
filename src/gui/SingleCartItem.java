package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Component;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import backend.controller.Helper;
import backend.entity.Book;
import javax.swing.SwingConstants;

public class SingleCartItem extends JPanel {

	/**
	 * Create the panel.
	 */
	public SingleCartItem(Book book, int quantityNumber) {
		setLayout(null);
		
		
		 try {
			 JPanel imgPanel = new JPanel();
			imgPanel.setBounds(12, 13, 124, 110);
			Component	img = Helper.GenerateImage("C:\\Users\\bader\\eclipse-workspace\\library-master\\images\\001-1-254x377@2x.jpg", 135, 100);

			imgPanel.add(img);
			add(imgPanel);

		 } catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
		JLabel name = new JLabel(book.name);
		name.setBounds(169, 13, 360, 79);
		add(name);
		
		JLabel price = new JLabel(book.price*quantityNumber+" $");
		price.setHorizontalAlignment(SwingConstants.CENTER);
		price.setFont(new Font("Tahoma", Font.PLAIN, 18));
		price.setBounds(541, 24, 79, 52);
		add(price);
		
		JLabel quantity = new JLabel(quantityNumber+"");
		quantity.setHorizontalAlignment(SwingConstants.CENTER);
		quantity.setFont(new Font("Tahoma", Font.PLAIN, 18));
		quantity.setBounds(621, 24, 87, 52);
		add(quantity);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(720, 35, 35, 35);
		spinner.setValue(quantityNumber);
		spinner.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				quantity.setText(spinner.getValue()+"");	
 				price.setText(book.price*(int)spinner.getValue()+"");	
			}
		});
		add(spinner);
	}
}
