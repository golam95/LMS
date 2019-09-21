package com.lms.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.lms.daoImpl.DBConnection;
import com.lms.daoImpl.UserDaoImpl;
import com.lms.util.SettingMenu;

public class logIn extends JDialog {
	private static final long serialVersionUID = 1L;
	private JButton btn_save;
	private JTextField txt_email;
	private JPasswordField txt_password;
	Font textsize = new Font("Arial", Font.BOLD, 14);
	UserDaoImpl userinfo = new UserDaoImpl();
	String current_date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
	SettingMenu setting = new SettingMenu();

	String getuserName = null;
	String getusrCode = null;

	public logIn() {
		this.createDalog();
	}

	private void createDalog() {
		try {
			this.setSize(400, 230);
			this.setResizable(false);
			this.setLayout(null);
			this.setLocationRelativeTo(null);
			JLabel lbl_Name = new JLabel("Email: ");
			JLabel lbl_Age = new JLabel("Password: ");
			txt_email = new JTextField();
			txt_password = new JPasswordField();

			btn_save = new JButton("LogIn");

			lbl_Name.setBounds(50, 30, 180, 30);
			lbl_Age.setBounds(50, 90, 180, 30);

			txt_email.setBounds(130, 30, 210, 30);
			txt_password.setBounds(130, 90, 210, 30);

			btn_save.setBounds(130, 150, 100, 30);
			lbl_Name.setFont(textsize);
			lbl_Age.setFont(textsize);
			txt_email.setFont(textsize);
			txt_password.setFont(textsize);

			btn_save.setFont(textsize);

			btn_save.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					String userName = txt_email.getText().toString();
					String userAge = txt_password.getText().toString();
					Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
					Matcher m = p.matcher(userName);
					if (userName.equals("") || userAge.equals("")) {
						JOptionPane.showMessageDialog(null, "Field Must Not Empty!");
					} else if (!m.find()) {

						JOptionPane.showMessageDialog(null, "Enter a valid email address!!");

					} else {
						try {
							if (userinfo.checklogIn(userName, userAge) == true) {

								Connection con = null;
								try {
									con = DBConnection.getConnecttion();
								} catch (Exception e) {

								}
								try {
									Statement stmt = con.createStatement();
									String query = "select * from user where useremail ='" + userName + "'";
									ResultSet rs = stmt.executeQuery(query);
									while (rs.next()) {
										getuserName = rs.getString(2).trim();
										getusrCode = rs.getString(5).trim();
									}
									rs = null;
									con.close();
								} catch (Exception ex) {
									ex.printStackTrace();
								}
								Dashboard dash = new Dashboard(getuserName, getusrCode);
								visiblefalse();
								MainFrame.disposeall();

							} else {
								JOptionPane.showMessageDialog(null, "Invalid email or password!!");
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}

					}

				}

			});

			this.add(lbl_Name);
			this.add(lbl_Age);
			this.add(txt_email);
			this.add(txt_password);
			this.add(btn_save);
			//this.getContentPane().setBackground(Color.decode("#668734"));
			this.setVisible(true);
		} catch (

		Exception ex) {
			JOptionPane.showMessageDialog(null, ex.toString());
		}
	}

	private void visiblefalse() {
		this.setVisible(false);
	}

}
