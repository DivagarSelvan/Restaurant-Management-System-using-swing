package com.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class AdminActivity extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminActivity frame = new AdminActivity();
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
	public AdminActivity() {
		setFont(new Font("Arial", Font.BOLD, 18));
		setBackground(new Color(255, 218, 185));
		setForeground(Color.GRAY);
		setTitle("Admin Activity");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 874, 505);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Customer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerFrame c = new CustomerFrame();
				c.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 23));
		btnNewButton.setMnemonic('C');
		btnNewButton.setBounds(341, 44, 368, 52);
		contentPane.add(btnNewButton);

		JButton btnSales = new JButton("Sales");
		btnSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SalesFrame s = new SalesFrame();
				s.setVisible(true);
				dispose();
			}
		});
		btnSales.setMnemonic('S');
		btnSales.setFont(new Font("Times New Roman", Font.BOLD, 23));
		btnSales.setBounds(341, 121, 368, 52);
		contentPane.add(btnSales);

		JButton btnMembers = new JButton("Members");
		btnMembers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MembersFrame m = new MembersFrame();
				m.setVisible(true);
				dispose();
			}
		});
		btnMembers.setMnemonic('M');
		btnMembers.setFont(new Font("Times New Roman", Font.BOLD, 23));
		btnMembers.setBounds(341, 199, 368, 52);
		contentPane.add(btnMembers);

		JButton btnMenu = new JButton("Menu");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuFrame mu = new MenuFrame();
				mu.setVisible(true);
				dispose();
			}
		});
		btnMenu.setMnemonic('M');
		btnMenu.setFont(new Font("Times New Roman", Font.BOLD, 23));
		btnMenu.setBounds(341, 281, 368, 52);
		contentPane.add(btnMenu);

		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnLogOut.setMnemonic('L');
		btnLogOut.setFont(new Font("Times New Roman", Font.BOLD, 23));
		btnLogOut.setBounds(341, 366, 368, 52);
		contentPane.add(btnLogOut);
	}

}
