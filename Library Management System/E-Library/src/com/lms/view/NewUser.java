package com.lms.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
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

import com.lms.daoImpl.UserDaoImpl;
import com.lms.model.User;
import com.lms.util.SettingMenu;

public class NewUser extends JDialog {
	private static final long serialVersionUID = 1L;
	private JButton btn_save;
	private JTextField txt_Name, txt_Age, txt_roll;
	private JPasswordField userpassword;
	private JComboBox<String> cmb_gender;
	private String array_gender[] = { "CIS-34", "CIS-34", "CIS-35", "CIS-36", "CIS-37", "CIS-38", "CIS-39" };
	Font textsize = new Font("Arial", Font.BOLD, 14);
	UserDaoImpl userinfo = new UserDaoImpl();
	String current_date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
	SettingMenu setting = new SettingMenu();

	public NewUser() {
		this.createDalog();
	}

	private void createDalog() {
		try {
			this.setSize(400, 390);
			this.setResizable(false);
			this.setLayout(null);
			this.setLocationRelativeTo(null);
			JLabel lbl_Name = new JLabel("Name: ");
			JLabel lbl_Age = new JLabel("email: ");
			JLabel lbl_password = new JLabel("Password: ");
			JLabel lbl_Gender = new JLabel("Batch: ");
			JLabel lbl_roll = new JLabel("Roll No: ");

			txt_Name = new JTextField();
			txt_Age = new JTextField();
			cmb_gender = new JComboBox<String>();
			txt_roll = new JTextField();
			userpassword = new JPasswordField();

			btn_save = new JButton("Save");

			for (int i = 0; i < array_gender.length; i++) {
				cmb_gender.addItem(array_gender[i]);
			}

			lbl_Name.setBounds(50, 30, 180, 30);
			lbl_Age.setBounds(50, 80, 180, 30);
			lbl_Gender.setBounds(50, 180, 180, 30);
			lbl_roll.setBounds(50, 230, 180, 30);
			lbl_password.setBounds(50, 130, 180, 30);

			txt_Name.setBounds(130, 30, 210, 30);
			txt_Age.setBounds(130, 80, 210, 30);
			userpassword.setBounds(130, 130, 210, 30);
			cmb_gender.setBounds(130, 180, 210, 30);
			btn_save.setBounds(130, 290, 100, 30);
			txt_roll.setBounds(130, 230, 210, 30);

			lbl_Name.setFont(textsize);
			lbl_Age.setFont(textsize);
			lbl_Gender.setFont(textsize);
			lbl_roll.setFont(textsize);
			lbl_password.setFont(textsize);
			txt_roll.setFont(textsize);
			txt_Name.setFont(textsize);
			txt_Age.setFont(textsize);
			cmb_gender.setFont(textsize);
			btn_save.setFont(textsize);
			userpassword.setFont(textsize);
			btn_save.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					String userName = txt_Name.getText().toString();
					String email = txt_Age.getText().toString();
					String userGender = cmb_gender.getSelectedItem().toString();
					String userroll = txt_roll.getText().toString();
					String userpass = userpassword.getText().toString();
					Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
					Matcher m = p.matcher(email);
					if (userName.equals("") || email.equals("") || userroll.equals("") || userpass.equals("")) {
						JOptionPane.showMessageDialog(null, "Field Must Not Empty!");
					} else if (!m.find()) {
						JOptionPane.showMessageDialog(null, "Enter a valid email address!!");
					} else {
						try {
							if (userinfo.checkUser(email) == true) {
								JOptionPane.showMessageDialog(null, "Sorry Email is Exist");
							} else {

								int rnd = (int) (Math.random() * 100);
								String rndstring = Integer.toString(rnd);
								String getstore = userName.substring(0, 3);
								String mergename = getstore + rndstring;
								userinfo.addUser(new User(0, userName, email, userGender, userroll, mergename,
										current_date, userpass));
								
								txt_Name.setText("");
								txt_Age.setText("");
								txt_roll.setText("");
								userpassword.setText("");
								JOptionPane.showMessageDialog(null, "Enroll Now!!!");
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
			});

			this.add(lbl_Name);
			this.add(lbl_Age);
			this.add(lbl_Gender);
			this.add(lbl_roll);
			this.add(lbl_password);

			this.add(txt_Name);
			this.add(txt_Age);
			this.add(cmb_gender);
			this.add(btn_save);
			this.add(txt_roll);
			this.add(userpassword);
			//his.getContentPane().setBackground(Color.decode("#FFFFFF"));

			this.setVisible(true);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.toString());
		}
	}
}
