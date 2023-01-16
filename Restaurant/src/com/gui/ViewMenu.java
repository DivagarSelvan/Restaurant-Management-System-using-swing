package com.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.model.Menu;

public class ViewMenu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	public JTable tblViewProduct;
	private JButton btnSearch;
	DefaultTableModel model;

	MenuFrame MenuFrameObj;
	Menu menu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewMenu frame = new ViewMenu();
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
	public ViewMenu() {
		setTitle("Menu Details");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1066, 556);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				filterTable();
			}
		});
		textField.setFont(new Font("Times New Roman", Font.BOLD, 18));
		textField.setBounds(22, 20, 873, 36);
		contentPane.add(textField);
		textField.setColumns(10);

		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				filterTable();
			}
		});

		btnSearch.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnSearch.setBounds(905, 16, 120, 43);
		contentPane.add(btnSearch);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel.setBounds(49, 344, 828, -230);
		contentPane.add(panel);
		panel.setLayout(null);

		JButton btnGetDetails = new JButton("Get Details");
		btnGetDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (tblViewProduct.getSelectedRow() != -1 || tblViewProduct.getSelectedColumn() != -1) {
						String productId = (String) tblViewProduct.getValueAt(tblViewProduct.getSelectedRow(), 0);
						System.out.println(productId);
						MenuFrameObj = new MenuFrame();
						MenuFrameObj.getCustomerDetailById(productId);
						setVisible(false);
						MenuFrameObj.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Please Select the Row...", "Info",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (IndexOutOfBoundsException exception) {
					exception.printStackTrace();
				}
			}
		});
		btnGetDetails.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnGetDetails.setBounds(276, 421, 159, 43);
		contentPane.add(btnGetDetails);
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnClose.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnClose.setBounds(502, 421, 120, 43);
		contentPane.add(btnClose);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 66, 873, 326);
		contentPane.add(scrollPane);

		tblViewProduct = new JTable();
		tblViewProduct.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tblViewProduct);

		tblViewProduct.setFont(new Font("Times New Roman", Font.BOLD, 12));
		tblViewProduct.setModel(new DefaultTableModel(new Object[][] { { null, null, null }, },
				new String[] { "Product_ID", "Product_Name", "Price" }));
		tblViewProduct.getColumnModel().getColumn(0).setPreferredWidth(151);
		tblViewProduct.getColumnModel().getColumn(1).setPreferredWidth(187);
		tblViewProduct.getColumnModel().getColumn(2).setPreferredWidth(106);
		populateTable();
	}

	void populateTable() {
		MenuFrameObj = new MenuFrame();
		model = (DefaultTableModel) tblViewProduct.getModel();
		List<Menu> productList = MenuFrameObj.getCustomerDetails();
		Object rowData[] = new Object[tblViewProduct.getColumnCount()];
		System.out.println("rowdata=" + rowData.length);
		model.setRowCount(0);
		System.out.println("Array List Size=" + productList.size());
		for (int i = 0; i < productList.size(); i++) {
			rowData[0] = productList.get(i).getProduct_ID();
			rowData[1] = productList.get(i).getProduct_Name();
			rowData[2] = productList.get(i).getPrice();
			System.out.println(rowData[0] + "\t" + rowData[1] + "\t" + rowData[2]);
			model.addRow(rowData);
		}
	}

	void filterTable() {
		model = (DefaultTableModel) tblViewProduct.getModel();
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
		tblViewProduct.setRowSorter(sorter);
		String text = textField.getText();
		try {
			sorter.setRowFilter(RowFilter.regexFilter(text));
		} catch (PatternSyntaxException pse) {
			System.err.println("Bad regex pattern");
		}
	}
}
