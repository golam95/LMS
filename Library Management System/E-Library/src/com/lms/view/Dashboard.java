package com.lms.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.lms.daoImpl.BookOrderDaoImpl;
import com.lms.daoImpl.DBConnection;
import com.lms.model.Bookorder;

public class Dashboard extends JFrame implements ActionListener, MouseListener, ListSelectionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String getusername = null;
	String getusercode = null;
	BookOrderDaoImpl orderdaoimpl = new BookOrderDaoImpl();
	public static final Dimension PREFERREDSIZE = new Dimension(1000, 600);
	JButton btn_sendrequest = new JButton("Send Request");
	JButton btn_displayrequest = new JButton("Display Request");
	private JComboBox cmbtype = new JComboBox();
	private JList<String> listofbook = new JList<String>();
	Border thickBorder = new LineBorder(Color.decode("#394B58"), 3);
	Font font = new Font("Arial", Font.BOLD, 15);
	JTextField txtbookId = new JTextField();
	JLabel lbltypeofbook = new JLabel("Book type: ");
	JButton btn_back = new JButton("Back");
	JButton btn_allow = new JButton("Allow");
	JButton lbllogout = new JButton("Logout");
	//
	JTextField txt_retriveid = new JTextField();
	JPanel pantable = new JPanel();
	private JTable tbl_details = new JTable();
	private JScrollPane pane_userDetails = new JScrollPane();
	//
	String current_date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

	public Dashboard(String username, String usercode) {
		this.getusername = username;
		this.getusercode = usercode;
		this.createDashboard();
	}

	private void createDashboard() {
		try {
			this.setSize(1000, 600);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setMinimumSize(PREFERREDSIZE);
			this.setPreferredSize(PREFERREDSIZE);
			this.setResizable(false);
			this.setLayout(null);
			this.setLocationRelativeTo(null);

			JPanel pantitle = new JPanel();
			pantitle.setBounds(160, 30, 700, 50);
			pantitle.setBackground(Color.decode("#367C38"));
			JLabel lblEmail = new JLabel("Name: " + getusername);
			lblEmail.setFont(font);

			lbllogout.setFont(font);
			lbllogout.addActionListener(this);
			lblEmail.setBounds(70, 40, 100, 30);
			lbllogout.setBounds(570, 40, 100, 30);
			lblEmail.setForeground(Color.WHITE);
			lbllogout.setForeground(Color.ORANGE);
			pantitle.add(lblEmail);
			pantitle.add(lbllogout);

			txtbookId.setFont(font);
			txtbookId.setBounds(620, 250, 210, 30);

			btn_sendrequest.setBounds(620, 340, 210, 30);
			btn_displayrequest.setBounds(620, 380, 210, 30);

			btn_sendrequest.addActionListener(this);
			btn_displayrequest.addActionListener(this);

			lbltypeofbook.setBounds(240, 110, 80, 30);
			btn_back.setBounds(150, 100, 80, 30);
			btn_allow.setBounds(280, 100, 80, 30);
			cmbtype.setBounds(340, 110, 320, 30);

			txt_retriveid.setBounds(430, 100, 80, 30);

			btn_back.addActionListener(this);
			btn_allow.addActionListener(this);
			cmbtype.addItem("Story Book");
			cmbtype.addItem("Cook Book");
			cmbtype.addItem("Text Book");
			cmbtype.addActionListener(this);
			listofbook.setBounds(120, 190, 320, 330);
			listofbook.setBorder(thickBorder);
			listofbook.addListSelectionListener(this);
			listofbook.setFont(font);

			pantable.setLayout(new GridLayout(0, 1));
			pantable.setBackground(Color.red);
			pantable.setBounds(100, 270, 820, 260);

			tbl_details.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {},
					new String[] { "SI", "BookId", "Usercode", "Status", "Date" }));
			tbl_details.addMouseListener(this);
			tbl_details.setFont(font);
			pane_userDetails.setViewportView(tbl_details);
			// txt_retriveid.setVisible(false);
			pantable.add(pane_userDetails);
			this.add(pantable);
			this.add(txt_retriveid);
			this.add(btn_back);
			this.add(btn_allow);
			this.add(listofbook);
			this.add(lbltypeofbook);
			this.add(cmbtype);
			this.add(txtbookId);
			this.add(listofbook);
			this.add(btn_sendrequest);
			this.add(pantitle);
			this.add(btn_displayrequest);

			if (getusercode.equals("admin")) {
				pantable.setVisible(true);
				btn_allow.setVisible(true);
				adminvisible();
			} else {
				showtablefalse();
			}
			Show_userInfo();
			this.setVisible(true);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Error main dashboard");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == btn_back) {
			showtablefalse();
		} else if (source == lbllogout) {
			this.setVisible(false);
			MainFrame.createAndShowGUI();
		} else if (source == btn_allow) {
			if (txt_retriveid.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Please select table row!!");
			} else {

				try {
					if (orderdaoimpl.checkStatus(txt_retriveid.getText(), "Not Allow") == true) {
						orderdaoimpl.updatebookorder(new Bookorder("Allow", Integer.parseInt(txt_retriveid.getText())));
						DefaultTableModel model = (DefaultTableModel) tbl_details.getModel();
						model.setRowCount(0);
						Show_userInfo();
						JOptionPane.showMessageDialog(null, "permission is allow!!");
					} else {
						JOptionPane.showMessageDialog(null, "Sorry,permission is available!!");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// sager

			}

		} else if (source == btn_sendrequest) {
			String getbookId = txtbookId.getText().toString();
			if (getbookId.equals("")) {
				JOptionPane.showMessageDialog(null, "Field must not be empty!!");
			} else {
				try {
					orderdaoimpl.addOrder(new Bookorder(0, getbookId, getusercode, current_date, "Not Allow"));
					JOptionPane.showMessageDialog(null, "Your request has been sent!!!");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} else if (source == btn_displayrequest) {
			DefaultTableModel model = (DefaultTableModel) tbl_details.getModel();
			model.setRowCount(0);
			Show_userInfo();
			showtabletrue();
		} else if (cmbtype.getSelectedItem().equals("Story Book") || cmbtype.getSelectedItem().equals("Cook Book")
				|| cmbtype.getSelectedItem().equals("Text Book")) {
			String query = "SELECT * FROM booklist WHERE typebook='" + cmbtype.getSelectedItem().toString() + "'";
			fill_listphysical(query);
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

		if (getusercode.equals("admin")) {

			int j = tbl_details.getSelectedRow();
			TableModel modelq = tbl_details.getModel();
			txt_retriveid.setText(modelq.getValueAt(j, 0).toString());
			String getId = modelq.getValueAt(j, 2).toString();
			String getbookId = modelq.getValueAt(j, 1).toString();
			JOptionPane.showMessageDialog(null,
					"A new user registered.Registration number is: " + getId + "\n" + "User " + getId
							+ " requested for book " + getbookId + "\n" + "Book " + getbookId + " was issued to user "
							+ getId);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void fill_listphysical(String str) {
		Connection con = null;
		DefaultListModel<String> m = new DefaultListModel<String>();
		try {
			con = DBConnection.getConnecttion();
		} catch (Exception e) {

		}
		try {
			Statement stmt = con.createStatement();
			String query = str;
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String date = rs.getString(3).trim();
				m.addElement(date);
			}
			listofbook.setModel(m);
			con.close();
		} catch (Exception ex) {
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getSource() == listofbook) {
			String getItem = listofbook.getSelectedValue().toString();
			String query = "select * from booklist where bookname='" + getItem + "'";
			retrivebookId(query);
		}

	}

	public void retrivebookId(String getquery) {
		Connection con = null;
		try {
			con = DBConnection.getConnecttion();
		} catch (Exception e) {

		}
		try {
			Statement stmt = con.createStatement();
			String query = getquery;
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String bookId = rs.getString(4).trim();
				txtbookId.setText(bookId);
			}
			con.close();
		} catch (Exception ex) {

		}

	}

	public ArrayList<Bookorder> receiveorderinfo() {
		ArrayList<Bookorder> usersList = new ArrayList<Bookorder>();
		Connection con = null;
		try {
			con = DBConnection.getConnecttion();
		} catch (Exception e) {

		}
		String query = null;
		if (getusercode.equals("admin")) {
			query = "select * from bookorder";
		} else {
			query = "select * from bookorder where usercode='" + getusercode + "' and role='" + "Allow" + "'";
		}

		Statement st;
		ResultSet rs;
		try {
			st = con.createStatement();
			rs = st.executeQuery(query);
			Bookorder user;
			while (rs.next()) {
				user = new Bookorder(rs.getInt("id"), rs.getString("bookId"), rs.getString("usercode"),
						rs.getString("date"), rs.getString("role"));
				usersList.add(user);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return usersList;
	}

	// Display Data In JTable

	public void Show_userInfo() {
		ArrayList<Bookorder> list = receiveorderinfo();
		DefaultTableModel model = (DefaultTableModel) tbl_details.getModel();
		Object[] row = new Object[5];
		for (int i = 0; i < list.size(); i++) {
			row[0] = list.get(i).getOrderId();
			row[1] = list.get(i).getBooKId();
			row[2] = list.get(i).getUserCode();
			row[3] = list.get(i).getRole();
			row[4] = list.get(i).getDate();
			model.addRow(row);
		}

	}

	private void showtabletrue() {
		txtbookId.setVisible(false);
		listofbook.setVisible(false);
		btn_sendrequest.setVisible(false);
		btn_displayrequest.setVisible(false);
		cmbtype.setVisible(false);
		lbltypeofbook.setVisible(false);

		btn_back.setVisible(true);
		pantable.setVisible(true);
		txt_retriveid.setVisible(false);

	}

	private void showtablefalse() {
		txtbookId.setVisible(true);
		listofbook.setVisible(true);
		btn_sendrequest.setVisible(true);
		btn_displayrequest.setVisible(true);
		cmbtype.setVisible(true);
		lbltypeofbook.setVisible(true);

		pantable.setVisible(false);
		btn_back.setVisible(false);
		btn_allow.setVisible(false);
		txt_retriveid.setVisible(false);

	}

	private void adminvisible() {
		txtbookId.setVisible(false);
		listofbook.setVisible(false);
		btn_sendrequest.setVisible(false);
		btn_displayrequest.setVisible(false);
		cmbtype.setVisible(false);
		lbltypeofbook.setVisible(false);
		btn_back.setVisible(false);
		txt_retriveid.setVisible(false);

	}

}
