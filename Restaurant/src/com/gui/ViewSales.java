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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.model.Sales;

public class ViewSales extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable tblViewSales;
	private JButton btnSearch;

	SalesFrame saleFrameObj;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewSales frame = new ViewSales();
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
	public ViewSales() {
		setTitle("Sales_Details");
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

		JButton btnGetDetails = new JButton("Get Details");
		btnGetDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (tblViewSales.getSelectedRow() != -1 || tblViewSales.getSelectedColumn() != -1) {
						String CustomerId = (String) tblViewSales.getValueAt(tblViewSales.getSelectedRow(), 0);
						System.out.println(CustomerId);
						saleFrameObj = new SalesFrame();
						saleFrameObj.getCustomerDetailById(CustomerId);
						setVisible(false);
						saleFrameObj.setVisible(true);
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

		tblViewSales = new JTable();
		tblViewSales.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tblViewSales);

		tblViewSales.setFont(new Font("Times New Roman", Font.BOLD, 12));
		tblViewSales.setModel(new DefaultTableModel(new Object[][] { { null, null, null }, },
				new String[] { "Customer_ID", "Order_Date", "Product_ID" }));
		tblViewSales.getColumnModel().getColumn(0).setPreferredWidth(151);
		tblViewSales.getColumnModel().getColumn(1).setPreferredWidth(187);
		tblViewSales.getColumnModel().getColumn(2).setPreferredWidth(106);
		populateTable();
	}

	void populateTable() {
		SalesFrame sframe = new SalesFrame();
		DefaultTableModel model = (DefaultTableModel) tblViewSales.getModel();
		List<Sales> salesList = sframe.getCustomerDetails();
		Object rowData[] = new Object[tblViewSales.getColumnCount()];
		System.out.println("rowdata=" + rowData.length);
		model.setRowCount(0);
		/* Print ArrayList Size */
		System.out.println("Array List Size=" + salesList.size());
		for (int i = 0; i < salesList.size(); i++) {

			rowData[0] = salesList.get(i).getCustomer_ID();
			rowData[1] = salesList.get(i).getDate();
			rowData[2] = salesList.get(i).getProduct_ID();
			System.out.println(rowData[0] + "\t" + rowData[1] + "\t" + rowData[2]);
			model.addRow(rowData);
		}
	}

	void filterTable() {
		DefaultTableModel model = (DefaultTableModel) tblViewSales.getModel();
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
		tblViewSales.setRowSorter(sorter);
		String text = btnSearch.getText();
		try {
			sorter.setRowFilter(RowFilter.regexFilter(text));
		} catch (PatternSyntaxException pse) {
			System.err.println("Bad regex pattern");
		}
	}
}
