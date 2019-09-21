package com.lms.view;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class RunApp {

	public static void main(String[] arg) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
			MainFrame.createAndShowGUI();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
