package com.model;

import java.util.Date;

public class Members {
	private String Customer_ID, Customer_Name;
	private Date join_Date;

	public Members() {
		// TODO Auto-generated constructor stub
	}

	public String getCustomer_ID() {
		return Customer_ID;
	}

	public void setCustomer_ID(String customer_ID) {
		Customer_ID = customer_ID;
	}

	public Date getJoin_Date() {
		return join_Date;
	}

	public void setJoin_Date(Date join_Date) {
		this.join_Date = join_Date;
	}

	public String getCustomer_Name() {
		return Customer_Name;
	}

	public void setCustomer_Name(String cutomer_Name) {
		Customer_Name = cutomer_Name;
	}

}
