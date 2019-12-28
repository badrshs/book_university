package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.EventQueue;

import backend.controller.MainPage;

public class  MainPageGui extends JFrame {

	public static JPanel jPanel;
	public static Menu  MainMenu;

	public MainPageGui() {
		setLayout(null);
		setVisible(true);
		setTitle("Admin Page");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1600, 800);
		jPanel = new JPanel();
		jPanel.setLayout(null);
		jPanel.setBounds(0, 0, 1600, 800);
		jPanel.setVisible(true);
		 generateMenue(new Menu());
		add(jPanel);
	}

	JMenu menu, submenu;
	JMenuItem i1, i2, i3, i4, i5;

	public void generateMenue( Menu menu) {
 		setJMenuBar(menu);
 		revalidate();
		repaint();
	}
}
