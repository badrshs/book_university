package backend;

import java.awt.EventQueue;

import backend.controller.Router;

public class starter {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Router.ShowCategoriesList();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
