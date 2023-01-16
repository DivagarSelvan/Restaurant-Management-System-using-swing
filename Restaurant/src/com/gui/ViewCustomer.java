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

import com.model.Customer;

public class ViewCustomer extends JFrame {
	/**
	*
	*/
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSearchCustomer;
	public JTable tblViewCustomer;
	CustomerFrame customerFrameObj;
	DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewCustomer frame = new ViewCustomer();
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
	public ViewCustomer() {
		setTitle("Customer_Details");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1066, 556);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		txtSearchCustomer = new JTextField();
		txtSearchCustomer.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				filterTable();
			}
		});
		txtSearchCustomer.setFont(new Font("Times New Roman", Font.BOLD, 18));
		txtSearchCustomer.setBounds(22, 20, 873, 36);
		contentPane.add(txtSearchCustomer);
		txtSearchCustomer.setColumns(10);

		JButton btnSearch = new JButton("Search");
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
					if (tblViewCustomer.getSelectedRow() != -1 || tblViewCustomer.getSelectedColumn() != -1) {
						String customerId = (String) tblViewCustomer.getValueAt(tblViewCustomer.getSelectedRow(), 0);
						System.out.println(customerId);
						customerFrameObj = new CustomerFrame();
						customerFrameObj.getCustomerDetailById(customerId);
						setVisible(false);
						customerFrameObj.setVisible(true);
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

		tblViewCustomer = new JTable();
		tblViewCustomer.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tblViewCustomer);

		tblViewCustomer.setFont(new Font("Times New Roman", Font.BOLD, 12));
		tblViewCustomer.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null }, },
				new String[] { "Customer_ID", "Customer_Name", "Gender", "Email", "Address", "Mobile_Number" }));
		tblViewCustomer.getColumnModel().getColumn(1).setPreferredWidth(90);
		tblViewCustomer.getColumnModel().getColumn(3).setPreferredWidth(120);
		tblViewCustomer.getColumnModel().getColumn(4).setPreferredWidth(90);
		tblViewCustomer.getColumnModel().getColumn(5).setPreferredWidth(90);
		populateTable();

	}

	/************ Populate Database Records into the Swing Table ************/
	void populateTable() {
		customerFrameObj = new CustomerFrame();
		model = (DefaultTableModel) tblViewCustomer.getModel();
		List<Customer> customerList = customerFrameObj.getCustomerDetails();
		Object rowData[] = new Object[tblViewCustomer.getColumnCount()];
		System.out.println("rowdata=" + rowData.length);
		model.setRowCount(0);
		/* Print ArrayList Size */
		System.out.println("Array List Size=" + customerList.size());
		for (int i = 0; i < customerList.size(); i++) {

			rowData[0] = customerList.get(i).getCustomer_ID();
			rowData[1] = customerList.get(i).getCustomerName();
			rowData[2] = customerList.get(i).getGender();
			rowData[3] = customerList.get(i).getEmail();
			rowData[4] = customerList.get(i).getAddress();
			rowData[5] = customerList.get(i).getMobile_Number();
			System.out.println(rowData[0] + "\t" + rowData[1] + "\t" + rowData[2] + "\t" + rowData[3] + "\t"
					+ rowData[4] + "\t" + rowData[5]);
			model.addRow(rowData);
		}
	}

	void filterTable() {
		model = (DefaultTableModel) tblViewCustomer.getModel();
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
		tblViewCustomer.setRowSorter(sorter);
		String text = txtSearchCustomer.getText();
		try {
			sorter.setRowFilter(RowFilter.regexFilter(text));
		} catch (PatternSyntaxException pse) {
			System.err.println("Bad regex pattern");
		}
	}
}