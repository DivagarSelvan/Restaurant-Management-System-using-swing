package com.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.dbutil.DBConnection;

public class LoginFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField txtC;
	private JTextField textField_1;

	private String u_name;
	private long P_name;

	public String csId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
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
	public LoginFrame() {
		setFont(new Font("Arial", Font.BOLD, 18));
		setBackground(new Color(255, 218, 185));
		setForeground(Color.GRAY);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 874, 505);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		// Customer_id
		JLabel lblNewLabel = new JLabel("User_Name");
		lblNewLabel.setForeground(Color.YELLOW);
		lblNewLabel.setBackground(Color.PINK);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel.setBounds(204, 112, 152, 34);
		contentPane.add(lblNewLabel);
		txtC = new JTextField();
		txtC.setText("C#1000");
		txtC.setForeground(Color.RED);
		txtC.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtC.setBounds(392, 112, 335, 34);
		contentPane.add(txtC);
		txtC.setColumns(10);

		JLabel lblUserpassword = new JLabel("User_Password");
		lblUserpassword.setForeground(Color.YELLOW);
		lblUserpassword.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblUserpassword.setBackground(Color.PINK);
		lblUserpassword.setBounds(204, 210, 152, 34);
		contentPane.add(lblUserpassword);

		textField_1 = new JTextField();
		textField_1.setForeground(Color.RED);
		textField_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		textField_1.setColumns(10);
		textField_1.setBounds(392, 210, 335, 34);
		contentPane.add(textField_1);

		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtC.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "User_Name Cannot Empty", "Error", JOptionPane.ERROR_MESSAGE);
					txtC.requestFocus();
				} else if (textField_1.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "User_Password Cannot Empty", "Error",
							JOptionPane.ERROR_MESSAGE);
					textField_1.requestFocus();
				} else {
					Connection con = DBConnection.getConnection();
					try {
						PreparedStatement ps = con.prepareStatement(
								"SELECT CUSTOMER_ID, MOBILE_NUMBER FROM " + "CUSTOMER WHERE CUSTOMER_ID=?");
						csId = txtC.getText();
						ps.setString(1, txtC.getText());
						ResultSet rs = ps.executeQuery();
						while (rs.next()) {
							setU_name(rs.getString(1));
							setP_name(rs.getLong(2));
						}
						Long ph = Long.parseLong(textField_1.getText());
						Long pass = 12345678L;
						if (txtC.getText().equals(getU_name()) && ph.equals(getP_name())) {
							CustomerMenu cm = new CustomerMenu();
							cm.lblNewLabel_1_1.setText(txtC.getText());
							cm.setVisible(true);
							dispose();

						} else if (txtC.getText().equalsIgnoreCase("Divagar") && ph.equals(pass)) {
							AdminActivity act = new AdminActivity();
							act.setVisible(true);
							dispose();

						} else {
							JOptionPane.showMessageDialog(null, "please check your User_Name and Pass_Word", "Error",
									JOptionPane.ERROR_MESSAGE);
							txtC.requestFocus();
						}

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});
		btnNewButton.setMnemonic('L');
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton.setBounds(191, 331, 139, 47);
		contentPane.add(btnNewButton);

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setMnemonic('C');
		btnClose.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnClose.setBounds(465, 331, 139, 47);
		contentPane.add(btnClose);
	}

	public String getU_name() {
		return u_name;
	}

	public void setU_name(String u_name) {
		this.u_name = u_name;
	}

	public long getP_name() {
		return P_name;
	}

	public void setP_name(long p_name) {
		P_name = p_name;
	}

}
