package com.lms.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class MainFrame extends JFrame implements ActionListener {
	JButton btn_newUser, btn_oldUser, exit, btn_admin;
	Border thickBorder = new LineBorder(Color.decode("#FFC800"), 10);
	Font textsize = new Font("Arial", Font.BOLD, 14);
	private JLabel lbl_retriveimage = new JLabel(new ImageIcon("images/title.png"));
	// userInfoDAOImp userinfo = new userInfoDAOImp();

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	public static final Dimension PREFERREDSIZE = new Dimension(1000, 600);
	static JFrame frame = new MainFrame();


	public MainFrame() {
		super("Library Management Software");
		this.create_layout();
	}

	public static void createAndShowGUI() {
		try {
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setMinimumSize(PREFERREDSIZE);
			frame.setPreferredSize(PREFERREDSIZE);
			frame.setResizable(false);
			frame.setLayout(null);
			frame.setLocationRelativeTo(null);
			frame.pack();
			frame.setVisible(true);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error createGui" + ex.toString());
		}

	}

	public static void disposeall() {
		frame.setVisible(false);
	}

	public void create_layout() {
		try {

			btn_newUser = new JButton("New user");
			btn_oldUser = new JButton("Old user");
			btn_admin = new JButton("Administrator");
			exit = new JButton("Exit");
			btn_newUser.setBackground(Color.decode("#0D620F"));
			btn_newUser.setForeground(Color.white);

			btn_admin.setBackground(Color.decode("#0D620F"));
			btn_admin.setForeground(Color.white);

			btn_oldUser.setBackground(Color.decode("#0D620F"));
			exit.setBackground(Color.decode("#0D620F"));
			btn_oldUser.setForeground(Color.white);
			exit.setForeground(Color.white);
			btn_newUser.setFont(textsize);
			btn_admin.setFont(textsize);
			btn_oldUser.setFont(textsize);
			exit.setFont(textsize);
			btn_newUser.setBorder(thickBorder);
			btn_oldUser.setBorder(thickBorder);
			btn_admin.setBorder(thickBorder);
			btn_newUser.setBounds(250, 230, 130, 120);
			btn_oldUser.setBounds(420, 230, 130, 120);
			btn_admin.setBounds(590, 230, 130, 120);
			//
			exit.setBounds(250, 410, 500, 50);
			lbl_retriveimage.setBounds(170, 0, 550, 200);

			exit.addActionListener(this);
			btn_newUser.addActionListener(this);
			btn_admin.addActionListener(this);
			btn_oldUser.addActionListener(this);
			this.add(lbl_retriveimage);
			this.add(exit);
			this.add(btn_newUser);
			this.add(btn_admin);
			this.add(btn_oldUser);

			exit.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					@SuppressWarnings("unused")
					int i = JOptionPane.showConfirmDialog(null, "Do you want to Exit?");
					if (i == 0) {
						dispose();
					}
				}
			});

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error createLayout" + ex.toString());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == btn_oldUser) {
			logIn login = new logIn();
		} else if (source == btn_newUser) {
			NewUser newuser = new NewUser();
		} else if (source == btn_admin) {
			logIn login = new logIn();
		}

	}

}
