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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.dbutil.DBConnection;
import com.model.Menu;

public class CustomerMenu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table_1;
	public JLabel lblNewLabel_1_1;

	Connection con;
	Statement stmt;
	PreparedStatement ps;
	ResultSet rs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerMenu frame = new CustomerMenu();
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
	public CustomerMenu() {
		setTitle("Customer Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 556);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("TODAY's MENU");
		lblNewLabel.setForeground(Color.YELLOW);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel.setBounds(40, 11, 233, 57);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("Order");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String CustomerID = lblNewLabel_1_1.getText();
				Date d = new Date();
				java.sql.Date sqd = new java.sql.Date(d.getTime());
				String productId = (String) table_1.getValueAt(table_1.getSelectedRow(), 0);
				con = DBConnection.getConnection();
				try {
					if (table_1.getSelectedRow() != -1 || table_1.getSelectedColumn() != -1) {
						ps = con.prepareStatement(
								"INSERT INTO SALES (CUSTOMER_ID, ORDER_DATE, PRODUCT_ID) " + "VALUES(?,?,?)");
						ps.setString(1, CustomerID);
						ps.setDate(2, sqd);
						ps.setString(3, productId);
						ps.executeUpdate();
						JOptionPane.showMessageDialog(null, "Ordered Successfully", "Status",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Please Select the Item...", "Info",
								JOptionPane.INFORMATION_MESSAGE);
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setMnemonic('O');
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnNewButton.setBounds(62, 446, 166, 43);
		contentPane.add(btnNewButton);

		JButton btnClose = new JButton("Close");
		btnClose.setMnemonic('C');
		btnClose.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnClose.setBounds(262, 446, 166, 43);
		contentPane.add(btnClose);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 79, 401, 336);
		contentPane.add(scrollPane);

		table_1 = new JTable();
		table_1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table_1.setModel(new DefaultTableModel(new Object[][] { { null, null, null }, },
				new String[] { "Product_ID", "Product_Name", "Price" }));
		table_1.getColumnModel().getColumn(0).setPreferredWidth(51);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(93);
		scrollPane.setViewportView(table_1);

		JLabel lblNewLabel_1 = new JLabel("Customer ID : ");
		lblNewLabel_1.setForeground(new Color(30, 144, 255));
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(283, 32, 106, 22);
		contentPane.add(lblNewLabel_1);

		lblNewLabel_1_1 = new JLabel();
		lblNewLabel_1_1.setForeground(new Color(30, 144, 255));
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNewLabel_1_1.setBounds(386, 32, 63, 22);
		contentPane.add(lblNewLabel_1_1);
		populate();
	}

	private void populate() {
		DefaultTableModel model = (DefaultTableModel) table_1.getModel();
		List<Menu> menuList = getCustomerDetails();
		Object rowData[] = new Object[table_1.getColumnCount()];
		System.out.println("rowdata=" + rowData.length);
		model.setRowCount(0);
		/* Print ArrayList Size */
		System.out.println("Array List Size=" + menuList.size());
		for (int i = 0; i < menuList.size(); i++) {
			rowData[0] = menuList.get(i).getProduct_ID();
			rowData[1] = menuList.get(i).getProduct_Name();
			rowData[2] = menuList.get(i).getPrice();

			System.out.println(rowData[0] + "\t" + rowData[1] + "\t" + rowData[2]);
			model.addRow(rowData);
		}
	}

	public List<Menu> getCustomerDetails() {
		List<Menu> menuList = new ArrayList<Menu>();
		try {
			con = DBConnection.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM MENU");
			while (rs.next()) {
				Menu memObj = new Menu();
				memObj.setProduct_ID(rs.getString("PRODUCT_ID"));
				memObj.setProduct_Name(rs.getString("PRODUCT_NAME"));
				memObj.setPrice(rs.getDouble("PRICE"));

				menuList.add(memObj);
			}
		} catch (SQLException sqle) {
			System.out.println(sqle.getMessage());
		}
		return menuList;
	}
}
