package com.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
//import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.dbutil.DBConnection;
import com.model.Customer;

//import java.awt.Toolkit;
public class CustomerFrame extends JFrame {
	/**
	*
	*/
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCustomerID;
	private JTextField txtCustomerName;
	private JTextField txtEmail;
	private JRadioButton rdoMale, rdoFemale;
	private ButtonGroup rdoButtonGroup;
	JTextArea taAddress;
	Connection con;
	Statement stmt;
	PreparedStatement ps;
	ResultSet rs;
//create Model Object
	Customer customerObj;
	String query;
	private static int sequential_ID = 1000;
	int count = 0;
	private JTextField txtMobileno;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerFrame frame = new CustomerFrame();
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
	public CustomerFrame() {
//setIconImage(Toolkit.getDefaultToolkit().getImage(CustomerFrame.class.getResource("/icons/addCustomer.png")));
		// Main Frame
		setFont(new Font("Arial", Font.BOLD, 18));
		setBackground(new Color(255, 218, 185));
		setForeground(Color.GRAY);
		setTitle("Customer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 874, 505);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		// Customer_id
		JLabel lblNewLabel = new JLabel("Customer_ID");
		lblNewLabel.setForeground(Color.YELLOW);
		lblNewLabel.setBackground(Color.PINK);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel.setBounds(202, 23, 152, 34);
		contentPane.add(lblNewLabel);
		txtCustomerID = new JTextField();
		txtCustomerID.setForeground(Color.RED);
		txtCustomerID.setEditable(false);
		txtCustomerID.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtCustomerID.setBounds(395, 23, 335, 34);
		contentPane.add(txtCustomerID);
		txtCustomerID.setColumns(10);
		// Customer_name
		txtCustomerName = new JTextField();
		txtCustomerName.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtCustomerName.setBounds(395, 70, 335, 34);
		contentPane.add(txtCustomerName);
		txtCustomerName.setColumns(10);
		JLabel lblNewLabel_1 = new JLabel("Customer_Name");
		lblNewLabel_1.setForeground(Color.YELLOW);
		lblNewLabel_1.setBackground(Color.PINK);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1.setBounds(202, 70, 152, 34);
		contentPane.add(lblNewLabel_1);
		// Gender
		JLabel lblNewLabel_3 = new JLabel("Gender");
		lblNewLabel_3.setForeground(Color.YELLOW);
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_3.setBounds(201, 133, 105, 34);
		contentPane.add(lblNewLabel_3);
		rdoMale = new JRadioButton("Male ");
		rdoMale.setBackground(Color.PINK);
		rdoMale.setFont(new Font("Times New Roman", Font.BOLD, 18));
		rdoMale.setBounds(409, 142, 80, 34);
		contentPane.add(rdoMale);
		rdoFemale = new JRadioButton("Female ");
		rdoFemale.setFont(new Font("Times New Roman", Font.BOLD, 18));
		rdoFemale.setBackground(Color.PINK);
		rdoFemale.setBounds(547, 142, 99, 34);
		contentPane.add(rdoFemale);
		// radio button grouping
		rdoButtonGroup = new ButtonGroup();
		rdoButtonGroup.add(rdoMale);
		rdoButtonGroup.add(rdoFemale);
		rdoMale.setSelected(true);
		// Address
		JLabel lblNewLabel_4 = new JLabel("Address");
		lblNewLabel_4.setForeground(Color.YELLOW);
		lblNewLabel_4.setBackground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_4.setBounds(202, 266, 118, 41);
		contentPane.add(lblNewLabel_4);
		taAddress = new JTextArea();
		taAddress.setBounds(397, 248, 333, 85);
		contentPane.add(taAddress);
		// Email
		JLabel lblNewLabel_5_1 = new JLabel("Email");
		lblNewLabel_5_1.setForeground(Color.YELLOW);
		lblNewLabel_5_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_5_1.setBackground(Color.WHITE);
		lblNewLabel_5_1.setBounds(201, 191, 99, 34);
		contentPane.add(lblNewLabel_5_1);
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtEmail.setColumns(10);
		txtEmail.setBackground(Color.WHITE);
		txtEmail.setBounds(394, 191, 335, 34);
		contentPane.add(txtEmail);

		JLabel lblNewLabel_5 = new JLabel("Mobile_No");
		lblNewLabel_5.setForeground(Color.YELLOW);
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_5.setBackground(Color.WHITE);
		lblNewLabel_5.setBounds(202, 344, 118, 34);
		contentPane.add(lblNewLabel_5);
		txtMobileno = new JTextField();
		txtMobileno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char key = e.getKeyChar();
				if (((key < '0') || (key > '9')) && (key != KeyEvent.VK_BACK_SPACE)
						|| (txtMobileno.getText().length() > 9)) {
					e.consume(); // if it's not a number, ignore the event
				}
			}
		});
		txtMobileno.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtMobileno.setColumns(10);
		txtMobileno.setBackground(Color.WHITE);
		txtMobileno.setBounds(395, 344, 335, 34);
		contentPane.add(txtMobileno);
		// Add Customer
		JButton btnAdd = new JButton("Add Customer");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				con = DBConnection.getConnection();
				// Query for setting Auto-Generated ID to the Customer String query = "SELECT
				// MAX(CUSTOMER_ID) FROM CUSTOMER";
				stmt = null;
				customerObj = new Customer();
				try {
					stmt = con.createStatement();
					query = "SELECT MAX(CUSTOMER_ID) FROM CUSTOMER";
					ResultSet rs = stmt.executeQuery(query);
					rs.next();
					if (rs.getString(1) == null) {
						System.out.println("No Record");
						customerObj.setCustomer_ID("C#" + sequential_ID);
						txtCustomerID.setText(customerObj.getCustomer_ID());
					} else {
						sequential_ID = Integer.parseInt(rs.getString(1).substring(2, rs.getString(1).length())) + 1;
						System.out.println(sequential_ID);
						customerObj.setCustomer_ID("C#" + sequential_ID);
						txtCustomerID.setText(customerObj.getCustomer_ID());
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				resetControls();
			}
		});
		btnAdd.setMnemonic('A');
		// btnAdd.setIcon(new
		// ImageIcon(CustomerFrame.class.getResource("/icons/adduser.png")));
		btnAdd.setBackground(SystemColor.window);
		btnAdd.setFont(new Font("Times New Roman", Font.BOLD, 17));
		btnAdd.setBounds(20, 412, 176, 41);
		contentPane.add(btnAdd);
		// Save Button
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Save Button Clicked");
				con = DBConnection.getConnection();
				query = "SELECT MAX(CUSTOMER_ID) FROM CUSTOMER";
				stmt = null;
				customerObj = new Customer();
				if (txtCustomerID.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Customer ID Cannot Empty", "Error", JOptionPane.ERROR_MESSAGE);
					txtCustomerName.requestFocus();
				} else if (txtCustomerName.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Customer Name Cannot Empty", "Error",
							JOptionPane.ERROR_MESSAGE);
					txtCustomerName.requestFocus();
				} else if (txtEmail.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Email Cannot Empty", "Error", JOptionPane.ERROR_MESSAGE);
					txtEmail.requestFocus();
				} else if (taAddress.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Address Cannot Empty", "Error", JOptionPane.ERROR_MESSAGE);
					taAddress.requestFocus();
				} else if (txtMobileno.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Mobile Number Cannot Empty", "Error",
							JOptionPane.ERROR_MESSAGE);
					txtMobileno.requestFocus();
				} else if (txtMobileno.getText().length() < 10) {
					JOptionPane.showMessageDialog(null, "Mobile Number requires 10 digits", "Error",
							JOptionPane.ERROR_MESSAGE);
					txtMobileno.requestFocus();
				} else {
					customerObj.setCustomer_ID(txtCustomerID.getText());
					customerObj.setCustomerName(txtCustomerName.getText());
					customerObj.setGender(rdoMale.isSelected() ? "Male" : "Female");
					customerObj.setEmail(txtEmail.getText());
					customerObj.setAddress(taAddress.getText());
					customerObj.setMobile_Number(Long.parseLong(txtMobileno.getText()));
					try {
						ps = con.prepareStatement(
								"INSERT INTO CUSTOMER (CUSTOMER_ID,CUSTOMER_NAME,GENDER,EMAIL,ADDRESS,MOBILE_NUMBER) "
										+ "VALUES(?,?,?,?,?,?)");
						ps.setString(1, customerObj.getCustomer_ID());
						ps.setString(2, customerObj.getCustomerName());
						ps.setString(3, customerObj.getGender());
						ps.setString(4, customerObj.getEmail());
						ps.setString(5, customerObj.getAddress());
						ps.setLong(6, customerObj.getMobile_Number());
						count = ps.executeUpdate();
						if (count != 0) {
							JOptionPane.showMessageDialog(null, count + " New Customer Added...", "Success",
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
		btnSave.setMnemonic('S');
//btnSave.setIcon(new ImageIcon(CustomerFrame.class.getResource("/icons/savee.png")));
		btnSave.setBackground(SystemColor.window);
		btnSave.setFont(new Font("Times New Roman", Font.BOLD, 17));
		btnSave.setBounds(219, 412, 127, 41);
		contentPane.add(btnSave);
		// Delete
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Delete Button Clicked");
				if (!txtCustomerID.getText().equals("")) {
					int confirmation = JOptionPane.showConfirmDialog(null, "Customer Confirm Delete...?",
							"Delete Customer", JOptionPane.YES_NO_OPTION);
					if (confirmation == 0) {
						query = "DELETE FROM CUSTOMER WHERE CUSTOMER_ID=?";
						try {
							ps = con.prepareStatement(query);
							ps.setString(1, txtCustomerID.getText());
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
		btnDelete.setMnemonic('D');
		// btnDelete.setIcon(new
		// ImageIcon(CustomerFrame.class.getResource("/icons/delete.png")));
		btnDelete.setBackground(SystemColor.window);
		btnDelete.setFont(new Font("Times New Roman", Font.BOLD, 17));
		btnDelete.setBounds(584, 412, 118, 41);
		contentPane.add(btnDelete);
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Close Button Clicked");
				dispose();
			}
		});
		btnClose.setMnemonic('C');
		// btnClose.setIcon(new
		// ImageIcon(CustomerFrame.class.getResource("/icons/close.png")));
		btnClose.setBackground(SystemColor.window);
		btnClose.setFont(new Font("Times New Roman", Font.BOLD, 17));
		btnClose.setBounds(725, 412, 118, 41);
		contentPane.add(btnClose);
		// view Customer
		JButton btnView = new JButton("View Customer");
		btnView.setMnemonic('V');
		// btnView.setIcon(new
		// ImageIcon(CustomerFrame.class.getResource("/icons/search.png")));
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("View Customer Clicked");
				ViewCustomer viewCustomer = new ViewCustomer();
				dispose();
				viewCustomer.setVisible(true);
			}
		});
		btnView.setBackground(SystemColor.window);
		btnView.setFont(new Font("Times New Roman", Font.BOLD, 17));
		btnView.setBounds(373, 412, 184, 41);
		contentPane.add(btnView);
	}

	/*
	 * Retrieve All the Admin Details from the Database table and stored in the
	 * Array list then return it.
	 */
	public List<Customer> getCustomerDetails() {
		List<Customer> customerList = new ArrayList<Customer>();
		try {
			con = DBConnection.getConnection();
			query = "SELECT * FROM CUSTOMER";
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				Customer customerObj = new Customer();
				customerObj.setCustomer_ID(rs.getString("CUSTOMER_ID"));
				customerObj.setCustomerName(rs.getString("CUSTOMER_NAME"));
				customerObj.setGender(rs.getString("GENDER"));
				customerObj.setEmail(rs.getString("EMAIL"));
				customerObj.setAddress(rs.getString("ADDRESS"));
				customerObj.setMobile_Number(rs.getLong("MOBILE_NUMBER"));
				customerList.add(customerObj);
			}
		} catch (SQLException sqle) {
			System.out.println(sqle.getMessage());
		}
		return customerList;
	}

	public void getCustomerDetailById(String customerId) {
		List<Customer> customerList = new ArrayList<Customer>();
		try {
			con = DBConnection.getConnection();
			query = "SELECT * FROM CUSTOMER WHERE CUSTOMER_ID=?";
			ps = con.prepareStatement(query);
			ps.setString(1, customerId);
			rs = ps.executeQuery();
			while (rs.next()) {
				Customer customerObj = new Customer();
				customerObj.setCustomer_ID(rs.getString("CUSTOMER_ID"));
				customerObj.setCustomerName(rs.getString("CUSTOMER_NAME"));
				customerObj.setGender(rs.getString("GENDER"));
				customerObj.setEmail(rs.getString("EMAIL"));
				customerObj.setAddress(rs.getString("ADDRESS"));
				customerObj.setMobile_Number(rs.getLong("MOBILE_NUMBER"));
				customerList.add(customerObj);
			}
			/* populate Fields to the corresponding controls */
			txtCustomerID.setText(customerList.get(0).getCustomer_ID());
			txtCustomerName.setText(customerList.get(0).getCustomerName());
			String gender = customerList.get(0).getGender();
			if (gender.equalsIgnoreCase("Male"))
				rdoMale.setSelected(true);
			else
				rdoFemale.setSelected(true);
			taAddress.setText(customerList.get(0).getAddress());
			txtMobileno.setText(Long.toString(customerList.get(0).getMobile_Number()));
			txtEmail.setText(customerList.get(0).getEmail());
		} catch (SQLException sqle) {
			System.out.println(sqle.getMessage());
		}
	}

	private void resetControls() {
		txtCustomerName.setText("");
		rdoMale.setSelected(true);
		taAddress.setText("");
		txtMobileno.setText("");
		txtEmail.setText("");
		txtCustomerName.requestFocus();
	}
}