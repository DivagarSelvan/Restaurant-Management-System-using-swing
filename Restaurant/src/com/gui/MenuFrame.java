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
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.dbutil.DBConnection;
import com.model.Menu;

public class MenuFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;

	Connection con;
	Statement stmt;
	PreparedStatement ps;
	ResultSet rs;

	Menu menuObj;
	String query;

	int count;

	private static int sequential_ID = 1000;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuFrame frame = new MenuFrame();
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
	public MenuFrame() {

		setFont(new Font("Arial", Font.BOLD, 18));
		setBackground(new Color(255, 218, 185));
		setForeground(Color.GRAY);
		setTitle("Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 874, 505);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Product_ID");
		lblNewLabel.setForeground(Color.YELLOW);
		lblNewLabel.setBackground(Color.YELLOW);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel.setBounds(286, 92, 175, 41);
		contentPane.add(lblNewLabel);

		JLabel lblProductname = new JLabel("Product_Name");
		lblProductname.setForeground(Color.YELLOW);
		lblProductname.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblProductname.setBackground(Color.YELLOW);
		lblProductname.setBounds(286, 183, 175, 41);
		contentPane.add(lblProductname);

		JLabel lblPrice = new JLabel("Price");
		lblPrice.setForeground(Color.YELLOW);
		lblPrice.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblPrice.setBackground(Color.YELLOW);
		lblPrice.setBounds(286, 274, 175, 41);
		contentPane.add(lblPrice);

		textField = new JTextField();
		textField.setForeground(Color.RED);
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		textField.setEditable(false);
		textField.setBounds(454, 94, 291, 41);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBackground(Color.WHITE);
		textField_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		textField_1.setColumns(10);
		textField_1.setBounds(454, 183, 291, 41);
		contentPane.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		textField_2.setBackground(Color.WHITE);
		textField_2.setColumns(10);
		textField_2.setBounds(454, 274, 291, 41);
		contentPane.add(textField_2);

		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				con = DBConnection.getConnection();
				menuObj = new Menu();
				try {
					stmt = con.createStatement();
					query = "SELECT MAX(PRODUCT_ID) FROM MENU";
					ResultSet rs = stmt.executeQuery(query);
					rs.next();
					if (rs.getString(1) == null) {
						System.out.println("No Record");
						menuObj.setProduct_ID("P#" + sequential_ID);
						textField.setText(menuObj.getProduct_ID());
					} else {
						sequential_ID = Integer.parseInt(rs.getString(1).substring(2, rs.getString(1).length())) + 1;
						System.out.println(sequential_ID);
						menuObj.setProduct_ID("P#" + sequential_ID);
						textField.setText(menuObj.getProduct_ID());
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				resetControls();
			}
		});
		btnNewButton.setBackground(SystemColor.window);
		btnNewButton.setMnemonic('A');
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnNewButton.setBounds(49, 381, 123, 41);
		contentPane.add(btnNewButton);

		btnNewButton_1 = new JButton("Save");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				query = "SELECT MAX(PRODUCT_ID) FROM SALES";
				if (textField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Product ID Cannot Empty", "Error", JOptionPane.ERROR_MESSAGE);
					textField_1.requestFocus();
				} else if (textField_1.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Product Name Cannot Empty", "Error",
							JOptionPane.ERROR_MESSAGE);
					textField_1.requestFocus();
				} else if (textField_2.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Price Cannot Empty", "Error", JOptionPane.ERROR_MESSAGE);
					textField_2.requestFocus();
				} else {
					menuObj.setProduct_ID(textField.getText());
					menuObj.setProduct_Name(textField_1.getText());
					menuObj.setPrice(Double.parseDouble(textField_2.getText()));

					try {
						ps = con.prepareStatement("INSERT INTO MENU (PRODUCT_ID,PRODUCT_NAME,PRICE) VALUES(?,?,?)");
						ps.setString(1, menuObj.getProduct_ID());
						ps.setString(2, menuObj.getProduct_Name());
						ps.setDouble(3, menuObj.getPrice());

						count = ps.executeUpdate();
						if (count != 0) {
							JOptionPane.showMessageDialog(null, count + " New Product Added...", "Success",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, " Record Insertion Failed", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnNewButton_1.setMnemonic('S');
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setBounds(211, 381, 123, 41);
		contentPane.add(btnNewButton_1);

		btnNewButton_2 = new JButton("View");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewMenu v = new ViewMenu();
				v.setVisible(true);
				dispose();
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
				if (!textField.getText().equals("")) {
					int confirmation = JOptionPane.showConfirmDialog(null, "Product Confirm Delete...?",
							"Delete Product", JOptionPane.YES_NO_OPTION);
					if (confirmation == 0) {
						query = "DELETE FROM MENU WHERE PRODUCT_ID=?";
						try {
							ps = con.prepareStatement(query);
							ps.setString(1, textField.getText());
							count = ps.executeUpdate();
							if (count != 0) {
								JOptionPane.showMessageDialog(null, "Product Deleted...", "Delete Product",
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

	}

	public void getCustomerDetailById(String productId) {
		List<Menu> productList = new ArrayList<Menu>();
		try {
			con = DBConnection.getConnection();
			query = "SELECT * FROM MENU WHERE PRODUCT_ID=?";
			ps = con.prepareStatement(query);
			ps.setString(1, productId);
			rs = ps.executeQuery();
			while (rs.next()) {
				Menu menuObj = new Menu();
				menuObj.setProduct_ID(rs.getString("PRODUCT_ID"));
				menuObj.setProduct_Name(rs.getString("PRODUCT_NAME"));
				menuObj.setPrice(rs.getDouble("PRICE"));
				productList.add(menuObj);
			}
			/* populate Fields to the corresponding controls */
			textField.setText(productList.get(0).getProduct_ID());
			textField_1.setText(productList.get(0).getProduct_Name());
			textField_2.setText(String.valueOf(productList.get(0).getPrice()));
		} catch (SQLException sqle) {
			System.out.println(sqle.getMessage());
		}
	}

	public List<Menu> getCustomerDetails() {
		List<Menu> productList = new ArrayList<Menu>();
		try {
			con = DBConnection.getConnection();
			query = "SELECT * FROM MENU";
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				Menu MenuObj = new Menu();
				MenuObj.setProduct_ID(rs.getString("PRODUCT_ID"));
				MenuObj.setProduct_Name(rs.getString("PRODUCT_NAME"));
				MenuObj.setPrice(Double.parseDouble(rs.getString("PRICE")));
				productList.add(MenuObj);
			}
		} catch (SQLException sqle) {
			System.out.println(sqle.getMessage());
		}
		return productList;
	}

	private void resetControls() {
		textField_1.setText("");
		textField_2.setText("");
		textField_1.requestFocus();
	}
}
