package com.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import com.model.Sales;

public class SalesFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private JTextField textField_1;
	private JTextField textField_2;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JComboBox comboBox;

	Connection con;
	Statement stmt;
	PreparedStatement ps;
	ResultSet rs;

	Sales salesObj;
	String query;
	Date date;

	int count;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalesFrame frame = new SalesFrame();
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
	public SalesFrame() {
		setFont(new Font("Arial", Font.BOLD, 18));
		setBackground(new Color(255, 218, 185));
		setForeground(Color.GRAY);
		setTitle("Sales");
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

		JLabel lblNewLabel_1 = new JLabel("Order_Date");
		lblNewLabel_1.setForeground(Color.YELLOW);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1.setBackground(Color.PINK);
		lblNewLabel_1.setBounds(286, 186, 175, 41);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Product_ID");
		lblNewLabel_2.setForeground(Color.YELLOW);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_2.setBackground(Color.PINK);
		lblNewLabel_2.setBounds(286, 280, 175, 41);
		contentPane.add(lblNewLabel_2);

		textField_1 = new JTextField();
		textField_1.setBackground(Color.WHITE);
		textField_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		textField_1.setBounds(471, 186, 267, 41);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CustomerMenu cm = new CustomerMenu();
				cm.lblNewLabel_1_1.setText(comboBox.getSelectedItem().toString());
				cm.dispose();
				cm.setVisible(true);
			}
		});
		textField_2.setEditable(false);
		textField_2.setBackground(Color.WHITE);
		textField_2.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		textField_2.setColumns(10);
		textField_2.setBounds(471, 280, 267, 41);
		contentPane.add(textField_2);

		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateBox();
				String join_d = java.time.LocalDate.now().toString();
				textField_1.setText(join_d);

			}
		});
		btnNewButton.setBackground(SystemColor.window);
		btnNewButton.setMnemonic('A');
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnNewButton.setBounds(49, 381, 123, 41);
		contentPane.add(btnNewButton);

		btnNewButton_1 = new JButton("Save");
		btnNewButton_1.setMnemonic('S');
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setBounds(211, 381, 123, 41);
		contentPane.add(btnNewButton_1);

		btnNewButton_2 = new JButton("View");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewSales vsObj = new ViewSales();
				dispose();
				vsObj.setVisible(true);
			}
		});
		btnNewButton_2.setMnemonic('V');
		btnNewButton_2.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.setBounds(374, 381, 123, 41);
		contentPane.add(btnNewButton_2);

		btnNewButton_3 = new JButton("Delete");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!comboBox.getSelectedItem().equals("")) {
					int confirmation = JOptionPane.showConfirmDialog(null, "Customer Confirm Delete...?",
							"Delete Customer", JOptionPane.YES_NO_OPTION);
					if (confirmation == 0) {
						query = "DELETE FROM sales WHERE CUSTOMER_ID=?";
						try {
							ps = con.prepareStatement(query);
							ps.setString(1, comboBox.getSelectedItem().toString());
							count = ps.executeUpdate();
							if (count != 0) {
								JOptionPane.showMessageDialog(null, "Sales Note Deleted...", "Delete Customer",
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
		btnNewButton_3.setMnemonic('D');
		btnNewButton_3.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnNewButton_3.setBackground(Color.WHITE);
		btnNewButton_3.setBounds(533, 381, 123, 41);
		contentPane.add(btnNewButton_3);

		btnNewButton_4 = new JButton("Close");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_4.setMnemonic('C');
		btnNewButton_4.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnNewButton_4.setBackground(Color.WHITE);
		btnNewButton_4.setBounds(686, 381, 123, 41);
		contentPane.add(btnNewButton_4);

		comboBox = new JComboBox();
		comboBox.setFont(new Font("Times New Roman", Font.BOLD, 18));
		comboBox.setForeground(Color.RED);
		comboBox.setBounds(471, 92, 271, 41);
		contentPane.add(comboBox);

	}

	public List<Sales> getCustomerDetails() {
		List<Sales> SalesList = new ArrayList<Sales>();
		try {
			con = DBConnection.getConnection();
			query = "SELECT * FROM SALES";
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				Sales saleObj = new Sales();
				saleObj.setCustomer_ID(rs.getString("CUSTOMER_ID"));
				saleObj.setDate(rs.getDate("ORDER_DATE"));
				saleObj.setProduct_ID(rs.getString("PRODUCT_ID"));
				SalesList.add(saleObj);
			}
		} catch (SQLException sqle) {
			System.out.println(sqle.getMessage());
		}
		return SalesList;
	}

	public void getCustomerDetailById(String customerId) {
		List<Sales> salesList = new ArrayList<Sales>();
		try {
			con = DBConnection.getConnection();
			query = "SELECT * FROM SALES WHERE CUSTOMER_ID = ?";
			ps = con.prepareStatement(query);
			ps.setString(1, customerId);
			rs = ps.executeQuery();
			while (rs.next()) {
				Sales saleObj = new Sales();
				saleObj.setCustomer_ID(rs.getString("CUSTOMER_ID"));
				saleObj.setDate(rs.getDate("ORDER_DATE"));
				saleObj.setProduct_ID(rs.getString("PRODUCT_ID"));

				salesList.add(saleObj);
			}
			comboBox.addItem(salesList.get(0).getCustomer_ID());
			textField_1.setText(String.valueOf(salesList.get(0).getDate()));
			textField_2.setText(salesList.get(0).getProduct_ID());
		} catch (SQLException sqle) {
			System.out.println(sqle.getMessage());
		}
	}

	private void updateBox() {
		query = "SELECT CUSTOMER_ID FROM CUSTOMER";
		con = DBConnection.getConnection();
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			comboBox.removeAllItems();
			while (rs.next()) {
				comboBox.addItem(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
