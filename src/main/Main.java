package main;

import model.Model;
import view.MainGUI;

public class Main {

	/**
	 * main entry
	 */
	public static void main(String[] args) {
		Model model = Model.getInstance();
		MainGUI gui = new MainGUI(model);
		gui.setVisible(true);
	}
}
