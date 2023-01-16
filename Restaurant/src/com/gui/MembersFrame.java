package com.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.dbutil.DBConnection;
import com.model.Members;

public class MembersFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	JComboBox comboBox;

	String query;
	Connection con;
	Statement stmt;
	PreparedStatement ps;
	ResultSet rs;

	Members members;

	Date date;

	int count;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MembersFrame frame = new MembersFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MembersFrame() {
		setFont(new Font("Arial", Font.BOLD, 18));
		setBackground(new Color(255, 218, 185));
		setForeground(Color.GRAY);
		setTitle("Members");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 874, 505);

		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Customer_ID");
		lblNewLabel.setForeground(Color.YELLOW);
		lblNewLabel.setBackground(Color.PINK);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel.setBounds(286, 92, 175, 41);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Customer_Name");
		lblNewLabel_1.setForeground(Color.YELLOW);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1.setBackground(Color.PINK);
		lblNewLabel_1.setBounds(286, 186, 175, 41);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Join_Date");
		lblNewLabel_2.setForeground(Color.YELLOW);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_2.setBackground(Color.PINK);
		lblNewLabel_2.setBounds(286, 280, 175, 41);
		contentPane.add(lblNewLabel_2);
		comboBox = new JComboBox();
		comboBox.setForeground(Color.RED);
		setValues();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				members = new Members();
				String s = comboBox.getSelectedItem().toString();
				query = "SELECT Customer_id,Customer_name FROM CUSTOMER";
				try {
					ps = con.prepareStatement(query);
					rs = ps.executeQuery();
					while (rs.next()) {
						members.setCustomer_ID(rs.getString(1));
						members.setCustomer_Name(rs.getString(2));
						if (members.getCustomer_ID().equals(s)) {
							textField.setText(members.getCustomer_Name());
						}
					}
					String join_d = java.time.LocalDate.now().toString();
					textField_1.setText(join_d);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		comboBox.setBounds(471, 94, 267, 41);
		contentPane.add(comboBox);

		textField = new JTextField();
		textField.setBackground(Color.WHITE);
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		textField.setBounds(471, 186, 267, 41);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBackground(Color.WHITE);
		textField_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		textField_1.setColumns(10);
		textField_1.setBounds(471, 280, 267, 41);
		contentPane.add(textField_1);

		JButton btnNewButton = new JButton("Save");
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 17));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem().equals("")) {
					JOptionPane.showMessageDialog(null, "Customer ID Cannot Empty", "Error", JOptionPane.ERROR_MESSAGE);
					comboBox.requestFocus();
				} else if (textField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Customer Name Cannot Empty", "Error",
							JOptionPane.ERROR_MESSAGE);
					textField.requestFocus();
				} else if (textField_1.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Join Date Cannot Empty", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					members.setCustomer_ID(comboBox.getSelectedItem().toString());
					members.setCustomer_Name(textField.getText());
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					try {
						date = dateFormat.parse(textField_1.getText());
					} catch (ParseException ae) {
						ae.printStackTrace();
					}
					members.setJoin_Date(date);
					try {
						ps = con.prepareStatement("INSERT INTO MEMBERS(CUSTOMER_ID,JOIN_DATE) VALUES(?,?)");
						ps.setString(1, members.getCustomer_ID());
						java.sql.Date join_date = new java.sql.Date(date.getTime());
						ps.setDate(2, join_date);
						count = ps.executeUpdate();
						resetControls();
						if (count != 0) {
							JOptionPane.showMessageDialog(null, count + " New Member Added...", "Success",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, " Record Insertion Failed", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnNewButton.setBackground(SystemColor.window);
		btnNewButton.setBounds(75, 358, 141, 49);
		btnNewButton.setMnemonic('S');
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Remove");
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 17));
		btnNewButton_1.setBackground(SystemColor.window);
		btnNewButton_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (!comboBox.getSelectedItem().equals("")) {
					int confirmation = JOptionPane.showConfirmDialog(null, "Member Confirm Delete...?",
							"Delete Customer", JOptionPane.YES_NO_OPTION);
					if (confirmation == 0) {
						query = "DELETE FROM MEMBERS WHERE CUSTOMER_ID=?";
						try {
							ps = con.prepareStatement(query);
							ps.setString(1, comboBox.getSelectedItem().toString());
							count = ps.executeUpdate();
							if (count != 0) {
								JOptionPane.showMessageDialog(null, "Customer Deleted...", "Delete Customer",
										JOptionPane.INFORMATION_MESSAGE);
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
		btnNewButton_1.setBounds(473, 358, 141, 49);
		btnNewButton_1.setMnemonic('R');

		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Close");
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 17));
		btnNewButton_2.setBackground(SystemColor.window);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_2.setBounds(671, 358, 141, 49);
		btnNewButton_2.setMnemonic('C');
		contentPane.add(btnNewButton_2);

		JButton btnView = new JButton("View");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewMembers viewMem = new ViewMembers();
				dispose();
				viewMem.setVisible(true);
			}
		});
		btnView.setMnemonic('V');
		btnView.setFont(new Font("Times New Roman", Font.BOLD, 17));
		btnView.setBackground(Color.WHITE);
		btnView.setBounds(274, 358, 141, 49);
		contentPane.add(btnView);
	}

	private void setValues() {
		con = DBConnection.getConnection();
		try {
			query = "SELECT CUSTOMER_ID FROM CUSTOMER";
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				comboBox.addItem(rs.getString(1));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public List<Members> getCustomerDetails() {
		List<Members> membersList = new ArrayList<Members>();
		try {
			con = DBConnection.getConnection();
			query = "SELECT MEMBERS.CUSTOMER_ID, CUSTOMER.CUSTOMER_NAME, MEMBERS.JOIN_DATE FROM MEMBERS\r\n"
					+ " INNER JOIN CUSTOMER ON MEMBERS.CUSTOMER_ID = CUSTOMER.CUSTOMER_ID;";
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				members = new Members();
				members.setCustomer_ID(rs.getString("CUSTOMER_ID"));
				members.setCustomer_Name(rs.getString("CUSTOMER_NAME"));
				members.setJoin_Date(rs.getDate("JOIN_DATE"));
				membersList.add(members);
			}
		} catch (SQLException sqle) {
			System.out.println(sqle.getMessage());
		}
		return membersList;
	}

	public void getCustomerDetailById(String customerId) {
		List<Members> membersList = new ArrayList<Members>();
		try {
			con = DBConnection.getConnection();
			query = "SELECT MEMBERS.CUSTOMER_ID, CUSTOMER.CUSTOMER_NAME, MEMBERS.JOIN_DATE FROM MEMBERS\r\n"
					+ " INNER JOIN CUSTOMER ON MEMBERS.CUSTOMER_ID = CUSTOMER.CUSTOMER_ID \r\n"
					+ " WHERE MEMBERS.CUSTOMER_ID =?";
			ps = con.prepareStatement(query);
			ps.setString(1, customerId);
			rs = ps.executeQuery();
			while (rs.next()) {
				Members memObj = new Members();
				memObj.setCustomer_ID(rs.getString("CUSTOMER_ID"));
				memObj.setCustomer_Name(rs.getString("CUSTOMER_NAME"));
				memObj.setJoin_Date(rs.getDate("JOIN_Date"));

				membersList.add(memObj);
			}
			/* populate Fields to the corresponding controls */
			comboBox.addItem(membersList.get(0).getCustomer_ID());
			textField.setText(membersList.get(0).getCustomer_Name());
			textField_1.setText(String.valueOf(membersList.get(0).getJoin_Date()));
		} catch (SQLException sqle) {
			System.out.println(sqle.getMessage());
		}
	}

	private void resetControls() {
		textField.setText("");
		textField_1.setText("");
	}
}