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

import com.model.Members;

public class ViewMembers extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JButton btnSearch;
	private JTable table;
	private JTable tblViewCustomer;

	MembersFrame memFrameObj;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewMembers frame = new ViewMembers();
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
	public ViewMembers() {
		setTitle("Members Details");
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

		table = new JTable();
		table.setBounds(6, 15, 816, -251);
		panel.add(table);

		table.setFont(new Font("Times New Roman", Font.BOLD, 18));
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null }, { null, null, null }, },
				new String[] { "Customer_ID", "Customer_Name", "Order_Date" }));

		JButton btnGetDetails = new JButton("Get Details");
		btnGetDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (tblViewCustomer.getSelectedRow() != -1 || tblViewCustomer.getSelectedColumn() != -1) {
						String customerId = (String) tblViewCustomer.getValueAt(tblViewCustomer.getSelectedRow(), 0);
						System.out.println(customerId);
						memFrameObj = new MembersFrame();
						memFrameObj.getCustomerDetailById(customerId);
						setVisible(false);
						memFrameObj.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Please Select the Row...", "Info",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (IndexOutOfBoundsException exception) {
					exception.printStackTrace();
				}
			}
		});
		// btnGetDetails.setIcon(new
		// ImageIcon(ViewCustomer.class.getResource("/icons/details.png")));
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
		tblViewCustomer.setModel(new DefaultTableModel(new Object[][] { { null, null, null }, },
				new String[] { "Customer_ID", "Customer_Name", "Join_Date" }));
		tblViewCustomer.getColumnModel().getColumn(0).setPreferredWidth(151);
		tblViewCustomer.getColumnModel().getColumn(1).setPreferredWidth(187);
		tblViewCustomer.getColumnModel().getColumn(2).setPreferredWidth(106);
		populateTable();
	}

	void populateTable() {
		memFrameObj = new MembersFrame();
		DefaultTableModel model = (DefaultTableModel) tblViewCustomer.getModel();
		List<Members> membersList = memFrameObj.getCustomerDetails();
		Object rowData[] = new Object[tblViewCustomer.getColumnCount()];
		System.out.println("rowdata=" + rowData.length);
		model.setRowCount(0);
		/* Print ArrayList Size */
		System.out.println("Array List Size=" + membersList.size());
		for (int i = 0; i < membersList.size(); i++) {
			/*
			 * Display ArrayList Datas here
			 * System.out.println(adminList.get(i).getAdmin_ID()+" "+adminList.get(i).
			 * getAdmin_Name()+
			 * " "+adminList.get(i).getDob()+" "+adminList.get(i).getGender()+" "+adminList.
			 * get(i).getAddress()+
			 * " "+adminList.get(i).getMobile_No()+" "+adminList.get(i).getEmail());
			 */
			rowData[0] = membersList.get(i).getCustomer_ID();
			rowData[1] = membersList.get(i).getCustomer_Name();
			rowData[2] = membersList.get(i).getJoin_Date();
			System.out.println(rowData[0] + "\t" + rowData[1] + "\t" + rowData[2]);
			model.addRow(rowData);
		}
	}

	void filterTable() {
		DefaultTableModel model = (DefaultTableModel) tblViewCustomer.getModel();
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
		tblViewCustomer.setRowSorter(sorter);
		String text = textField.getText();
		try {
			sorter.setRowFilter(RowFilter.regexFilter(text));
		} catch (PatternSyntaxException pse) {
			System.err.println("Bad regex pattern");
		}
	}

}
